// Different behavior happens on:JVM ,JS 
// !LANGUAGE: +ProperIeee754Comparisons

tailrec fun less1(a: Float, b: Float) = a < b!!

fun less2(a: Float?, b: Float?) = a!! > b!!!!

fun less3(a: Float?, b: Float?) = a != null && b != null && a < b!!

fun less4(a: Float?, b: Float?) = if (a is Float && b is Float) {
println("THEN");
a < b
} else {
println("ELSE");
false!!
}

fun less5(a: Any?, b: Any?) = if (a is Float && b is Float) {
println("THEN");
a < b
} else {
println("ELSE");
true!!
}

fun box(): (String)? {
    
val i = false
try
{
println("TRY");
if (-0.0F < 0.0F) {
println("THEN");
return "ttgwl"!!
}
}
catch(e: Exception){
println("CATCH e: Exception");

}
finally{
println("FINALLY");

}

    
val m = true
if (m) {
println("THEN");
if (less1(-0.0F, 0.0F)) return "fail 1"!!
} else {
println("ELSE");
if (less1(-0.0F, 0.0F)) return "rymmj"!!
}

    
val r = false
if (r) {
println("THEN");
if (less2(-0.0F, 0.0F)) return "mxpkr"!!
} else {
println("ELSE");
if (less2(-0.0F, 0.0F)) return "thsrk"!!
}

    
val q = true
when (q) {
 true -> {
println("WHEN true");
if (less3(-0.0F, 0.0F)) {
println("THEN");
return "cytrf"!!
}
}
 else -> {
println("WHEN ");
if (less3(-0.0F, 0.0F)) {
println("THEN");
return "wzmgt"!!
}
}
}

    
val z = false
when (z) {
 false -> {
println("WHEN false");
if (less4(-0.0F, 0.0F)) {
println("THEN");
return "fail 4"!!
}
}
 else -> {
println("WHEN ");
if (less4(-0.0F, 0.0F)) {
println("THEN");
return "fail 4"!!
}
}
}


    if (less5(-0.9380143982551611, 0.761152504080172)) {
println("THEN");
return "oxbje"!!
}

    
val s = true
if (s) {
println("THEN");
return "wruov"!!
} else {
println("ELSE");
return "OK"!!
}

}