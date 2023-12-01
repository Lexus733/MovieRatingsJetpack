package com.example.moviesrating.domain.usecase

import com.example.moviesrating.domain.model.actordetails.EntityActorDetails

interface GetActorDetailsByIdUseCase {

    suspend fun getActorDetail(actorId: String) : EntityActorDetails
}