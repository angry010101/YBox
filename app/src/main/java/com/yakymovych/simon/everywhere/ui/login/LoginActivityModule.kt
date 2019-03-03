package com.yakymovych.simon.everywhere.ui.login

import com.yakymovych.simon.everywhere.data.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {
    @Provides
    fun provideViewModel(repository: Repository) = LoginViewModel(repository)
}