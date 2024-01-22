package com.m.ammar.composeTemplate.data

import com.m.ammar.composeTemplate.R
import com.m.ammar.composeTemplate.data.managers.DataManager
import com.m.ammar.composeTemplate.utility.NetworkUtils
import timber.log.Timber

open class BaseRepository(val dataManager: DataManager) {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>): Result<T> {
        return try {
            verifySessionAndMakeApiCall(call)
        } catch (e: Exception) {
            Timber.e("ApiException => ${e.stackTrace}")
            Result.failure(e)
        }
    }

    protected open fun isNetworkNotAvailable(): Boolean {
        return !NetworkUtils.isNetworkConnected(dataManager.getResourceManager().getContext())
    }

    private suspend fun <T : Any> verifySessionAndMakeApiCall(
        call: suspend () -> Result<T>
    ): Result<T> {
        if (isNetworkNotAvailable()) {
            return Result.failure(
                Throwable(
                    dataManager.getResourceManager().getString(R.string.internet_error)
                )
            )
        }

        return apiOutput(call)
    }


    private suspend fun <T : Any> apiOutput(
        call: suspend () -> Result<T>,
    ): Result<T> {
        return try {
            val result = call.invoke()

            if (result.isSuccess) {
                result
            } else {
                val throwable = result.exceptionOrNull()

                // Parse error response
                Result.failure(
                    Throwable(
                        throwable?.message ?: "Failed to parse error response"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(Throwable("Failed to make API call: ${e.message}"))
        }
    }

}
