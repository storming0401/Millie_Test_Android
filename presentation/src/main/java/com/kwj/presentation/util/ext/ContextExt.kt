package com.kwj.presentation.util.ext

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat

/**
 * 확장 함수를 지원하는 클래스입니다.
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.toastShort(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toastLong(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.text(resId: Int) =
    this.getText(resId).toString()

fun Context.text(resId: Int, value: String) =
    this.getString(resId, value)

fun Context.color(resId: Int) =
    ContextCompat.getColor(this, resId)