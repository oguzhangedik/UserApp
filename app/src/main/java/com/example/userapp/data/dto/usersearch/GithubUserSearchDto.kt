package com.example.userapp.data.dto.usersearch

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserSearchDto(
    @Json(name = "total_count") val totalCount: Int?,
    @Json(name = "incomplete_results") val incompleteResults: Boolean?,
    @Json(name = "items") val items: List<GithubUserDto>?
) : Parcelable
