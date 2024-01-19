package com.m.ammar.composeTemplate.data

import com.m.ammar.composeTemplate.models.ResistorObject
import retrofit2.http.*

interface RetrofitInterface {

    @GET("ResistanceGuide.json")
    suspend fun getList(): List<ResistorObject>


}