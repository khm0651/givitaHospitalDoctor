package com.example.vitameanshospitaldoctor.data

import java.util.*

data class Patient(
    val no: Long,
    val uid: String,
    val registration: Calendar,
    val name: String,
    val gender: String,
    val age: Int,
    val disease: String,
    val shrinkage: Int,
    val relaxation: Int,
    val bpRegistrationDate: Calendar,
    val bloodSugar: Int,
    val bsRegistrationDate: Calendar,
    val latestVisit: Calendar,
    val requestDate: Calendar,
    val requestCheck: String
)
