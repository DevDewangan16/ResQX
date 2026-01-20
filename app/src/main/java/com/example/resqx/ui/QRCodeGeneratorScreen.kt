package com.example.resqx.ui

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.*
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.resqx.R
import com.example.resqx.ui.data.DataBase
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

@Composable
fun QRCodeGeneratorScreen(resQXViewModel: ResQXViewModel) {

    var qrCodeBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    val vehicleDisplay by resQXViewModel.vehicleDisplay.collectAsState()
    val vehicleDetails by resQXViewModel.vehicleDetails.collectAsState()

    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted && qrCodeBitmap != null) {
                saveQRCode(context, qrCodeBitmap!!)
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = vehicleDisplay,
            onValueChange = { resQXViewModel.setVehicleDisplay(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("Vehicle Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                resQXViewModel.fetchVehicleDetails(vehicleDisplay)

                vehicleDetails?.let { details ->
                    val data = DataBase(
                        vehicleNo = details.vehicleNo,
                        ownerName = details.ownerName,
                        contact1 = details.contact1,
                        contact2 = details.contact2,
                        bloodGroup = details.bloodGroup,
                        allergies = details.allergies,
                        chronicCondition = details.chronicCondition
                    )

                    qrCodeBitmap = generateCarQRCode(data.toString(), context)
                }
            }
        ) {
            Text("Generate QR Code")
        }

        Spacer(modifier = Modifier.height(20.dp))

        qrCodeBitmap?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Car Shaped QR",
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        saveQRCode(context, bitmap)
                    } else {
                        permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    }
                }
            ) {
                Text("Download QR Code")
            }
        }
    }
}

/* ----------------------------------------------------
   GENERATE CAR-SHAPED QR CODE
---------------------------------------------------- */

private fun generateCarQRCode(text: String, context: Context): Bitmap? {
    return try {
        val size = 1200

        // Generate QR code first
        val qrBitmap = generateQRCode(text, size) ?: return null

        // Create final bitmap
        val result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)

        // Fill with white background
        canvas.drawColor(Color.WHITE)

        // Load car mask
        val carMask = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.car_mask
        ).let { original ->
            Bitmap.createScaledBitmap(original, size, size, true)
        }

        // Create a car-shaped bitmap by applying mask
        val carShapedQR = createCarShapedBitmap(qrBitmap, carMask, size)

        // Draw the car-shaped QR
        canvas.drawBitmap(carShapedQR, 0f, 0f, null)

        // Enhance finder patterns to ensure they're scannable
        enhanceFinderPatterns(canvas, size, carMask)

        drawBigRLogo(canvas, size, carMask)

        result
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

/* ----------------------------------------------------
   CREATE CAR-SHAPED BITMAP
---------------------------------------------------- */

private fun createCarShapedBitmap(qrBitmap: Bitmap, mask: Bitmap, size: Int): Bitmap {
    val result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(result)

    // Start with transparent background
    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)

    // Draw the car mask first (white car on transparent)
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    canvas.drawBitmap(mask, 0f, 0f, paint)

    // Use DST_IN to apply QR code only inside the car shape
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    canvas.drawBitmap(qrBitmap, 0f, 0f, paint)
    paint.xfermode = null

    return result
}

private fun enhanceFinderPatterns(canvas: Canvas, size: Int, carMask: Bitmap) {
    val finderSize = 70
    val padding = 100 // Position finder patterns well inside car shape

    // Paint for finder patterns
    val blackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }

    val whitePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    // Define finder pattern positions (adjust these based on your car mask shape)
    val finderPositions = listOf(
        // Top-left - positioned inside car windshield area
        RectF(padding.toFloat(), padding.toFloat(),
            (padding + finderSize).toFloat(), (padding + finderSize).toFloat()),

        // Top-right - positioned inside car windshield area
        RectF((size - padding - finderSize).toFloat(), padding.toFloat(),
            (size - padding).toFloat(), (padding + finderSize).toFloat()),

        // Bottom-left - positioned near car wheel area
        RectF(padding.toFloat(), (size - padding - finderSize).toFloat(),
            (padding + finderSize).toFloat(), (size - padding).toFloat())
    )

    // Draw enhanced finder patterns
    for (finderRect in finderPositions) {
        val centerX = finderRect.centerX()
        val centerY = finderRect.centerY()

        // Check if this position is inside the car mask (not transparent)
        if (isInsideCarMask(centerX.toInt(), centerY.toInt(), carMask)) {
            // Draw outer black square
            canvas.drawRect(finderRect, blackPaint)

            // Draw inner white square
            val innerSize = finderSize - 20
            val innerLeft = centerX - innerSize/2
            val innerTop = centerY - innerSize/2
            canvas.drawRect(
                innerLeft, innerTop,
                innerLeft + innerSize, innerTop + innerSize,
                whitePaint
            )

            // Draw small black square in center
            val tinySize = innerSize - 20
            val tinyLeft = centerX - tinySize/2
            val tinyTop = centerY - tinySize/2
            canvas.drawRect(
                tinyLeft, tinyTop,
                tinyLeft + tinySize, tinyTop + tinySize,
                blackPaint
            )
        }
    }
}

private fun isInsideCarMask(x: Int, y: Int, mask: Bitmap): Boolean {
    if (x < 0 || x >= mask.width || y < 0 || y >= mask.height) {
        return false
    }

    val pixel = mask.getPixel(x, y)
    // Check if pixel is not transparent (alpha > 0)
    return Color.alpha(pixel) > 0
}


private fun drawBigRLogo(canvas: Canvas, size: Int, carMask: Bitmap) {
    val centerX = size / 2f
    val centerY = size / 2f
    val logoSize = 80f // BIGGER logo size (was 40f)

    // Check if center is available
    if (isInsideCarMask(centerX.toInt(), centerY.toInt(), carMask)) {
        // Draw orange circle background
        val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#FF6B35")
            style = Paint.Style.FILL
        }
        canvas.drawCircle(centerX, centerY, logoSize, bgPaint)

        // White border (thicker for bigger logo)
        val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.WHITE
            style = Paint.Style.STROKE
            strokeWidth = 6f // Thicker border (was 3f)
        }
        canvas.drawCircle(centerX, centerY, logoSize, borderPaint)

        // BIGGER "R" text
        val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.WHITE
            textSize = 56f //
            textAlign = Paint.Align.CENTER
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }

        val textY = centerY - ((textPaint.descent() + textPaint.ascent()) / 2)
        canvas.drawText("R", centerX, textY, textPaint)
    } else {
        // Alternative position if center is not available
        // Try slightly above center
        val altX = centerX
        val altY = centerY - 100f

        if (isInsideCarMask(altX.toInt(), altY.toInt(), carMask)) {
            val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.parseColor("#FF6B35")
                style = Paint.Style.FILL
            }
            canvas.drawCircle(altX, altY, logoSize, bgPaint)

            val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.WHITE
                style = Paint.Style.STROKE
                strokeWidth = 6f
            }
            canvas.drawCircle(altX, altY, logoSize, borderPaint)

            val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.WHITE
                textSize = 56f
                textAlign = Paint.Align.CENTER
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }

            val textY = altY - ((textPaint.descent() + textPaint.ascent()) / 2)
            canvas.drawText("R", altX, textY, textPaint)
        }
    }
}

/* ----------------------------------------------------
   QR GENERATION with maximum error correction
---------------------------------------------------- */

private fun generateQRCode(text: String, size: Int): Bitmap? {
    return try {
        val hints = hashMapOf<EncodeHintType, Any>()
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.Q // Maximum error correction
        hints[EncodeHintType.MARGIN] = 2
        hints[EncodeHintType.CHARACTER_SET] = "UTF-8"

        val bitMatrix = QRCodeWriter().encode(
            text,
            BarcodeFormat.QR_CODE,
            size,
            size,
            hints
        )

        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)

        for (x in 0 until bitMatrix.width) {
            for (y in 0 until bitMatrix.height) {
                bitmap.setPixel(
                    x,
                    y,
                    if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                )
            }
        }
        bitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

/* ----------------------------------------------------
   SAVE TO GALLERY
---------------------------------------------------- */

private fun saveQRCode(context: Context, bitmap: Bitmap) {

    val values = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "ResQX_QR_${System.currentTimeMillis()}.png")
        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(
                MediaStore.Images.Media.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + "/ResQX"
            )
        }
    }

    val resolver = context.contentResolver
    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

    try {
        uri?.let {
            resolver.openOutputStream(it)?.use { stream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                Toast.makeText(context, "QR saved successfully", Toast.LENGTH_SHORT).show()
            }
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Failed to save QR", Toast.LENGTH_SHORT).show()
        e.printStackTrace()
    }
}