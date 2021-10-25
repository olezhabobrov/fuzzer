// DONT_TARGET_EXACT_BACKEND: WASM
// WASM_MUTE_REASON: IGNORED_IN_JS
// IGNORE_BACKEND: JS_IR
// IGNORE_BACKEND: JS_IR_ES6
// TODO: muted automatically, investigate should it be ran for JS or not
// DONT_RUN_GENERATED_CODE: JS
// IGNORE_BACKEND: JS
// IGNORE_FIR_DIAGNOSTICS_DIFF

tailrec fun test(x : Int) : Unit {
    if (x == 1) {
        test(x - 1)
    } else if (x == 2) {
        test(x - 1)
        return
    } else if (x == 3) {
        <!NON_TAIL_RECURSIVE_CALL!>test<!>(x - 1)
        if (x == 3) {
            test(x - 1)
        }
        return
    } else if (x > 0) {
        test(x - 1)
    }
}

fun box() : String {
    test(1000000)
    return "OK"
}
