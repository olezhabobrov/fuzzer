package com.stepanov.bbf.bugfinder.mutator.transformations.tce

import com.intellij.psi.PsiElement
import com.stepanov.bbf.bugfinder.generator.targetsgenerators.RandomInstancesGenerator
import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.util.getNameWithoutError
import com.stepanov.bbf.bugfinder.util.getTrue
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.apache.log4j.Logger
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.isNullable
import java.lang.StringBuilder
import kotlin.random.Random

class FillerGenerator(
    private val file: BBFFile,
    private val generatedUsages: MutableList<Triple<KtExpression, String, KotlinType?>>
) {

    private val blockListOfTypes = listOf("Unit", "Nothing", "Nothing?")
    private val log = Logger.getLogger("mutatorLogger")

    fun generateExpressionOfType(type: KotlinType, depth: Int = 0): KtExpression? {
        val randomUsage = generatedUsages.randomOrNull() ?: return null
        val isNullable = randomUsage.third!!.isNullable()
        return StdLibraryGenerator.generateCallSequenceToGetType(randomUsage.third!!, type)
            .random()
            .let { handleCallSeq(it) }
            ?.let {
                val prefix = if (isNullable) "(${randomUsage.second})?." else "(${randomUsage.second})."
                psiFactory.createExpressionIfPossible("$prefix${it.text}")
            }
    }

    //TODO repair for Sam<(FT..FT?), (FR..FR?)>
    fun getFillExpressions(node: Pair<KtExpression, KotlinType?>, depth: Int = 0): List<KtExpression> {
        log.debug("replacing ${node.first.text} ${node.second}")
        //Nullable or most common types
        val res = mutableListOf<KtExpression>()
        val neededType = node.second ?: return emptyList()
        val needTypeDescriptor = neededType.constructor.declarationDescriptor
        log.debug("Getting value of type $neededType")
        val isNullable = neededType.isNullable()

        val localRes = mutableListOf<PsiElement>()
        val checkedTypes = mutableListOf<String>()

        for (el in generatedUsages.filter { it.first !is KtProperty }.shuffled()) {
            if (el.third?.getNameWithoutError() in blockListOfTypes) continue
            val elCopy = el.first.copy()
            val typeDescriptorOfUsage = el.third?.constructor?.declarationDescriptor
            if (typeDescriptorOfUsage?.defaultType == needTypeDescriptor?.defaultType) {
                localRes.add(elCopy)
            }
            when {
                el.third?.getNameWithoutError() == "$neededType" -> {
                    localRes.add(elCopy)
                }
                el.third?.getNameWithoutError() == "$neededType?" -> {
                    localRes.add(elCopy)
                }
                StdLibraryGenerator.isImplementation(neededType, el.third) -> {
                    localRes.add(elCopy)
                }
                //commonTypesMap[strNodeType]?.contains(el.third?.toString()) ?: false -> localRes.add(el.first)
            }
            if (neededType.isNullable()) res.add(psiFactory.createExpression("null"))
            if (depth > 0) continue
            //val deeperCases = UsageSamplesGeneratorWithStLibrary.generateForStandardType(el.third!!, nodeType)
            log.debug("GETTING ${neededType} from ${el.third.toString()}")
            if (checkedTypes.contains(el.third!!.toString())) continue
            checkedTypes.add(el.third!!.toString())
            StdLibraryGenerator.generateCallSequenceToGetType(el.third!!, neededType)
                .filter { it.isNotEmpty() }
                .shuffled()
                .take(10)
                .forEach { list ->
                    log.debug("Case = ${list.map { it }}")
                    handleCallSeq(list)?.let {
                        val rtvType = list.last().returnType
                        val prefix = if (isNullable) "(${el.second})?." else "(${el.second})."
                        val exp = "$prefix${it.text}"
                        val postfix = if (exp.contains("?.") && !neededType.isNullable()) "!!" else ""
                        log.debug("Trying to generate expression: $exp$postfix")
                        psiFactory.createExpressionIfPossible("$exp$postfix")?.let {
                            log.debug("GENERATED CALL = ${it.text}")
                            localRes.add(it)
                        }
                    }
                }
            if (localRes.isNotEmpty()) {
                localRes.forEach { res.add(it as KtExpression) }
                break
            }
        }
        return res
    }

    fun handleCallSeq(postfix: List<CallableDescriptor>): KtExpression? {
        val res = StringBuilder()
        var prefix = ""
        postfix.map { desc ->
            val expr = when (desc) {
                is PropertyDescriptor -> desc.name.asString()
                is FunctionDescriptor -> generateCallExpr(desc)?.text
                else -> ""
            }
            expr ?: return null
            res.append(prefix)
            prefix = if (desc.returnType?.isNullable() == true) "?." else "."
            res.append(expr)
        }
        return psiFactory.createExpression(res.toString())
    }

    //We are not expecting typeParams
    private fun generateCallExpr(func: CallableDescriptor): KtExpression? {
        log.debug("GENERATING call of type $func")
        val name = func.name
        val valueParams = func.valueParameters.map { vp ->
            val fromUsages = generatedUsages.filter { usage -> "${vp.type}".trim() == "${usage.third}".trim() }
            if (fromUsages.isNotEmpty() && Random.getTrue(70)) fromUsages.random().second
            else RandomInstancesGenerator(file).generateValueOfType(vp.type)
            //getInsertableExpressions(Pair(it, it.typeReference?.getAbbreviatedTypeOrType()), 1).randomOrNull()
        }
        if (valueParams.any { it.isEmpty() }) {
            log.debug("CANT GENERATE PARAMS FOR $func")
            return null
        }
        val inv = "$name(${valueParams.joinToString()})"
        return psiFactory.createExpressionIfPossible(inv)
    }

}