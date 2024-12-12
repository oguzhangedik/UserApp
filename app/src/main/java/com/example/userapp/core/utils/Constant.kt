package com.example.userapp.core.utils
object FragmentDataTransferKeyword {
    const val GITHUB_USER = "githubUser"
}

const val EMPTY = ""
const val NOT_SPECIFIED = "not specified"
const val ZERO_TEXT = "0"
const val SOMETHING_WENT_WRONG = "Something went wrong"
const val ZER0 = 0
const val NETWORK_ERROR = "Check your network connection"

object Base {
    const val PERMISSION_WARNING_MESSAGE = "This application can't post or receive notifications without Notification permission"
}

object RegexUtils {
    const val SEARCH_TEXT_REGEX = "[^a-zA-Z0-9\\s]"
}

object UserDetailListItemKey {
    const val BIO = "BIO"
    const val EMAIL = "EMAIL"
    const val BLOG = "BLOG"
    const val LOCATION = "LOCATION"
    const val TWITTER = "TWITTER"
}

object UserSearch {
    const val USER_NOT_FOUND = "User not found"

    const val LOAD_MORE_TRIGGER_THRESHOLD = 5
    const val RECYCLER_VIEW_CACHE_SIZE = 30
    const val LIST_PAGE_ITEM_COUNT = 30
    const val UPDATE_SEARCH_TEXT_JOB_DELAY = 2000L
    const val LOAD_MORE_JOB_DELAY = 1000L

}

object UserDetail {
    const val USER_DETAIL_EMPTY = "User detail is null"
}
