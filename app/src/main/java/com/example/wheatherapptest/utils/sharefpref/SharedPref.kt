package com.example.wheatherapptest.utils.sharefpref

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPref @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "MyPrefs"
        private const val NAME_CITY = "name_city"
    }

    var cityy: String?
        get() = sharedPreferences.getString(NAME_CITY, null)
        set(value) {
            Log.d("SharedPref", "Saving city: $value")
            sharedPreferences.edit().putString(NAME_CITY, value).apply()
            Log.d("SharedPref", "Saving city: $value")
        }

}


