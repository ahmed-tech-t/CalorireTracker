package com.example.tracker_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tracker_data.local.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun upsertTrackedFood(foodEntity: TrackedFoodEntity)

    @Delete
   suspend fun deleteTrackedFood(foodEntity: TrackedFoodEntity)

    @Query(
        value = """
                    SELECT * 
                    FROM  trackedfoodentity 
                    where dayOfMonth = :day  AND month =:month AND year = :year
                """
    )
    fun getFoodByData(day: Int, month: Int, year: Int) : Flow<List<TrackedFoodEntity>>
}