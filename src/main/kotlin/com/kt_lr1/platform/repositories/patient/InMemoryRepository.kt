package com.kt_lr1.platform.repositories.patient

import com.kt_lr1.domain.patient.PatientInfo
import com.kt_lr1.domain.patient.PatientRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private fun mockPatients(): MutableList<PatientInfo> {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return mutableListOf(
        PatientInfo(
            id = "1",
            fullName = "John Doe",
            dateOfBirth = LocalDate.parse("1990-01-15", dateFormatter),
            admissionDate = LocalDate.parse("2023-01-01", dateFormatter),
            dischargeDate = LocalDate.parse("2023-01-15", dateFormatter),
            patientBenefits = listOf("DiscountCard", "Insurance"),
            diseaseType = "Influenza"
        ),
        PatientInfo(
            id = "2",
            fullName = "Jane Smith",
            dateOfBirth = LocalDate.parse("1985-07-20", dateFormatter),
            admissionDate = LocalDate.parse("2023-02-01", dateFormatter),
            dischargeDate = LocalDate.parse("2023-02-10", dateFormatter),
            patientBenefits = listOf("SeniorCitizen"),
            diseaseType = "CommonCold"
        ),
        // Add more mock data as needed
    )
}

class InMemoryRepository(
    private val patients: MutableCollection<PatientInfo> = mockPatients(),
): PatientRepository {
    override fun addPatient(patient: PatientInfo): Result<String> =
    when(patients.add(patient)) {
        true -> Result.success(patient.id)
        false -> Result.failure(Throwable(message = "Patient can`t be added"))
    }

    override fun getPatient(id: String): PatientInfo? = patients.firstOrNull{ it.id == id }

    override fun allPatients(): Collection<PatientInfo> = patients


    override fun getBaseTreatmentCost(diseaseType: String): Double = 10_000.0
}