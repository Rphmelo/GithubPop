package com.rphmelo.data.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GitHubUserPayload (
    @SerializedName("id") val id: Long?,
    @SerializedName("login") val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?
) : Serializable