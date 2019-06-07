package com.example.dd.Classes

data class Class(
    val _id: String,
    val class_levels: ClassLevels,
    val hit_die: Int,
    val index: Int,
    val name: String,
    val proficiencies: List<Proficiency>,
    val proficiency_choices: List<ProficiencyChoice>,
    val saving_throws: List<SavingThrow>,
    val starting_equipment: StartingEquipment,
    val subclasses: List<Subclasse>,
    val url: String
)