package com.example.map_k0.usecases.get

import com.example.map_k0.data.repository.EventAssistanceRepository
import com.example.map_k0.domain.entities.EventAssistanceBO

class GetAllEventsAssistanceUseCase(private val eventAssistanceRepository: EventAssistanceRepository) {
    suspend operator fun invoke() : List<EventAssistanceBO> = eventAssistanceRepository.getEventAssistance()
}