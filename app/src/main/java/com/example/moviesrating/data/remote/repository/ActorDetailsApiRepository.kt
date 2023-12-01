package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.domain.model.actordetails.EntityActorDetails

interface ActorDetailsApiRepository {
    suspend fun getActorDetails(actorId: String): EntityActorDetails
}