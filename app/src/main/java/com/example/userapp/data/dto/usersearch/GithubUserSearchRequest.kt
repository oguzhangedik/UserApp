package com.example.userapp.data.dto.usersearch

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.userapp.core.utils.EMPTY
import com.example.userapp.core.utils.UserSearch
import com.example.userapp.core.utils.ZER0

@Entity
data class GithubUserSearchRequest(
    val textInUserNameToSearch: String = EMPTY,
    val page: Int = ZER0,
    val perPageUserCount: Int = UserSearch.LIST_PAGE_ITEM_COUNT
) {
    @PrimaryKey(autoGenerate = true) var dbId: Long = ZER0.toLong()
}
