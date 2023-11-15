package com.kt_lr1.domain.patient

import java.time.LocalDate

data class PatientInfo(
    val id: String,
    val fullName: String, // ПІБ пацієнта
    val dateOfBirth: LocalDate, // дата народження пацієнта
    val admissionDate: LocalDate, // дата надходження
    val dischargeDate: LocalDate, // дата виписки
    val patientBenefits: List<String>, // список пільг пацієнта
    val diseaseType: String // тип хвороби
)

