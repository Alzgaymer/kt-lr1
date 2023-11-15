package com.kt_lr1.domain.patient

import java.time.LocalDate

interface PatientRepository {
    fun addPatient(patient :PatientInfo): Result<String>
    fun getPatient(id: String): PatientInfo?
    fun allPatients(): Collection<PatientInfo>
    fun getBaseTreatmentCost(diseaseType: String): Double
}