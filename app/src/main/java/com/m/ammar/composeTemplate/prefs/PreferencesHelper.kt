package com.m.ammar.composeTemplate.prefs
interface PreferencesHelper {
    fun clearPreferences()

    fun clearKey(key: String)

    fun putString(key: String, value: String)

    fun getString(key: String): String

    fun putLong(key: String, value: Long)

    fun getLong(key: String): Long

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String): Boolean
}