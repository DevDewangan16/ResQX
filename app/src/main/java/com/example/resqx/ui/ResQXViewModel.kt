package com.example.resqx.ui

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.resqx.ui.data.Content
import com.example.resqx.ui.data.DataBase
import com.example.resqx.ui.data.GeminiRequest
import com.example.resqx.ui.data.Part
import com.example.resqx.ui.data.RequestResponse
import com.example.resqx.ui.data.SaveRecord
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


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
    val myRef2=database.getReference("users/${auth.currentUser?.uid}/SavedRecords")

    private val _response = MutableStateFlow("Ask something...")
    val response = _response.asStateFlow()

    private val _historyList = MutableStateFlow<List<RequestResponse>>(emptyList())
    val historyList: StateFlow<List<RequestResponse>> = _historyList

    private val _saveRecord=MutableStateFlow<List<SaveRecord>>(emptyList())
    val saveRecord:StateFlow<List<SaveRecord>>get()=_saveRecord.asStateFlow()

    private val Context.datastore : DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore("SavedRecords")
    private val cartItemsKey= stringPreferencesKey("SavedRecords_items")
    private val context=application.applicationContext

    lateinit var screenJob: Job
    lateinit var InternetJob: Job

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
    fun addSavedDatabase(item:SaveRecord){
        myRef2.push().setValue(item)
    }

    private suspend fun saveCartItemsToDataStore(){
        context.datastore.edit { preferences->
            preferences[cartItemsKey]= Json.encodeToString(_saveRecord.value)
        }
    }

    private suspend fun loadCartItemsFromDataStore(){
        val fullData=context.datastore.data.first()
        val cartItemsJson=fullData[cartItemsKey]
        if (!cartItemsJson.isNullOrEmpty()){
            _saveRecord.value= Json.decodeFromString(cartItemsJson)
        }
    }

    fun getSavedItems(){
        InternetJob=viewModelScope.launch {
            try {
                loadCartItemsFromDataStore()
            }catch (exception:Exception){
//                toggleVisibility()
//                screenJob.cancel()
            }
        }
    }
    fun addToCart(item:SaveRecord){
        _saveRecord.value = _saveRecord.value + item
        viewModelScope.launch {
            saveCartItemsToDataStore()
        }
    }

    fun fillCartItems(){
        // Read from the database
        myRef2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                _saveRecord.value= emptyList()
                for (childSnapshot in dataSnapshot.children){
                    val item=childSnapshot.getValue(SaveRecord::class.java)
                    item?.let {
//                        val newItem=it
//                        addToCart(newItem)
                        _saveRecord.value = _saveRecord.value + it

                    }
                }
//                setLoading(false)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun removeFromCart(oldItem:SaveRecord){
        /* _cartItems.value = _cartItems.value - item
         viewModelScope.launch {
             saveCartItemsToDataStore()
         }*/ //this code is for to remove item from the cart only not database
        myRef2.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //_databaseList.value= emptyList()
                for (childSnapshot in dataSnapshot.children){
                    var itemRemoved=false
                    val item=childSnapshot.getValue(SaveRecord::class.java)
                    item?.let {
                        if (oldItem.request ==it.request && oldItem.response == it.response){
                            childSnapshot.ref.removeValue()
                            itemRemoved=true
                        }
                    }
                    if(itemRemoved) break
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
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

                val newEntry = RequestResponse(prompt,responseText)
                _historyList.value = _historyList.value + newEntry


            } catch (e: Exception) {
                _response.value = "Error: ${e.message}"
            }
        }
    }

    init {
        getSavedItems()
        fillCartItems()
    }
}