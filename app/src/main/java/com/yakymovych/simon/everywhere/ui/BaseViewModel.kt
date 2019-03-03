package com.yakymovych.simon.everywhere.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yakymovych.simon.everywhere.data.repository.BaseRepository


import com.yakymovych.simon.everywhere.data.repository.Repository

abstract class BaseViewModel() : ViewModel(){
    var toastMessage = MutableLiveData<String>()
}