package com.example.shutterflow.presentation.gallery


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shutterflow.data.DatabaseProvider
import com.example.shutterflow.data.ImageEntity
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

    fun addImage(filePath: String) {
        viewModelScope.launch {
            dao.insert(ImageEntity(filePath = filePath, timestamp = System.currentTimeMillis()))
        }
    }

    fun deleteImage(image: ImageEntity) {
        viewModelScope.launch {
            File(image.filePath).delete()
            dao.delete(image)
        }
    }
}
