package com.coufie.challengechaptereight.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coufie.challengechaptereight.repository.FilmRepository
import com.coufie.challengechaptereight.data.GetFilmItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(private val api : FilmRepository) : ViewModel() {

    private val filmState = MutableStateFlow(emptyList<GetFilmItem>())
    val dataState : StateFlow<List<GetFilmItem>> get() = filmState

    init {
        viewModelScope.launch {

            val film = api.getFilm()
            filmState.value = film
        }
    }
}