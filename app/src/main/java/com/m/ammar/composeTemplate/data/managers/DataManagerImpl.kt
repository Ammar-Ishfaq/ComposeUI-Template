package com.m.ammar.composeTemplate.data.managers

import com.m.ammar.composeTemplate.data.RetrofitInterface
import com.m.ammar.composeTemplate.prefs.PreferencesHelper
import com.m.ammar.composeTemplate.prefs.PreferencesHelperImpl
import com.m.ammar.composeTemplate.utility.ResourceProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManagerImpl @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val appService: RetrofitInterface,
    private val preferencesHelper: PreferencesHelper,
    private val preferencesHelperImpl: PreferencesHelperImpl
) : DataManager {

    override fun getResourceManager(): ResourceProvider {
        return resourceProvider
    }

    override fun getApiHelper(): RetrofitInterface {
        return appService
    }

    override fun getPreferencesHelper(): PreferencesHelper {
        return preferencesHelper
    }

    override fun getPreferencesHelperImpl(): PreferencesHelperImpl {
        return preferencesHelperImpl
    }

}