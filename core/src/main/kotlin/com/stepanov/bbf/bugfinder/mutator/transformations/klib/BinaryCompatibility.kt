package com.stepanov.bbf.bugfinder.mutator.transformations.klib

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation

abstract class BinaryCompatibleTransformation(amount: Int): Transformation(amount)

abstract class BinaryIncompatibleTransformation: Transformation()