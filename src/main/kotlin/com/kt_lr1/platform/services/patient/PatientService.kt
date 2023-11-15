package com.kt_lr1.platform.services.patient

import com.kt_lr1.domain.patient.PatientInfo
import com.kt_lr1.domain.patient.PatientRepository
import com.kt_lr1.domain.patient.PatientService
import java.time.LocalDate

class PatientService(
    private val repository: PatientRepository
) : PatientService {
    // Перевірка оплачуваного лікарняного
    override fun checkSickLeavePayment(id: String): Boolean = calculateSickLeaveDays(id) <= 14

    // Розрахунок вартості лікування залежно від хвороби та перевірка пільг
    override fun calculateTreatmentCost(id: String): Double {
        val patient = repository.getPatient(id)

        val diseaseType = patient?.diseaseType ?: ""
        val patientBenefits = patient?.patientBenefits ?: emptyList()

        val baseCost = repository.getBaseTreatmentCost(diseaseType)
        val discount = calculateBenefitDiscount(patientBenefits)

        return baseCost * (1 - discount)
    }

    // Внутрішній метод для розрахунку кількості днів лікарняного
    override fun calculateSickLeaveDays(id: String): Long {
        val patient = repository.getPatient(id)

        val admissionDate = patient?.admissionDate
        val dischargeDate = patient?.dischargeDate

        return if (admissionDate != null && dischargeDate != null) {
            dischargeDate.toEpochDay() - admissionDate.toEpochDay()
        } else 0
    }

    // Внутрішній метод для розрахунку знижки від пільг
    private fun calculateBenefitDiscount(patientBenefits: List<String>): Double {
        return if ("Insurance" in patientBenefits){0.5}
                else 0.0
    }
}