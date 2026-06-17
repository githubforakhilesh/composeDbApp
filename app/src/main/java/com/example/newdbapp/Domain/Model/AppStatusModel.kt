package com.example.newdbapp.Domain.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppStatusModel(
    var msg: String? = null,
    var state: Int? = null,
    var status: String? = null,
    var tutorial: TutorialModel? = null
)

@Serializable
data class TutorialModel(
    var images: List<TutorialContentModel?>? = null,
    var videos: List<TutorialContentModel?>? = null
)

@Serializable
data class TutorialContentModel(
    var src: String? = null,
    var alt: String? = null,
    var title: String? = null
)

