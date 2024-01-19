package com.m.ammar.composeTemplate.data.managers

import com.m.ammar.composeTemplate.data.ApiInterface
import com.m.ammar.composeTemplate.prefs.PreferencesHelper
import com.m.ammar.composeTemplate.prefs.PreferencesHelperImpl
import com.m.ammar.composeTemplate.utility.ResourceProvider

interface DataManager {
    fun getApiHelper(): ApiInterface
    fun getResourceManager(): ResourceProvider
    fun getPreferencesHelper(): PreferencesHelper
    fun getPreferencesHelperImpl(): PreferencesHelperImpl

}