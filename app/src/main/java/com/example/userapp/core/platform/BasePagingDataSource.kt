package com.example.userapp.core.platform

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class BasePagingDataSource<T : Any> :
    PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T>
}
