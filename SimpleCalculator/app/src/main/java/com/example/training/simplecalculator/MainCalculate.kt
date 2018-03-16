/**
 * Created by Training on 07/08/2017.
 */
package com.example.training.simplecalculator
import java.text.DecimalFormat

val CALCULATE_ERROR_RESULT: String ="Error"

class MainCalculate {

    var result: Double = 0.0
    var inputB: Double = 0.0

    /**
     *
     * @param inputValueA
     * @param inputValueB
     * @param typeofCalculation @see CalculatorFunction
     * throws
     * @return
     */
    //throws InvalidParameterException
    fun getCalculation(inputValueA: String, inputValueB: String, typeofCalculation: CalculatorFunction? ): String  {

        if( typeofCalculation == null )
            throw Exception( "Type of calculation must be non-null!")

        val nullResult: Double? = inputValueA.toDoubleOrNull()
        val nullB: Double? = inputValueB.toDoubleOrNull()
       // Log.d("log", "A:$nullResult, inputB:$nullB, type: $typeofCalculation.name")

        validityCheck(nullResult, nullB, typeofCalculation)

        result = subCalculate(typeofCalculation)
       
        return format()
    }

    fun validityCheck(nullResult: Double?, nullB: Double?, calcType: CalculatorFunction): Unit {

        if (nullResult !is Double || nullB !is Double )
            throw Exception( "Input numbers must be non-null!")

        if ( calcType == CalculatorFunction.Divide && nullB == 0.0) {

           throw Exception("Can not divide by zero!")
        }
        else{

            result = nullResult
            inputB = nullB
        }
    }

    fun subCalculate(calcType: CalculatorFunction): Double
    {
        return when (calcType) {

            CalculatorFunction.Multiply -> result * inputB
            CalculatorFunction.Divide -> result / inputB
            CalculatorFunction.Add -> result + inputB
            CalculatorFunction.Subtract ->  result - inputB
        }
    }

    fun format(): String
    {
        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY)
             throw Exception("Number too large!")

        return DecimalFormat(decFormatPrecisionCheck(5)).format(result)

    }

    fun decFormatPrecisionCheck(sigFig: Int): String {

        val sigFigNum = Math.pow(10.0, sigFig.toDouble())
        result *=sigFigNum

        if(result % (sigFigNum) != 0.0) {

            //precision check (rounding 10^-6 correctly)
            Math.round(result)

        }
        result /=sigFigNum
        var formatString="#."

        for(counter in 1..sigFig) {

            formatString+="#"
        }

        return formatString
    }
}