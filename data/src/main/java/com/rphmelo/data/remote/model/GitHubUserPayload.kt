package com.rphmelo.data.remote.model

import com.google.gson.annotations.SerializedName

data class GitHubUserPayload (
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)