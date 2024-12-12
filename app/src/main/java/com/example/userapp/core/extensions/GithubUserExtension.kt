package com.example.userapp.core.extensions

import com.example.userapp.domain.model.GithubUser
import com.example.userapp.domain.model.GithubUserDetail
import com.example.userapp.data.dto.userdetail.GithubUserDetailDto
import com.example.userapp.data.dto.usersearch.GithubUserDto

fun List<GithubUserDto>.toGithubUsers() : List<GithubUser> {
    val githubUsers = arrayListOf<GithubUser>()

    forEach { githubUserDto ->
        githubUsers.add(githubUserDto.toGithubUser())
    }
    return githubUsers
}

fun GithubUserDto.toGithubUser() : GithubUser {
    return GithubUser(
        login = login,
        id = id,
        nodeId = nodeId,
        avatarUrl = avatarUrl,
        gravatarId = gravatarId,
        url = url,
        htmlUrl = htmlUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        organizationsUrl = organizationsUrl,
        reposUrl = reposUrl,
        eventsUrl = eventsUrl,
        receivedEventsUrl = receivedEventsUrl,
        type = type,
        userViewType = userViewType,
        siteAdmin = siteAdmin,
        score = score
    )
}

fun GithubUserDetailDto.toGithubUserDetail() : GithubUserDetail {
    return GithubUserDetail(
        login = login,
        id = id,
        nodeId = nodeId,
        avatarUrl = avatarUrl,
        gravatarId = gravatarId,
        url = url,
        htmlUrl = htmlUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        organizationsUrl = organizationsUrl,
        reposUrl = reposUrl,
        eventsUrl = eventsUrl,
        receivedEventsUrl = receivedEventsUrl,
        type = type,
        userViewType = userViewType,
        siteAdmin = siteAdmin,
        name = name,
        company = company,
        blog = blog,
        location = location,
        email = email,
        hireable = hireable,
        bio = bio,
        twitterUsername = twitterUsername,
        publicRepos = publicRepos,
        publicGists = publicGists,
        followers = followers,
        following = following,
    )
}