package com.example.wheatherapptest.utils.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

}

fun EditText.addTextChange(yesButton: Button, noButton: Button) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s.isNullOrEmpty()) {
                yesButton.visibility = View.INVISIBLE
                noButton.visibility = View.VISIBLE
            } else {
                yesButton.visibility = View.VISIBLE
                noButton.visibility = View.INVISIBLE
            }
        }
    })
}
