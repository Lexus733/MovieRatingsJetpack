package com.example.moviesrating.domain.interactor

import com.example.moviesrating.domain.model.actordetails.EntityActorDetails
import com.example.moviesrating.domain.model.moviedetail.EntityGen
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.domain.usecase.GetActorDetailsByIdUseCase
import com.example.moviesrating.domain.usecase.GetMovieCastByIdUseCase
import com.example.moviesrating.domain.usecase.GetPopularFilmsDataUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class HomeScreenInteractor @Inject constructor(
    private val getPopularFilmsDataUseCase: GetPopularFilmsDataUseCase,
    private val getMovieCastByIdUseCase: GetMovieCastByIdUseCase,
    private val getActorDetailsByIdUseCase: GetActorDetailsByIdUseCase
) {

    suspend fun getData(): HomeInteractorModel = coroutineScope {
        val popularFilms = async { getPopularFilmsDataUseCase.getPopularMovies() }.await()
        val gens = getGenMap(popularFilms)
        val result = arrayListOf<EntityMovieDetail>()
        popularFilms.map { movieDetail ->
            val castList =
                async { getMovieCastByIdUseCase.getMovieCast(movieDetail.imdb_id) }.await()
            val actorList = arrayListOf<EntityActorDetails>()
            castList.imdbIds.map { imdbId ->
                actorList.add(async { getActorDetailsByIdUseCase.getActorDetail(imdbId) }.await())
            }
            result.add(
                movieDetail.copy(
                    castList = actorList
                )
            )
        }

        return@coroutineScope HomeInteractorModel(result, gens)
    }

    private fun getGenMap(success: List<EntityMovieDetail>): HashMap<EntityGen, List<EntityMovieDetail>> {
        val genSet = mutableSetOf<EntityGen>()
        val hashMap = hashMapOf<EntityGen, List<EntityMovieDetail>>()

        success.map { genSet.addAll(it.gen) }

        genSet.map { gen ->
            hashMap.put(gen, success.filter { it.gen.contains(gen) })
        }

        return hashMap
    }
}

data class HomeInteractorModel(
    val list: List<EntityMovieDetail>,
    val gens: HashMap<EntityGen, List<EntityMovieDetail>>
)
