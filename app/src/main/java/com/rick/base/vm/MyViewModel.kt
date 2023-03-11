package com.rick.base.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val text = MutableLiveData("Hello Kitty")
}