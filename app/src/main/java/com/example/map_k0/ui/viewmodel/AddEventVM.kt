package com.example.map_k0.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.map_k0.data.remote.datasource.EventRemoteDataSourceImpl
import com.example.map_k0.data.repository.EventRepository
import com.example.map_k0.domain.entities.EventBO
import com.example.map_k0.usecases.create.CreateEventUseCase
import com.example.map_k0.usecases.create.CreateLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEventVM : ViewModel() {

    private val eventRepository = EventRepository(EventRemoteDataSourceImpl())

    val createdEvent = MutableLiveData<EventBO>()

    val created = MutableLiveData<Boolean>(false)

    private val createEventUseCase = CreateEventUseCase(eventRepository)

    fun createEvent(eventBO: EventBO){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO se usa para llamada largas o API
            createEventUseCase.invoke(eventBO)
            created.postValue(true)
        }
    }
}