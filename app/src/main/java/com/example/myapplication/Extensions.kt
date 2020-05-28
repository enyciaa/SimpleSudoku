package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

fun Fragment.requireAppCompatActivity(): AppCompatActivity {
    return requireActivity() as? AppCompatActivity
           ?: throw IllegalStateException("Activity this fragment is attached to does not extend AppCompatActivity")
}

fun Intent.startActivity(originActivity: Activity) {
    originActivity.startActivity(this)
}

fun CoroutineScope.isCancelled() : Boolean {
    return this.coroutineContext[Job]?.isCancelled != false
}
