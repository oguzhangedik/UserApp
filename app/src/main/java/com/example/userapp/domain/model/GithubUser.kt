package com.example.userapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.userapp.core.utils.ZER0

@Entity
data class GithubUser(
    val login: String?,
    val id: Int?,
    val nodeId: String?,
    val avatarUrl: String?,
    val gravatarId: String?,
    val url: String?,
    val htmlUrl: String?,
    val followersUrl: String?,
    val followingUrl: String?,
    val gistsUrl: String?,
    val starredUrl: String?,
    val subscriptionsUrl: String?,
    val organizationsUrl: String?,
    val reposUrl: String?,
    val eventsUrl: String?,
    val receivedEventsUrl: String?,
    val type: String?,
    val userViewType: String?,
    val siteAdmin: Boolean?,
    val score: Double?
) : BaseListItemOfGithubUser() {
    @PrimaryKey(autoGenerate = true) var dbId: Long = ZER0.toLong()

    var searchRequestDbId: Long = ZER0.toLong()

    var isFavorite : Boolean? = false
}
