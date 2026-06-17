package com.example.newdbapp.Data.Dto

import com.example.newdbapp.Domain.Model.AppStatusModel
import com.example.newdbapp.Domain.Model.TutorialContentModel
import com.example.newdbapp.Domain.Model.TutorialModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppStatusResponseDto(
    @SerialName("msg")
    var msg: String? = null,
    @SerialName("state")
    var state: Int? = null,
    @SerialName("status")
    var status: String? = null,
    @SerialName("tutorial")
    var tutorial: TutorialModelDto? = null
){
    fun toModel(): AppStatusModel {
        return AppStatusModel(
            msg = this.msg,
            state = this.state,
            status = this.status,
            tutorial = this.tutorial?.toModel()
        )
    }
}

@Serializable
data class TutorialModelDto(
    @SerialName("images")
    var images: List<TutorialContentModelDto?>? = null,
    @SerialName("videos")
    var videos: List<TutorialContentModelDto?>? = null
){
    fun toModel(): TutorialModel{
        return TutorialModel(
            images = this.images?.map { it?.toModel() },
            videos = this.videos?.map { it?.toModel() }
        )
    }
}

@Serializable
data class TutorialContentModelDto(
    @SerialName("src")
    var src: String? = null,
    @SerialName("alt")
    var alt: String? = null,
    @SerialName("title")
    var title: String? = null
){
    fun toModel(): TutorialContentModel{
        return TutorialContentModel(
            src = this.src,
            alt = this.alt,
            title = this.title
        )
    }
}
