package com.example.bootcampfinalproject.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bootcampfinalproject.data.remote.TmdbApi
import com.example.bootcampfinalproject.data.remote.mapper.toDomain
import com.example.bootcampfinalproject.data.remote.model.Result
import com.example.bootcampfinalproject.domain.MovieCategory

class MoviePagingSource(
    private val api: TmdbApi,
    private val category: MovieCategory
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val currentPage = params.key ?: 1
          val response = when(category){
                MovieCategory.UP_COMING -> api.getUpComingMovies(page = currentPage)
              MovieCategory.TOP_RATED -> {
                  val topRated = api.getTopRatedMovies(page = currentPage)
                  Log.d("PagingSource", "TopRated: ${topRated.results.size}")
                  topRated
              }
            }

            LoadResult.Page(
                data = response.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.results.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            Log.e("PagingSource", "Error loading data", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
