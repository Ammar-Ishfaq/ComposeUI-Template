package com.m.ammar.composeTemplate.utility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.m.ammar.composeTemplate.extension.Event

object RepoConstants {
    //for the overall session Expiring
    val _sessionExpire = MutableLiveData<Event<String>>()
    val sessionExpire: LiveData<Event<String>> = _sessionExpire

    //for the overall session Expiring
    val _onUploadRefresh = MutableLiveData<Event<Unit>>()
    val onUploadRefresh: LiveData<Event<Unit>> = _onUploadRefresh


}