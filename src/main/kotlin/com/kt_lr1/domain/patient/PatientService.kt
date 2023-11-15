package com.kt_lr1.domain.patient

import java.time.LocalDate

interface PatientService {
    fun calculateSickLeaveDays(id: String): Long
    fun checkSickLeavePayment(id: String): Boolean
    fun calculateTreatmentCost(id: String): Double
}