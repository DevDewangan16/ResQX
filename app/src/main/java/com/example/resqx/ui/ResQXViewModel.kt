package com.example.resqx.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow


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
}