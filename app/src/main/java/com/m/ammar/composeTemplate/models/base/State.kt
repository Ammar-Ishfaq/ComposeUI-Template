package com.m.ammar.composeTemplate.models.base

sealed class State<out T> {

    data class Success<T>(val wrapperData: T?) : State<T>()

    data class Error(val responseError: ResponseError) : State<Nothing>()

    companion object {

        /**
         * Returns [State.Success] instance.
         * @param data Data to emit with status.
         */
        fun <T> success(data: T) = Success(data)

        /**
         * Returns [State.Error] instance.
         * @param message Description of failure.
         */
        fun <T> error(responseError: ResponseError) =
            Error(responseError)
    }
}

