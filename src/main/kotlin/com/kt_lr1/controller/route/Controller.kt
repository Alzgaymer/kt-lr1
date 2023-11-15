package com.kt_lr1.controller.route

import com.kt_lr1.domain.patient.PatientInfo
import com.kt_lr1.domain.patient.PatientRepository
import com.kt_lr1.domain.patient.PatientService
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import kotlin.random.nextUInt


class Controller(
    private val patientService: PatientService,
    private val patientRepository: PatientRepository,
) {
    fun calculateSickLeave() {
        println("Type patient id to calculate the number of days on sick leave.")
        val id = readln()
        println("Patient $id has ${patientService.calculateSickLeaveDays(id)} days on sick leave")
    }

    fun showCost() {
        println()
        val id = readln()

        val calculateTreatmentCost = patientService.calculateTreatmentCost(id)

        println("$id`s patient treatment cost - $calculateTreatmentCost")
    }

    fun checkSickDaysPayment() {
        println("Enter patient ID to check if they have sick days payment:")
        val id = readln()

        if (patientService.checkSickLeavePayment(id)) {
            println("The patient has sick days leave payment.")
        } else {
            println("The patient does`t have sick days leave payment.")
        }
    }

    fun showPatients() {
        val patients = patientRepository.allPatients()

        if (patients.isEmpty()) {
            println("No patients found.")
        } else {
            println("List of Patients:")
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

            patients.forEach { patient ->
                println("Patient ${patient.id}:")
                println("Full Name: ${patient.fullName}")
                println("Date of Birth: ${patient.dateOfBirth.format(dateFormatter)}")
                println("Admission Date: ${patient.admissionDate.format(dateFormatter)}")
                println("Discharge Date: ${patient.dischargeDate.format(dateFormatter)}")
                println("Patient Benefits: ${patient.patientBenefits.joinToString(", ")}")
                println("Disease Type: ${patient.diseaseType}")
                println("---------------")
            }
        }
    }

    fun addPatient() {
        println("Enter patient's full name:")
        val fullName = readln()

        println("Enter patient's date of birth (YYYY-MM-DD):")
        val dateOfBirthStr = readln()
        val dateOfBirth = LocalDate.parse(dateOfBirthStr)

        println("Enter admission date (YYYY-MM-DD):")
        val admissionDateStr = readln()
        val admissionDate = LocalDate.parse(admissionDateStr)

        println("Enter discharge date (YYYY-MM-DD):")
        val dischargeDateStr = readln()
        val dischargeDate = LocalDate.parse(dischargeDateStr)

        println("Enter patient benefits (comma-separated, e.g., benefit1,benefit2):")
        val patientBenefitsStr = readln()
        val patientBenefits = patientBenefitsStr.split(",").map { it.trim() }

        println("Enter disease type:")
        val diseaseType = readln()

        patientRepository.addPatient(PatientInfo(
            Random.Default.nextUInt().toString(),
            fullName,
            dateOfBirth,
            admissionDate,
            dischargeDate,
            patientBenefits,
            diseaseType
        ))
    }
}