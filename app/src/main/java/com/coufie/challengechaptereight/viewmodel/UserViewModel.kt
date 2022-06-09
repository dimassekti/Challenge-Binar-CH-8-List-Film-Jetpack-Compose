package com.coufie.challengechaptereight.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coufie.challengechaptereight.api.ApiService
import com.coufie.challengechaptereight.data.GetUserItem
import com.coufie.challengechaptereight.data.PostUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel  @Inject constructor(private val api: ApiService): ViewModel() {
    private val userState = MutableStateFlow(emptyList<GetUserItem>())
    val dataUserState : StateFlow<List<GetUserItem>> get() = userState
    private val apii = api

    init {
        viewModelScope.launch {
            val user = apii.getAllUser()
            userState.value = user
        }
    }

    fun registerUser(postUser: PostUser): Boolean {
        var messageResponse = false
        viewModelScope.launch {
            api.addUser(postUser)
                .enqueue(object : Callback<GetUserItem> {
                    override fun onResponse(
                        call: Call<GetUserItem>,
                        response: Response<GetUserItem>
                    ) {
                        messageResponse = response.isSuccessful                    }

                    override fun onFailure(call: Call<GetUserItem>, t: Throwable) {
                        messageResponse = false
                    }


                })
        }
        return messageResponse
    }
}