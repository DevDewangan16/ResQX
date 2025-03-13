package com.example.resqx.ui

import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.resqx.ui.data.SaveRecord
import io.noties.markwon.Markwon

@Composable
fun SaveScreen(resQXViewModel: ResQXViewModel,navController: NavController) {
    val saveRecord: List<SaveRecord> by resQXViewModel.saveRecord.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Saved Information")

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(saveRecord) { entry ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    //elevation =4.dp,
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFEE1B6)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Request: ${entry.request}")
//                        Text(text = "Response: ${entry.response}")
                        MarkdownText2(response = "Response: ${entry.response} ")
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Button(onClick = {
                                resQXViewModel.removeFromCart(entry)
                            },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF2F2F2F)
                                )) {
                                Text(text = "Remove")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2F2F2F)
            )) {
            Text("Back to Home")
        }
    }
}

@Composable
fun MarkdownText2(response: String) {
    val context = LocalContext.current
    val markwon = remember { Markwon.create(context) }
    val spanned = remember(response) { markwon.toMarkdown(response) }

    AndroidView(
        factory = { TextView(it).apply { movementMethod = LinkMovementMethod.getInstance() } },
        update = { it.text = spanned }
    )
}