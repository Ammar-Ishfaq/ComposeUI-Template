package com.m.ammar.composeTemplate.models.base

import com.google.gson.annotations.SerializedName

data class ResponseError(
    @SerializedName("statusCode") var statusCode: Int? = null,

    @SerializedName("message") var message: String? = null

) : java.io.Serializable