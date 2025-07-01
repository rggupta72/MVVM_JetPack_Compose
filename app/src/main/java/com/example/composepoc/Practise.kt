package com.example.composepoc

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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

@Composable
fun abc(){
    val abc = remember{mutableStateOf(::a)}

    Button(onClick = {
        abc.value = ::b
    }) { }
    launchEffect1(abc.value)

}

@Composable
fun launchEffect1(onTemp:() ->Unit){
    val computation by rememberUpdatedState(onTemp)
    LaunchedEffect(true) {
        delay(5000)
        computation()
    }
}

@Composable
fun MyButtonComponent() {
    val scope = rememberCoroutineScope()
    Button(onClick = {
        scope.launch {
           // viewModel.saveUserData() // Suspend function
        }
    }) {
        Text("Save")
    }
}




fun a(){
    Log.d("Print a", "Message of a")
}

fun b(){
    Log.d("Print b", "Message of b")
}
