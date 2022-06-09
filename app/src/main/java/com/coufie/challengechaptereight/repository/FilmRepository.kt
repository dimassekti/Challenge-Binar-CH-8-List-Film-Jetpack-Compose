@file:Suppress("UnusedImport", "UnusedImport", "UnusedImport", "UnusedImport", "UnusedImport",
    "UnusedImport", "UnusedImport", "UnusedImport", "UnusedImport", "UnusedImport", "UnusedImport",
    "UnusedImport", "UnusedImport", "UnusedImport"
)

package com.coufie.challengechaptereight.repository

import com.coufie.challengechaptereight.api.ApiService
import com.coufie.challengechaptereight.data.GetFilmItem
import com.coufie.challengechaptereight.data.GetUserItem
import javax.inject.Inject


class FilmRepository @Inject constructor(private val filmapi : ApiService){
    suspend fun getFilm() : List<GetFilmItem>{
        return filmapi.getAllNews()
    }

}