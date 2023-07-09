package com.example.tracker_domain.use_case

import com.example.tracker_domain.models.TrackableFood
import com.example.tracker_domain.repo.TrackerRepository

class SearchFood(
    private val repo: TrackerRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {
        if (query.isBlank()) return Result.success(emptyList())
        return repo.searchFood(
                query = query.trim(),
                page = page,
                pageSize = pageSize
            )
    }
}