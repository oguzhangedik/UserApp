package com.example.userapp.ui.usersearch.domain

enum class UserSearchActionState {
    NULL,
    GET_GITHUB_USERS,
    LOAD_MORE_GITHUB_USERS,
    SEARCH_NEW_GITHUB_USERS,
    UPDATE_GITHUB_USER_TO_FAVORITE_STATE,
    UPDATE_GITHUB_USER_TO_UNFAVORITE_STATE
}
