package com.example.dd.Classes

data class StartingEquipmentClass(
    val _id: String,
    val `class`: ClassX,
    val choice_1: List<Choice1>,
    val choice_2: List<Choice2>,
    val choices_to_make: Int,
    val index: Int,
    val starting_equipment: List<StartingEquipmentX>,
    val url: String
)