package com.example.vitameanshospitaldoctor.data

data class DetailBloodSugar(
    var immediatleyWakeup: BloodSugar,
    var beforeBreakfast: BloodSugar,
    var afterBreakfast: BloodSugar,
    var beforeLunch: BloodSugar,
    var afterLunch: BloodSugar,
    var beforeDinner: BloodSugar,
    var afterDinner: BloodSugar,
    var beforeSleep: BloodSugar
)