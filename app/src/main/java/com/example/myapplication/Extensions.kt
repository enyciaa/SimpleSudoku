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
 * Loop over a 2D array
 */
inline fun <reified T> Array<Array<T>>.forEachIndexed2D(function: (rowNumber: Int, columnNumber: Int, T) -> Unit): Unit {
    var rowNumber = 0
    var columnNumber = 0
    for (i in 0 until this.size) {
        for (j in 0 until this[0].size) {
            function(rowNumber, columnNumber, this[i][j])
            columnNumber++
        }
        columnNumber = 0
        rowNumber++
    }
}

