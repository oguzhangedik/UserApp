package com.example.userapp.data.dto.userdetail

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class GithubUserDetailDto(
    @Json(name = "login") val login: String?,
    @Json(name = "id") val id: Int?,
    @Json(name = "node_id") val nodeId: String?,
    @Json(name = "avatar_url") val avatarUrl: String?,
    @Json(name = "gravatar_id") val gravatarId: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "html_url") val htmlUrl: String?,
    @Json(name = "followers_url") val followersUrl: String?,
    @Json(name = "following_url") val followingUrl: String?,
    @Json(name = "gists_url") val gistsUrl: String?,
    @Json(name = "starred_url") val starredUrl: String?,
    @Json(name = "subscriptions_url") val subscriptionsUrl: String?,
    @Json(name = "organizations_url") val organizationsUrl: String?,
    @Json(name = "repos_url") val reposUrl: String?,
    @Json(name = "events_url") val eventsUrl: String?,
    @Json(name = "received_events_url") val receivedEventsUrl: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "user_view_type") val userViewType: String?,
    @Json(name = "site_admin") val siteAdmin: Boolean?,
    @Json(name = "name") val name: String?,
    @Json(name = "company") val company: String?,
    @Json(name = "blog") val blog: String?,
    @Json(name = "location") val location: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "hireable") val hireable: Boolean?,
    @Json(name = "bio") val bio: String?,
    @Json(name = "twitter_username") val twitterUsername: String?,
    @Json(name = "public_repos") val publicRepos: Int?,
    @Json(name = "public_gists") val publicGists: Int?,
    @Json(name = "followers") val followers: Int?,
    @Json(name = "following") val following: Int?
) : Parcelable