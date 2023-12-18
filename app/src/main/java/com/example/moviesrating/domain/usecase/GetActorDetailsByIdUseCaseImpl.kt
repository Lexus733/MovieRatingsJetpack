package com.example.moviesrating.domain.usecase

import com.example.moviesrating.data.remote.repository.ActorDetailsApiRepository
import com.example.moviesrating.domain.model.actordetails.EntityActorDetails
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetActorDetailsByIdUseCaseImpl @Inject constructor(
    private val actorDetailsApiRepository: ActorDetailsApiRepository
) : GetActorDetailsByIdUseCase {

    override suspend fun getActorDetail(actorId: String): EntityActorDetails = coroutineScope {
        actorDetailsApiRepository.getActorDetails(actorId = actorId)
    }
}
