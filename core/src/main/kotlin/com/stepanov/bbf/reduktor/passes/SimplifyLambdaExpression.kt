package com.stepanov.bbf.reduktor.passes

class SimplifyLambdaExpression() {

//    override fun simplify() {
//        val lambdas = file.getAllPSIChildrenOfType<KtLambdaExpression>()
//        lambdas
//                .filter { it.getAllPSIChildrenOfType<KtBlockExpression>().isNotEmpty() }
//                .filter { it.bodyExpression != null }
//                .forEach {
//                    val copy = it.copy() as KtLambdaExpression
//                    val body = it.bodyExpression!! as KtExpression
//                    val block = KtPsiFactory(file.project).createBlock(body.text)
//                    it.replaceThis(block)
//                    if (!checker.checkTest(file.text))
//                        block.replaceThis(copy)
//                }
//    }
}