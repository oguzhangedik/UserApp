package com.example.userapp.ui.userdetail.domain

import com.example.userapp.core.data.dto.user.BaseListItemOfGithubUserDetail
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserDetail
import com.example.userapp.core.data.dto.user.UserDetailHeaderItem
import com.example.userapp.core.data.dto.user.UserDetailListItem

class UserDetailMapperImpl : UserDetailMapper {
    override fun map(
        githubUser: GithubUser,
        githubUserDetail: GithubUserDetail
    ): ArrayList<BaseListItemOfGithubUserDetail> {
        val userDetails = arrayListOf<BaseListItemOfGithubUserDetail>()

        userDetails.add(
            UserDetailHeaderItem(
                avatarUrl = githubUserDetail.avatarUrl ?: "",
                isFavorite = githubUser.isFavorite ?: false,
                name = githubUserDetail.name ?: "not specified",
                company = githubUserDetail.company ?: "not specified",
                publicRepos = githubUserDetail.publicRepos?.toString() ?: "0",
                followers = githubUserDetail.followers?.toString() ?: "0",
                following = githubUserDetail.following?.toString() ?: "0"
            )
        )

        githubUserDetail.bio?.let { bio->
            val bioListItem = UserDetailListItem(
                title = "BIO",
                description = bio
            )
            userDetails.add(bioListItem)
        }
        githubUserDetail.email?.let { email->
            val listItem = UserDetailListItem(
                title = "EMAIL",
                description = email
            )
            userDetails.add(listItem)
        }
        githubUserDetail.blog?.let { blog->
            val listItem = UserDetailListItem(
                title = "BLOG",
                description = blog
            )
            userDetails.add(listItem)
        }
        githubUserDetail.location?.let { location->
            val listItem = UserDetailListItem(
                title = "LOCATION",
                description = location
            )
            userDetails.add(listItem)
        }
        githubUserDetail.twitterUsername?.let { twitterUsername->
            val listItem = UserDetailListItem(
                title = "TWITTER",
                description = twitterUsername
            )
            userDetails.add(listItem)
        }
        return userDetails
    }
}