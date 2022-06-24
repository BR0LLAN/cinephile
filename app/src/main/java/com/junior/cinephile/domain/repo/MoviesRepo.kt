package com.junior.cinephile.domain.repo

import com.junior.cinephile.entities.Response

interface MoviesRepo {
    suspend fun getMovies(offset: Int): Response
}