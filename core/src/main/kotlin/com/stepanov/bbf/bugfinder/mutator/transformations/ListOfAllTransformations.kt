package com.stepanov.bbf.bugfinder.mutator.transformations

import com.stepanov.bbf.bugfinder.server.messages.TransformationClass
import kotlin.reflect.KClass

object Constants {
    // TODO: all transformation classes should be here
    val allTransformations: List<TransformationClass> = listOf(
        AddArgumentToFunction::class,
//        AddBlockToExpression(),
//        AddBracketsToExpression(),
//        AddCallableReference(),
//        AddCasts(),
//        AddDefaultValueToArg::class,
//        AddExpressionToLoop::class,
//        AddFunInvocations(),
//        AddInheritance::class,
//        AddLabels::class,
//        AddLoop::class,
//        AddMethodToClass::class,
//        AddNotNullAssertions::class,
//        AddNullabilityTransformer::class,
//        AddPossibleModifiers::class,
//        AddPropertiesToClass::class,
//        AddRandomAnnotation::class,
//        AddRandomClass(),
//        AddRandomComponent::class,
//        AddRandomControlStatements::class,
//        AddRandomDS::class,
//        AddRandomNode::class,
//        AddReificationToTypeParam::class,
//        AddSameFunctions::class,
//        AddTryExpression::class,
//        ChangeArgToAnotherValue::class,
//        ChangeConstants::class,
//        ChangeModifiers::class,
//        ChangeOperators::class,
//        ChangeOperatorsToFunInvocations::class,
//        ChangeRandomASTNodes::class,
//        ChangeRandomASTNodesFromAnotherTrees::class,
//        ChangeRandomLines::class,
//        ChangeReturnValueToConstant::class,
//        ChangeSmthToExtension::class,
//        ChangeTypes::class,
//        ChangeVarToNull::class,
//        ExpressionObfuscator::class,
//        ExpressionReplacer::class,
//        LocalTCE::class,
//        ReinitProperties::class,
//        RemoveRandomLines::class,
//        ReplaceDotExpression::class,
//        ShuffleFunctionArguments::class,
//        SkeletonEnumeration::class,
//        TCETransformation::class
    ).map { TransformationClass(it) }
}
