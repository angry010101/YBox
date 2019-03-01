package com.yakymovych.simon.everywhere.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import com.yakymovych.simon.everywhere.data.Repository

abstract class BaseViewModel(var repository: Repository) : ViewModel(){
    var toastMessage = MutableLiveData<String>()
}