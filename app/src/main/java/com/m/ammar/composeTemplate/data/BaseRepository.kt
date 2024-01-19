package com.m.ammar.composeTemplate.data

import com.m.ammar.composeTemplate.R
import com.m.ammar.composeTemplate.data.managers.DataManager
import com.m.ammar.composeTemplate.extension.wrapWithEvent
import com.m.ammar.composeTemplate.utility.Constants
import com.m.ammar.composeTemplate.utility.NetworkUtils
import com.m.ammar.composeTemplate.utility.RepoConstants
import retrofit2.HttpException
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
                Timber.e("API_RESPONSE_ERR => ${throwable?.message}")


                // Check for unauthorized error
                if (throwable is HttpException && throwable.code() == 401) {
//                    val stream = throwable.response()?.errorBody()?.byteStream()
//                    val jsonContent =
//                        InputStreamReader(stream).use { it.readText() } // Read the JSON content into a String
//                    val gson = Gson()
//                    Timber.e("API_RESPONSE_ERR => $jsonContent")

                    // Handle unauthorized error, e.g., refresh tokens or log out the user
                    RepoConstants._sessionExpire.postValue(Constants.SESSION_EXPIRE.wrapWithEvent())
                }

                Result.failure(Throwable("Failed to parse error response."))
            }
        } catch (e: Exception) {
            Result.failure(Throwable("Failed to make API call: ${e.message}"))
        }
    }

}
