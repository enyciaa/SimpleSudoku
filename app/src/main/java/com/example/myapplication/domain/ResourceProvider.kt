package com.example.myapplication.domain

import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes stringRes: Int): String
}