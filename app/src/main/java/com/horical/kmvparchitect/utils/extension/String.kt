package com.horical.kmvparchitect.utils.extension

import java.util.regex.Pattern

fun String.isEmail(): Boolean {
    val pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    return Pattern.compile(pattern).matcher(this).matches()
}
