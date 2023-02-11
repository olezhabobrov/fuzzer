package com.stepanov.bbf.bugfinder.project


//fun Project.moveAllCodeInOneFile(): Project {
//    val files = this.files.map { BBFFile(it.name, it.psiFile.copy() as PsiFile) }
//    val fileWithImports = psiFactory.createFile("")
//    val packages = files
//        .flatMap { it.psiFile.getAllPSIChildrenOfType<KtPackageDirective>() }
//    // Move all imports in one file
//    val imports = files
//        .flatMap { it.psiFile.getAllPSIChildrenOfType<KtImportDirective>() }
//        .map { it.importPath.toString() }
//        .toSet()
//        //TODO!!!
//        .filter { it.contains("kotlin") || it.contains("java") }
//
//    imports.map {
//        if (it.contains('*'))
//            psiFactory.createImportDirective(ImportPath(FqName(it.takeWhile { it != '*' }), true))
//        else
//            psiFactory.createImportDirective(ImportPath(FqName(it), false))
//    }.forEach { fileWithImports.addImport(it) }
//    files.map { it.psiFile.getAllPSIChildrenOfType<KtPackageDirective>().forEach { it.delete() } }
//    val text = files.joinToString("\n") { it.psiFile.text }
//    val resFile = psiFactory.createFile(fileWithImports.text + "\n" + text)
//    return Project.createFromCode(resFile.text)
//}