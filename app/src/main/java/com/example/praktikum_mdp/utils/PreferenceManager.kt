package com.example.praktikum_mdp.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

// ✅ Deklarasi DataStore harus di luar class/object
val Context.dataStore by preferencesDataStore(name = "user_prefs")

object PreferenceManager {

    private val TOKEN_KEY = stringPreferencesKey("auth_token")

    suspend fun setToken(context: Context, token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    suspend fun getToken(context: Context): String? {
        val prefs = context.dataStore.data.first()
        return prefs[TOKEN_KEY]
    }
}
