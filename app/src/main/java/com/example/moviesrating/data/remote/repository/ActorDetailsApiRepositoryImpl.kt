package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.di.IoDispatcher
import com.example.moviesrating.domain.model.actordetails.EntityActorDetails
import com.example.moviesrating.domain.model.actordetails.EntityActorDetailsMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActorDetailsApiRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val entityActorDetailsMapper: EntityActorDetailsMapper
) : ActorDetailsApiRepository {

    override suspend fun getActorDetails(actorId: String): EntityActorDetails =
        withContext(ioDispatcher) {
            entityActorDetailsMapper.adapt(api.getDetailsByActorId(actorId))
        }
}