package com.example.vitameanshospitaldoctor.data

data class DetailBloodPressure(
    var immediatleyWakeup: BloodPressure,
    var beforeBreakfast: BloodPressure,
    var afterBreakfast: BloodPressure,
    var beforeLunch: BloodPressure,
    var afterLunch: BloodPressure,
    var beforeDinner: BloodPressure,
    var afterDinner: BloodPressure,
    var beforeSleep: BloodPressure
)
