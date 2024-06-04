package com.m.ammar.composeTemplate.models.base


data class ResponseError(
   var statusCode: Int? = null,
   var message: String? = null

) : java.io.Serializable