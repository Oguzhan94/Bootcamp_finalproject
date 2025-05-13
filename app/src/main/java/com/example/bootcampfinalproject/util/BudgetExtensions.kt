package com.example.bootcampfinalproject.util

fun Int.formatWithSuffix(): String {
    return when {
        this >= 1_000_000_000 -> String.format("%.1f B", this / 1_000_000_000.0)
        this >= 1_000_000     -> String.format("%.1f M", this / 1_000_000.0)
        this >= 1_000         -> String.format("%.1f k", this / 1_000.0)
        else -> this.toString()
    }
}
