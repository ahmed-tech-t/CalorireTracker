package com.example.core.domain.model

import com.example.core.util.FEMALE
import com.example.core.util.MALE

sealed class Gender(var gender: String) {
    object Male : Gender(MALE)
    object Female : Gender(FEMALE)
    companion object {
        fun fromString(gender: String): Gender {
            return when (gender) {
                MALE -> Male
                FEMALE -> Female
                else -> Male
            }
        }
    }
}

