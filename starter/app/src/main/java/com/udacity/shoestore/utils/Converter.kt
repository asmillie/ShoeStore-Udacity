package com.udacity.shoestore.utils

import android.widget.EditText
import androidx.databinding.InverseMethod

object Converter {
    @InverseMethod("stringToDouble")
    @JvmStatic fun doubleToString(double: Double): String {
        return double.toString()
    }

    @JvmStatic fun stringToDouble(string: String): Double {
        return string.toDouble()
    }
}