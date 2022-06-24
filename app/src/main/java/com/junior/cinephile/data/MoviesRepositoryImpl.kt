package com.junior.cinephile.data

import com.junior.cinephile.data.network.Api
import com.junior.cinephile.domain.repo.MoviesRepo
import com.junior.cinephile.entities.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor (private val api: Api): MoviesRepo {
    override suspend fun getMovies(offset: Int): Response {
        return api.getDataMovies(offset)
    }
}