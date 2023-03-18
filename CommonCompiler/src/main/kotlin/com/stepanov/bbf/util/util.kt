package com.stepanov.bbf.util

fun String.getSimpleFileNameWithoutExt() =
    this.substringAfterLast("/").substringBeforeLast(".kt")

fun String.getSimpleNameFile() = this.substringAfterLast("/")