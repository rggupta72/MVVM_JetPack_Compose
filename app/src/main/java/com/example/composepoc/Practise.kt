package com.example.composepoc


fun higherOderFunctionTakeAsParameterOrReturnAsParameter(a: Int, b: Int, calculate:(Int,Int)->Int):Int{

    return calculate(a,b)
}

fun higherOderFunctionTakeAsParameter(a: Int, b: Int, calculate:(Int,Int)->Int){
    println(calculate(a,b))

}

fun add(a:Int,b:Int): Int{

    return a+b
}

val array = arrayOf(1,2,3,4)


val lambdaExpression1: (Int,Int)->Int={a,b-> a+b}
val lambdaExpression2={a:Int,b:Int-> a+b}


fun main() {
    val abc:String? = null
    println(abc)
    println(higherOderFunctionTakeAsParameterOrReturnAsParameter(5, 3) { a, b -> a * b })
    println(higherOderFunctionTakeAsParameter(5, 3, ::add))

    println(lambdaExpression2(2,3))
    for ((index,element) in array.withIndex()){
      println("$index $element")
    }
}
