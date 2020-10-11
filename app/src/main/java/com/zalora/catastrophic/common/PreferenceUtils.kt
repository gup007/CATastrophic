package com.zalora.catastrophic.common

import android.content.Context
import android.content.SharedPreferences


class PreferenceUtils constructor(var context: Context) {

    private var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(KEY_APP_PREFERENCE, Context.MODE_PRIVATE)
    }


    fun putString(key: String?, value: String?): Boolean {
        if (key == null) return false
        val editor = preferences.edit()
        editor.putString(key, value)
        return editor.commit()
    }

    fun getString(key: String?): String? {
        return if (key == null) null else getString(key, null)
    }

    fun getString(key: String?, defaultValue: String?): String? {
        if (key == null) return null
        return preferences.getString(key, defaultValue)
    }

    fun putLong(key: String?, value: Long): Boolean {
        if (key == null) return false
        val editor = preferences.edit()
        editor.putLong(key, value)
        return editor.commit()
    }

    fun putBoolean(key: String?, value: Int): Boolean {
        if (key == null) return false
        val editor = preferences.edit()
        editor.putInt(key, value)
        return editor.commit()
    }

    fun putBoolean(key: String?, value: Boolean): Boolean {
        if (key == null) return false
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        return editor.commit()
    }

    private fun getLong(key: String?, defaultValue: Long): Long {
        if (key == null) return defaultValue
        return preferences.getLong(key, defaultValue)
    }


    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        if (key == null) return defaultValue
        return preferences.getBoolean(key, defaultValue)
    }

    fun getLong(key: String?): Long {
        return if (key == null) 0 else getLong(key, 0)
    }


    fun getInt(key: String?, defaultValue: Int): Int {
        if (key == null) return defaultValue
        return preferences.getInt(key, defaultValue)
    }

    fun putInt(key: String?): Int {
        return if (key == null) 0 else getInt(key, 0)
    }

    fun putInt(key: String?, value: Int): Boolean {
        if (key == null) return false
        val editor = preferences.edit()
        editor.putInt(key, value)
        return editor.commit()
    }

    companion object {
        private const val KEY_APP_PREFERENCE = "PREFERENCES"
    }
}
