package com.example.cleanarchitectureapplication.ui.navigation.route.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableStateFlow<FirebaseUser?>(auth.currentUser)
    val authState: StateFlow<FirebaseUser?> get() = _authState

    suspend fun signIn(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            _authState.value = result.user
            Result.success(result.user)
        } catch (e: Exception) {
            // Handle case where user is not registered
            if (e is FirebaseAuthInvalidUserException) {
                Result.failure(Exception("User with this email is not registered"))
            } else if (e is FirebaseAuthInvalidCredentialsException) {
                Result.failure(Exception("Invalid credentials. Please check your email and password."))
            } else {
                Result.failure(e)
            }
        }
    }




    suspend fun signOut() {
        auth.signOut()
        _authState.value = null
    }

    suspend fun signUp(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            _authState.value = result.user
            Result.success(result.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}