package com.example.tracker_domain.use_case

import com.example.tracker_domain.models.TrackedFood
import com.example.tracker_domain.repo.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDate(
    private val repo: TrackerRepository
) {
   operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repo.getFoodsForDate(date)
    }
}