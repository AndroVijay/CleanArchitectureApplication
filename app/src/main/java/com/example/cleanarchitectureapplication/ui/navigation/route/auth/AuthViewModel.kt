package com.example.cleanarchitectureapplication.ui.navigation.route.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    val authState = authRepository.authState.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), authRepository.authState.value)
    private val _authResult = MutableStateFlow<Result<FirebaseUser?>?>(null)
    val authResult: StateFlow<Result<FirebaseUser?>?> get() = _authResult

    fun signIn(email: String, password: String) {

        viewModelScope.launch {


                val result = authRepository.signIn(email, password)
                _authResult.value = result



        }
    }



    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            authRepository.signUp(email, password)
        }
    }

    fun isUserLoggedIn(): Boolean {
        return authRepository.isUserLoggedIn()
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
        }
    }
}