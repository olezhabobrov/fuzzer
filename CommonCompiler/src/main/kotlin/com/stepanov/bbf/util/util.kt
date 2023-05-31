package com.stepanov.bbf.util

import com.stepanov.bbf.messages.ProjectMessage

fun String.getSimpleFileNameWithoutExt() =
    this.substringAfterLast("/").substringBeforeLast(".kt")

fun String.getWithoutExt() = this.substringBeforeLast(".kt")

fun String.getSimpleNameFile() = this.substringAfterLast("/")

fun String.getKlibName(project: ProjectMessage) =
    "${project.dir}${this.getSimpleFileNameWithoutExt()}.klib"