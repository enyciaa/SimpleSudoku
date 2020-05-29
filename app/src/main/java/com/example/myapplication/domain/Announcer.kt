package com.example.myapplication.domain

interface Announcer {
    fun announce(
            text: String,
            actionText: String,
            callback: () -> Unit
    )
}