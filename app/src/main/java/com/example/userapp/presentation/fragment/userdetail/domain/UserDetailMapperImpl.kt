package com.example.userapp.presentation.fragment.userdetail.domain

import com.example.userapp.domain.model.BaseListItemOfGithubUserDetail
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.domain.model.GithubUserDetail
import com.example.userapp.domain.model.UserDetailHeaderItem
import com.example.userapp.domain.model.UserDetailListItem
import com.example.userapp.core.utils.*


class UserDetailMapperImpl : UserDetailMapper {
    override fun map(
        githubUser: GithubUser,
        githubUserDetail: GithubUserDetail
    ): ArrayList<BaseListItemOfGithubUserDetail> {
        val userDetails = arrayListOf<BaseListItemOfGithubUserDetail>()

        userDetails.add(
            UserDetailHeaderItem(
                avatarUrl = githubUserDetail.avatarUrl ?: EMPTY,
                isFavorite = githubUser.isFavorite ?: false,
                name = githubUserDetail.name ?: NOT_SPECIFIED,
                company = githubUserDetail.company ?: NOT_SPECIFIED,
                publicRepos = githubUserDetail.publicRepos?.toString() ?: ZERO_TEXT,
                followers = githubUserDetail.followers?.toString() ?: ZERO_TEXT,
                following = githubUserDetail.following?.toString() ?: ZERO_TEXT
            )
        )

        arrayListOf<Pair<String, String?>>().apply {
            add(Pair(UserDetailListItemKey.BIO, githubUserDetail.bio))
            add(Pair(UserDetailListItemKey.EMAIL, githubUserDetail.email))
            add(Pair(UserDetailListItemKey.BLOG, githubUserDetail.blog))
            add(Pair(UserDetailListItemKey.LOCATION, githubUserDetail.location))
            add(Pair(UserDetailListItemKey.TWITTER, githubUserDetail.twitterUsername))
        }
            .filter { it.second.isNullOrBlank().not() }
            .forEach { userDetail ->
                userDetail.second?.trim()?.let { userDetailDescription ->
                    val userDetailListItem = UserDetailListItem(
                        title = userDetail.first,
                        description = userDetailDescription
                    )
                    userDetails.add(userDetailListItem)
                }
            }

        return userDetails
    }
}