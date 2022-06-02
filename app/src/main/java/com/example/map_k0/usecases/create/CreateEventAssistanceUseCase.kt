package com.example.map_k0.usecases.create

import com.example.map_k0.data.repository.EventAssistanceRepository
import com.example.map_k0.domain.entities.EventAssistanceBO

class CreateEventAssistanceUseCase(private val eventAssistanceRepository: EventAssistanceRepository) {
    suspend operator fun invoke(eventAssistanceBO: EventAssistanceBO) = eventAssistanceRepository.createEventAssistance(eventAssistanceBO)
}