package com.uvg.ejercicioslabs.ejercicios.firebase.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.uvg.ejercicioslabs.ejercicios.firebase.domain.repository.LoginRepository
import kotlinx.coroutines.tasks.await

class FirebaseLoginRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val crashlytics: FirebaseCrashlytics = FirebaseCrashlytics.getInstance()
): LoginRepository {
    override suspend fun login(email: String, password: String): Boolean {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            result.user != null
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            println(e)
            crashlytics.recordException(e)
            false
        } catch (e: FirebaseAuthInvalidUserException) {
            println(e)
            crashlytics.recordException(e)
            false
        } catch (e: Exception) {
            println(e)
            crashlytics.recordException(e)
            false
        }
    }

    override suspend fun createUser(email: String, password: String): Boolean {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result.user != null
        } catch (e: FirebaseAuthWeakPasswordException) {
            println("Weak password: ${e.message}")
            crashlytics.recordException(e)
            false
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            println("Invalid email: ${e.message}")
            crashlytics.recordException(e)
            false
        } catch (e: FirebaseAuthUserCollisionException) {
            println("Email already in use: ${e.message}")
            crashlytics.recordException(e)
            false
        } catch (e: Exception) {
            println("Unknown error: ${e.message}")
            crashlytics.recordException(e)
            false
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    override suspend fun logout() {
        auth.signOut()
    }
}