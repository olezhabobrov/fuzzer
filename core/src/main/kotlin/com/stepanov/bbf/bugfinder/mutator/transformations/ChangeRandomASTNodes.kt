package com.stepanov.bbf.bugfinder.mutator.transformations

import com.intellij.lang.ASTNode
import com.stepanov.bbf.bugfinder.util.getAllChildrenNodes
import com.stepanov.bbf.bugfinder.util.replaceThis
import com.stepanov.bbf.reduktor.parser.PSICreator.psiFactory
import org.jetbrains.kotlin.psi.psiUtil.parents
import kotlin.random.Random

class ChangeRandomASTNodes: Transformation(200) {

    override fun transform(target: FTarget) {
        val children = target.file.psiFile.node.getAllChildrenNodes()
        swapRandomNodes(children)
    }

    companion object {

        fun swapRandomNodes(children: List<ASTNode>) {
            //Swap random nodes
            var randomNode1 = children[Random.nextInt(children.size)]
            var randomNode2 = children[Random.nextInt(children.size)]
            while (true) {
                if (randomNode1.text.trim().isEmpty() /*|| randomNode1.text.contains("\n")*/
                    || randomNode1.parents().contains(randomNode2)
                )
                    randomNode1 = children[Random.nextInt(children.size)]
                else if (randomNode2.text.trim().isEmpty() /*|| randomNode2.text.contains("\n")*/
                    || randomNode2.parents().contains(randomNode1)
                )
                    randomNode2 = children[Random.nextInt(children.size)]
                else break
            }
            swap(randomNode1, randomNode2)
        }

        private fun swap(randomNode1: ASTNode, randomNode2: ASTNode): Pair<ASTNode, ASTNode> {
            val tmp1 = psiFactory.createProperty("val a = 1")
            val tmp2 = psiFactory.createProperty("val a = 2")
            randomNode1.treeParent.addChild(tmp1.node, randomNode1)
            randomNode2.treeParent.addChild(tmp2.node, randomNode2)
            tmp1.replaceThis(randomNode2.psi)
            tmp2.replaceThis(randomNode1.psi)
            return randomNode2 to randomNode1
        }
    }
}