package com.example.map_k0.usecases.create

import com.example.map_k0.data.repository.EventRepository
import com.example.map_k0.domain.entities.EventBO

class CreateEventUseCase(private val eventRepository: EventRepository) {
    suspend operator fun invoke(eventBO: EventBO) = eventRepository.createEvent(eventBO)
}