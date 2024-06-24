package com.viesure.assigment.util

enum class ToastType(val value: String) {
    SUCCESS("success"),
    FAIL("fail"),
    WARNING("warning");

    companion object {
        fun from(value: String) = ToastType.values().first { it.value == value }
    }
}


