package com.example.tracker_data.repo

import com.example.tracker_data.local.TrackerDao
import com.example.tracker_data.mapper.toTrackableFood
import com.example.tracker_data.mapper.toTrackedFood
import com.example.tracker_data.mapper.toTrackedFoodEntity
import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_domain.models.TrackableFood
import com.example.tracker_domain.models.TrackedFood
import com.example.tracker_domain.repo.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TrackerRepositoryImpl (
    private val dao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> = try {
        val result = api.searchFood(query = query, page = page, pageSize = pageSize)
        Result.success(result.products
            .filter {
                val calculatedCalories =
                    it.nutriments.carbohydrates100g * 4f +
                            it.nutriments.proteins100g * 4f +
                            it.nutriments.fat100g * 9f
                val lowerBound = calculatedCalories * 0.98f
                val upperBound = calculatedCalories * 1.02f
                it.nutriments.energyKcal100g in (lowerBound..upperBound)
            }
            .mapNotNull { it.toTrackableFood() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.upsertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodByData(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}