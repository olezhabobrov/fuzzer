package com.stepanov.bbf.bugfinder.mutator.transformations

import kotlin.reflect.KClass

object Constants {
    // TODO: all transformation classes should be here
    val allTransformations: List<KClass<out Transformation>> = listOf(
        AddArgumentToFunction::class,
        AddBlockToExpression::class,
        AddBracketsToExpression::class,
        AddCallableReference::class,
        ExpressionObfuscator::class,
        ExpressionReplacer::class,
        ChangeConstants::class,

    )
}
