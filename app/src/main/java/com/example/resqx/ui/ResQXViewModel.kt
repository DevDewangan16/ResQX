package com.example.resqx.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.resqx.ui.data.Content
import com.example.resqx.ui.data.DataBase
import com.example.resqx.ui.data.GeminiRequest
import com.example.resqx.ui.data.Part
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ResQXViewModel(application:Application):AndroidViewModel(application){
    private val _name=MutableStateFlow<String>("")
    val name :MutableStateFlow<String>get() = _name

    private val _email=MutableStateFlow<String>("")
    val email:MutableStateFlow<String>get() = _email

    private val _password=MutableStateFlow<String>("")
    val password:MutableStateFlow<String>get() = _password

    private val _confirmPass=MutableStateFlow<String>("")
    val confirmPass:MutableStateFlow<String>get() = _confirmPass

    private val _user=MutableStateFlow<FirebaseUser?>(null)
    val user:MutableStateFlow<FirebaseUser?>get() = _user

    private val _vehicleNo=MutableStateFlow<String>("")
    val vehicleNo:MutableStateFlow<String>get() = _vehicleNo

    private val _ownerName=MutableStateFlow<String>("")
    val ownerName:MutableStateFlow<String>get() = _ownerName

    private val _contact1=MutableStateFlow<String>("")
    val contact1:MutableStateFlow<String>get() = _contact1

    private val _contact2=MutableStateFlow<String>("")
    val contact2:MutableStateFlow<String>get() = _contact2

    private val _bloodGroup=MutableStateFlow<String>("")
    val bloodGroup:MutableStateFlow<String>get() = _bloodGroup

    private val _allergies=MutableStateFlow<String>("")
    val allergies:MutableStateFlow<String>get() = _allergies

    private val _chronicCondition=MutableStateFlow<String>("")
    val chronicCondition:MutableStateFlow<String>get() = _chronicCondition


    private val _databaseList=MutableStateFlow<List<DataBase>>(emptyList())
    val databaseList: StateFlow<List<DataBase>>
        get() = _databaseList.asStateFlow()

    val database= Firebase.database
    val myRef = database.getReference("users/${auth.currentUser?.uid}/Vehicle")

    private val _response = MutableStateFlow("Ask something...")
    val response = _response.asStateFlow()

    fun setUser(user: FirebaseUser){
        _user.value=user
    }

    fun setName(name:String){
        _name.value=name
    }

    fun setEmail(email:String){
        _email.value=email
    }

    fun setPassword(password:String){
        _password.value=password
    }

    fun setConfirmPass(confirm:String){
        _confirmPass.value=confirm
    }

    fun setVehicleNo(vehicleNumber:String){
        _vehicleNo.value=vehicleNumber
    }

    fun setOwnerName(ownerName:String){
        _ownerName.value=ownerName
    }

    fun setContact1(contact1:String){
        _contact1.value=contact1
    }

    fun setContact2(contact2:String){
        _contact2.value=contact2
    }

    fun setBloodGroup(bloodGroup:String){
        _bloodGroup.value=bloodGroup
    }

    fun setAllergies(allergies:String){
        _allergies.value=allergies
    }

    fun setChronicCondition(chronic:String){
        _chronicCondition.value=chronic
    }

    fun addToDatabase(item:DataBase){
        myRef.push().setValue(item)
    }
    private val _vehicleInfo = MutableStateFlow<String>("")
    val vehicleInfo: MutableStateFlow<String> get() = _vehicleInfo

    private val _vehicleDetails = MutableStateFlow<DataBase?>(null)
    val vehicleDetails: MutableStateFlow<DataBase?> get() = _vehicleDetails

    fun setVehicleInfo(info: String) {
        _vehicleInfo.value = info
    }

    fun fetchVehicleDetails(vehicleNo: String) {
        myRef.orderByChild("vehicleNo").equalTo(vehicleNo).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snapshot in dataSnapshot.children) {
                        val vehicleDetails = snapshot.getValue(DataBase::class.java)
                        _vehicleDetails.value = vehicleDetails
                        return
                    }
                } else {
                    _vehicleDetails.value = null
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors.
                _vehicleDetails.value = null
            }
        })
    }

    private val apiKey = "AIzaSyDbGhLvg47UU1tY7O0LS7dbeho1dFEuvPk"

    //used for fetching the response with the help of Gemini of the Text to Text based
    fun fetchResponse(prompt: String) {
        viewModelScope.launch {
            try {
                val request = GeminiRequest(
                    contents = listOf(
                        Content(parts = listOf(Part(text = prompt)))  // ✅ Correct format
                    )
                )

                val result = RetrofitClient.instance.getGeminiResponse(apiKey, request)

                // ✅ Correctly extracting response text
                val responseText = result.candidates?.getOrNull(0)?.content?.parts?.getOrNull(0)?.text ?: "No response"

                _response.value = responseText

//                val newEntry = RequestResponse(prompt,responseText)
//                _historyList.value = _historyList.value + newEntry


            } catch (e: Exception) {
                _response.value = "Error: ${e.message}"
            }
        }
    }
}