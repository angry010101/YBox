package com.yakymovych.simon.everywhere.ui.login

import androidx.lifecycle.MutableLiveData
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.yakymovych.simon.everywhere.data.SignInError
import com.yakymovych.simon.everywhere.data.repository.Repository
import com.yakymovych.simon.everywhere.ui.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class LoginViewModel(var repository: Repository) : BaseViewModel(){
    var editTextEmailValue = "sample@site.com"
    var editTextPasswordValue = "0123456"
    var doRegister = MutableLiveData<Boolean>(false)
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
        doRegister.value = isChecked

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


    private fun signIn(view: View){
        repository.login(editTextEmailValue, editTextPasswordValue)
                .subscribeBy(
                        onSuccess = {
                            goToMainActivity.value = true

                            Timber.d("onLogInClick.onSuccess")
                        },
                        onError = { throwable ->
                            Timber.d("onLogInClick.onError")
                            (view as Button).isEnabled = true

                            run {
                                if (throwable is HttpException) {
                                    val e = throwable as HttpException
                                    val json = e.response().errorBody().string()
                                    val error = Gson().fromJson(json,SignInError::class.java)
                                    val msg = error?.fields?.email ?: ""
                                    toastMessage.value = "ERROR HAPPENED: " + error.message + " " + msg
                                }
                                else
                                    toastMessage.value = throwable.message
                            }
                        })
    }

    private fun signUp(view: Button) {
        repository.register(editTextEmailValue, editTextPasswordValue)
                .subscribeBy(
                        onSuccess = {
                            goToMainActivity.value = true

                            Timber.d("onLogInClick.onSuccess")
                        },
                        onError = { throwable ->
                            Timber.d("onLogInClick.onError")
                            (view as Button).isEnabled = true

                            run {
                                if (throwable is HttpException) {
                                    val e = throwable as HttpException
                                    val json = e.response().errorBody().string()
                                    val error = Gson().fromJson(json,SignInError::class.java)
                                    toastMessage.value = "ERROR HAPPENED: " + error.message + " " + error.fields.email
                                }
                                else
                                    toastMessage.value = throwable.message
                            }
                        })
    }
    fun onLogInClick(view: View) {
        (view as Button).isEnabled = false
        if ((validateEmail() && validatePass())) {
            Timber.d("LOGINING: " + editTextEmailValue + " " + editTextPasswordValue)
            if (doRegister.value!!) signUp(view)
            else signIn(view)
        }
        else {
            view.isEnabled = true
        }
    }


    private fun validateEmail(): Boolean {
        return if (!TextUtils.isEmpty(editTextEmailValue) && Patterns.EMAIL_ADDRESS.matcher(editTextEmailValue).matches()) {
            true;
        } else {
            toastMessage.value = "Wrong email address"
            false
        }
    }

    private fun validatePass(): Boolean {
        return if (editTextPasswordValue.length > 0) {
            true
        } else {
            toastMessage.value = "Wrong password"
            false
        }
    }


}