package com.example.map_k0.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.map_k0.data.remote.datasource.EventRemoteDataSourceImpl
import com.example.map_k0.data.remote.datasource.UserRatingLocationDataSourceImpl
import com.example.map_k0.data.repository.EventRepository
import com.example.map_k0.data.repository.UserRatingLocationRepository
import com.example.map_k0.domain.entities.EventBO
import com.example.map_k0.domain.entities.UserRatingLocationBO
import com.example.map_k0.usecases.get.GetAllEventsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventsVM : ViewModel() {

    val eventList = MutableLiveData<List<EventBO>>()

    private val eventRepository = EventRepository(
        EventRemoteDataSourceImpl()
    )

    private val getAllEventsUseCase = GetAllEventsUseCase(eventRepository)

    fun loadAllEvents(){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
            eventList.postValue(getAllEventsUseCase.invoke())
        }
    }
}