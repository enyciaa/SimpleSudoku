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

/**
 * Maps a 2D array
 */
inline fun <reified T> Array<Array<T>>.map(function: (T) -> T): Array<Array<T?>> {
    val data = Array(this.size){arrayOfNulls<T>(this[0].size)}
    for (i in 0 until this.size) {
        for (j in 0 until this[0].size) {
            data[i][j] = function(this[i][j])
        }
    }
    return data
}

