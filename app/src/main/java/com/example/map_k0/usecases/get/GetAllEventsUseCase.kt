package com.example.map_k0.usecases.get

import com.example.map_k0.data.repository.EventRepository
import com.example.map_k0.domain.entities.EventBO

class GetAllEventsUseCase(private val eventRepository: EventRepository) {
    suspend operator fun invoke() : List<EventBO> = eventRepository.getEvents()
}