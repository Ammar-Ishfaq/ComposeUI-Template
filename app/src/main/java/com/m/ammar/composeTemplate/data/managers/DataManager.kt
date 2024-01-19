package com.m.ammar.composeTemplate.data.managers

import com.m.ammar.composeTemplate.data.RetrofitInterface
import com.m.ammar.composeTemplate.prefs.PreferencesHelper
import com.m.ammar.composeTemplate.prefs.PreferencesHelperImpl
import com.m.ammar.composeTemplate.utility.ResourceProvider

interface DataManager {
    fun getApiHelper(): RetrofitInterface
    fun getResourceManager(): ResourceProvider
    fun getPreferencesHelper(): PreferencesHelper
    fun getPreferencesHelperImpl(): PreferencesHelperImpl

}