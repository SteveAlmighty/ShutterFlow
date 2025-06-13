package com.example.shutterflow.presentation.gallery


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shutterflow.data.DatabaseProvider
import com.example.shutterflow.data.ImageDao
import com.example.shutterflow.data.ImageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File

class PhotoGalleryViewModel(context: Context) : ViewModel() {
    private val db = DatabaseProvider.getDatabase(context)
    private val dao = db.imageDao()

    val images = dao.getAllImages().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun getImagesByCategory(category: String): Flow<List<ImageEntity>> {
        return dao.getImagesByCategory(category)
    }

    fun getCategoryCounts(): Flow<List<ImageDao.CategoryCount>> {
        return dao.getCategoryCounts()
    }

    fun addImage(filePath: String, category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val image = ImageEntity(
                filePath = filePath,
                timestamp = System.currentTimeMillis(),
                category = category
            )
            dao.insert(image)
        }
    }

    fun deleteImage(image: ImageEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(image)
            File(image.filePath).delete() // also delete from storage
        }
    }
}
