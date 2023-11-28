package com.example.moviesrating.utils

interface IntentHandler<T> {
    fun obtainIntent(intent: T)
}