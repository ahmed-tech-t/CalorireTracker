package com.example.core.domain.use_case

class FilterOutDigit {
    operator fun invoke(text: String): String {
        return text.filter { it.isDigit() }
    }
}