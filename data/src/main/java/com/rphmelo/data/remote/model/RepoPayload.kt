package com.rphmelo.data.remote.model

import com.google.gson.annotations.SerializedName

data class RepoPayload (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("description") val description: String,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("stargazers_count") val starsCount: Int,
    @SerializedName("owner") val owner: GitHubUserPayload
)