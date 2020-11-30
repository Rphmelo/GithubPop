package com.rphmelo.data.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponsePayload(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("incomplete_results") val incompleteResults: Boolean?,
    @SerializedName("items") val items: List<RepoPayload> = arrayListOf()
) : Serializable