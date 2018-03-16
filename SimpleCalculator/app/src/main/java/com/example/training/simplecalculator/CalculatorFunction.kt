package com.example.training.simplecalculator

/**
 * Created by Training on 09/08/2017.
 */
enum class CalculatorFunction( val function: Int ) {

    Divide(1), Multiply(2), Add(3), Subtract(4)

}

fun getCalculationFunction( functionName: String): CalculatorFunction? {

    for( function in CalculatorFunction.values() ){

        if( function.name == functionName )
            return function
    }

    return null;
}

fun getCalculationFunctionSafe( functionName: String): CalculatorFunction {

    for( function in CalculatorFunction.values() ){

        if( function.name == functionName )
            return function
    }

    throw error("Invalid calculator function!")
}