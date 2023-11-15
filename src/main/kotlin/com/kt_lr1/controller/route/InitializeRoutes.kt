package com.kt_lr1.controller.route

import com.kt_lr1.domain.patient.PatientRepository
import com.kt_lr1.platform.repositories.patient.InMemoryRepository
import com.kt_lr1.platform.services.patient.*
import kotlin.system.exitProcess

public fun initializeRoutes(router: Router) {

    val patientRepository: PatientRepository = InMemoryRepository()

    val patientService = PatientService(patientRepository)

    val controller = Controller(patientService, patientRepository)

    router.addMethod(1) {
        controller.calculateSickLeave()
    }
    router.addMethod(2) {
        controller.checkSickDaysPayment()
    }
    router.addMethod(3) {
        controller.addPatient()
    }
    router.addMethod(4) {
        controller.showPatients()
    }
    router.addMethod(5) {
        controller.showCost()
    }
    router.addMethod(0) {
        exitProcess(0)
    }
}