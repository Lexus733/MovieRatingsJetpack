package com.example.moviesrating.domain.model.actordetails

import com.example.moviesrating.data.remote.model.actordetails.DataEntityActorDetails

class EntityActorDetailsMapper {

    fun adapt(dataEntity: DataEntityActorDetails) : EntityActorDetails {
        return EntityActorDetails(
            image_url = dataEntity.results.image_url,
            name = dataEntity.results.name ?: "empty"
        )
    }
}