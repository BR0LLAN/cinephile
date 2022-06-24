package com.junior.cinephile.domain.useСase

import com.junior.cinephile.domain.repo.MoviesRepo
import com.junior.cinephile.entities.Response
import com.junior.cinephile.entities.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetDataMovies @Inject constructor(
    private val mRepo: MoviesRepo
    ) {

    operator fun invoke(offset: Int): Flow<ResultState<Response>> = flow {
       try {
           emit(ResultState.Loading)
           val movies = mRepo.getMovies(offset)
           emit(ResultState.Success(movies))
       }
       catch (e: Exception){
           emit(ResultState.Error(e.message!!))
       }
    }
}