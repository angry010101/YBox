package com.yakymovych.simon.everywhere.ui.login

import androidx.lifecycle.MutableLiveData
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import com.yakymovych.simon.everywhere.data.Repository
import com.yakymovych.simon.everywhere.ui.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import java.io.IOException

class LoginViewModel(repository: Repository) : BaseViewModel(repository){
    var editTextEmailValue = "sample@site.com"
    var editTextPasswordValue = "0123456"
    var doRegister = false
    var goToMainActivity = MutableLiveData<Boolean>()

    fun getUserMailEditTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                editTextEmailValue = charSequence.toString()
            }

            override fun afterTextChanged(editable: Editable) {
            }
        }
    }

    fun onLoginRegisterSwitchChange(buttonView: CompoundButton, isChecked: Boolean){
        doRegister = isChecked
    }

    fun getUserPassEditTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                editTextPasswordValue = charSequence.toString()
            }

            override fun afterTextChanged(editable: Editable) {
            }
        }
    }

    fun onLogInClick(view: View) {
        (view as Button).isEnabled = false
        if ((validateEmail() && validatePass())) {
            Timber.d("LOGINING: " + editTextEmailValue + " " + editTextPasswordValue)
            repository.login(editTextEmailValue, editTextPasswordValue,doRegister)
                    .subscribeBy(
                            onSuccess = {
                                Timber.d("onLogInClick.onSuccess")
                                goToMainActivity.value = true
                            },
                            onError = { throwable ->
                                Timber.d("onLogInClick.onError")
                                (view as Button).isEnabled = true
                                run {
                                    if (throwable is IOException)
                                        toastMessage.value = "Error signing in"
                                    else
                                        toastMessage.value = throwable.message
                                }
                            })
        }
        else {
            view.isEnabled = true
        }
    }


    private fun validateEmail(): Boolean {
//        return if (!TextUtils.isEmpty(editTextUserMailValue) && Patterns.EMAIL_ADDRESS.matcher(editTextUserMailValue).matches()) {
//            true;
//        } else {
//            toastMessage.value = "Wrong email"
//            false
//        }
        return true
    }

    private fun validatePass(): Boolean {
//        return if (editTextUserPassValue.length > 0) {
//            true
//        } else {
//            toastMessage.value = "Wrong password"
//            false
//        }
        return true
    }


}