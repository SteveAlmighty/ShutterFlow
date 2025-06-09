package com.example.shutterflow.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ImageDao {
    @Query("SELECT * FROM images ORDER BY timestamp DESC")
    fun getAllImages(): Flow<List<ImageEntity>>

    @Query("SELECT * FROM images WHERE category = :category ORDER BY timestamp DESC")
    fun getImagesByCategory(category: String): Flow<List<ImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(image: ImageEntity)

    @Delete
    suspend fun delete(image: ImageEntity)

    @Query("SELECT category, COUNT(*) as count FROM images GROUP BY category")
    fun getCategoryCounts(): Flow<List<CategoryCount>>

    data class CategoryCount(
        val category: String,
        val count: Int
    )

}