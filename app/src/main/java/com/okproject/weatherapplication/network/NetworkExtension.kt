package com.okproject.weatherapplication.network

fun List<String>.toQueryString() = toString()
    .filterNot { char ->
        when (char) {
            '[', ']', ' ' -> true
            else -> false
        }
    }
