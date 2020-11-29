package com.rphmelo.data.remote.model

import com.google.gson.annotations.SerializedName

data class RepoPullRequestPayload (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("state") val state: String,
    @SerializedName("user") val user: GitHubUserPayload
)