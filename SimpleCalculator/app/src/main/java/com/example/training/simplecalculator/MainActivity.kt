package com.example.training.simplecalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.*


public class MainActivity : AppCompatActivity() {

    internal var calculateButton: Button? = null
    internal var textFieldA: EditText? = null
    internal var textFieldB: EditText? = null
    internal var actionSpinner: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


        calculateButton = findViewById(R.id.invokeButton) as Button
        textFieldA = findViewById(R.id.myText) as EditText
        textFieldB = findViewById(R.id.myText2) as EditText
        actionSpinner = findViewById(R.id.spinner1) as Spinner

        calculateButton?.setOnClickListener {

            val calculator = MainCalculate()
            val textInputA: String  = textFieldA?.text.toString()
            val textInputB: String  = textFieldB?.text.toString()
            val calculationFunction = getCalculationFunction( actionSpinner?.selectedItem.toString() )
            var answer: String
                    try{
                        answer = calculator.getCalculation(textInputA, textInputB, calculationFunction)
                    }
                    catch(t: Exception)
                    {
                        answer = "Error"
                    }

           /* val infinityValidityChecker = answer
            if(infinityValidityChecker.toString() == "Infinity")
                textFieldA?.setText(CALCULATE_ERROR_RESULT)
            else*/

                textFieldA?.setText(answer)

            textFieldB?.setText(null)
        }

        val calculationFunctions = mutableListOf<String>()
        for( function: CalculatorFunction in CalculatorFunction.values() ){

            calculationFunctions.add( function.name )
        }

        val adapter: ArrayAdapter<String> = ArrayAdapter( this, R.layout.spinner_item, calculationFunctions )
        actionSpinner?.adapter = adapter
    }

}
