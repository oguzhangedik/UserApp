package com.example.userapp.domain.model

import android.os.Parcelable
import android.view.View
import com.example.userapp.core.utils.*
import kotlinx.parcelize.Parcelize

data class NoItemOfGithubUser(val noItemMessage : String = UserSearch.USER_NOT_FOUND) : BaseListItemOfGithubUser()

class ProgressItemOfGithubUser : BaseListItemOfGithubUser()

@Parcelize
open class BaseListItemOfGithubUser : Parcelable

interface GithubUserItemClickListener {
    fun onUserClicked(itemView: View?, favoriteView: View?, githubUser: GithubUser?)
    fun onUserFavoriteButtonClicked(itemView: View?, favoriteView: View?, githubUser: GithubUser?)
}

/************************************************************************************************/

@Parcelize
open class BaseListItemOfGithubUserDetail : Parcelable

interface GithubUserDetailItemClickListener {
    fun onUserFavoriteButtonClicked(favoriteView: View?, userDetailHeaderItem: UserDetailHeaderItem?)
}

data class UserDetailHeaderItem (
    val avatarUrl: String,
    var isFavorite : Boolean,
    val name : String,
    val company : String,
    val publicRepos: String,
    val followers: String,
    val following: String
) : BaseListItemOfGithubUserDetail()

class UserDetailListItem(
    val title : String,
    val description : String
) : BaseListItemOfGithubUserDetail()
