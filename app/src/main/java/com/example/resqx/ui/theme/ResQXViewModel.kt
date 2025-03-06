package com.example.resqx.ui.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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
}