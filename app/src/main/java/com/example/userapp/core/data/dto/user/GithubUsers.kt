package com.example.userapp.core.data.dto.user

import android.os.Parcelable
import android.view.View
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Entity
data class GithubUserSearchRequest(
    val textInUserNameToSearch: String = "A",
    val page: Int = 0,
    val perPageUserCount: Int = 30
) {
    @PrimaryKey(autoGenerate = true) var dbId: Long = 0
}

@Parcelize
data class GithubUserResponse(
    @Json(name = "total_count") val totalCount: Int?,
    @Json(name = "incomplete_results") val incompleteResults: Boolean?,
    @Json(name = "items") val items: List<GithubUser>?
) : Parcelable

    @Entity
    data class GithubUser(
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
    @Json(name = "score") val score: Double?
) : BaseListItemOfGithubUser() {
    @PrimaryKey(autoGenerate = true) var dbId: Long = 0

    var searchRequestDbId: Long = 0

    var isFavorite : Boolean? = false
}

data class NoItemOfGithubUser(val noItemMessage : String = "Kullanıcı Bulunamadı.") : BaseListItemOfGithubUser()

class ProgressItemOfGithubUser : BaseListItemOfGithubUser()

@Parcelize
open class BaseListItemOfGithubUser : Parcelable

interface GithubUserItemClickListener {
    fun onUserClicked(itemView: View?, favoriteView: View?, githubUser: GithubUser?)
    fun onUserFavoriteButtonClicked(itemView: View?, favoriteView: View?, githubUser: GithubUser?)
}

/************************************************************************************************/

data class GithubUserDetailRequest(
    val login: String
)

@Entity
@Parcelize
data class GithubUserDetail(
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
) : Parcelable {
    @PrimaryKey(autoGenerate = true) var dbId: Long = 0
}


