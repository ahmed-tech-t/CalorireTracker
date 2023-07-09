package com.example.tracker_domain.use_case

import com.example.tracker_domain.models.TrackedFood
import com.example.tracker_domain.repo.TrackerRepository

class DeleteTrackedFood(
    private val repo: TrackerRepository
) {
    suspend operator fun invoke(food: TrackedFood) {
        return repo.deleteTrackedFood(food)
    }
}