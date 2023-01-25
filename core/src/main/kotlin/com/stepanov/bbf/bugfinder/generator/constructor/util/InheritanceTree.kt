package com.stepanov.bbf.bugfinder.generator.constructor.util

import org.jetbrains.kotlin.psi.KtFile


data class InheritanceTreeNode(
    val type: String,
    val supertypes: List<String>,
    val childrenTypes: List<String>
)

class InheritanceTree(val file: KtFile, val root: InheritanceTreeNode) {
}

