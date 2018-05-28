package com.example.android.calculatorrpn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    val REQUEST_CODE = 10000
    var stack =  Stack<String>();
    var textViewArray = arrayOf<TextView>()
    var tmp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewArray = arrayOf<TextView>(stackOne, stackTwo, stackThree, stackFour)
    }

   fun insert(v: View){

        if(!tmp){
            stackOne.text = ""
        }
       //val b = v as Button
       stackOne.text =   stackOne.text.toString() +  (v as Button).text.toString()

       if(!tmp){
           one.text = "->"
           two.text = "1"
           three.text ="2"
           four.text = "3"
           tmp = true

           var maxIndex = minOf(stack.size-1, 2)
           for(i in stack.size downTo stack.size-maxIndex){
               textViewArray[stack.size - i + 1].text = stack.get(i-1)
           }
       }
   }

    fun enter(v: View){
        if(!stackOne.text.equals("")) {
            stack.add(stackOne.text.toString())
        }

        var maxIndex = minOf(stack.size-1, 3)

        for(i in stack.size downTo stack.size-maxIndex){
            textViewArray[stack.size - i].text = stack.get(i-1)
        }
        one.text = "1"
        two.text = "2"
        three.text ="3"
        four.text = "4"
        tmp = false
    }


    fun changeSign(v: View){
        if(stack.size > 0) {
            var number =  stack.pop()
            var firstLetter = number.substring(0,1)
            if(!firstLetter.equals("-")){
                stack.add("-" + number)
            }else{
                stack.add(number.substring(1,number.length))
            }

            if(!tmp){
                stackOne.text = stack.lastElement()
            }else{
                stackTwo.text = stack.lastElement()
            }

        }
    }

    fun openSettings(v: View){
        val i = Intent(this, SettingsActivity::class.java)
        startActivityForResult(i, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if((requestCode==REQUEST_CODE)&&(resultCode == Activity.RESULT_OK)){
            if(data!= null){
                if(data.hasExtra("returnString1")){
                    constraintLayout.setBackgroundColor(data.extras.getInt("returnString1"))
                }
            }
        }
    }

    fun allClear(v: View){
        for(i in 0..textViewArray.size-1){
            textViewArray[i].text = ""
        }
        stack.clear()
    }

    fun drop(v: View){
        if(stack.size > 0) {
            stack.pop()
            draw()
        }
    }

    fun swap(v: View){
        if(stack.size > 1) {
            var a = stack.pop()
            var b = stack.pop()
            stack.add(a)
            stack.add(b)
            draw()
        }
    }

    fun draw(){
        if (tmp) {
            for(i in 1..textViewArray.size-1){
                textViewArray[i].text = ""
            }

            var maxIndex = minOf(stack.size - 1, 2)


            for (i in stack.size downTo stack.size - maxIndex) {
                textViewArray[stack.size - i + 1].text = stack.get(i - 1)
            }
        } else {
            for(i in 0..textViewArray.size-1){
                textViewArray[i].text = ""
            }

            var maxIndex = minOf(stack.size - 1, 3)

            for (i in stack.size downTo stack.size - maxIndex) {
                textViewArray[stack.size - i].text = stack.get(i - 1)
            }
        }
    }

    fun undo(v: View){
        var number = stackOne.text
        if(tmp && (number.length > 0)){

            stackOne.text = number.substring(0,number.length-1)
        }
    }

    fun addition(v: View){
        if(stack.size > 1) {
            var a = stack.pop()
            var b = stack.pop()
            stack.add((a.toDouble() + b.toDouble()).toString())

            draw()
        }
    }

    fun multiplication(v: View){
        if(stack.size > 1) {
            var a = stack.pop()
            var b = stack.pop()
            stack.add((a.toDouble() * b.toDouble()).toString())

            draw()
        }
    }

    fun division(v: View){
        if(stack.size > 1) {
            var a = stack.pop()
            var b = stack.pop()
            stack.add((a.toDouble() / b.toDouble()).toString())

            draw()
        }
    }

    fun subtraction(v: View){
        if(stack.size > 1) {
            var a = stack.pop()
            var b = stack.pop()
            stack.add((a.toDouble() - b.toDouble()).toString())

            draw()
        }
    }

    fun exponentiation(v: View){
        if(stack.size > 1) {
            var a = stack.pop()
            var b = stack.pop()
            stack.add((Math.pow(b.toDouble(),  a.toDouble())).toString())

            draw()
        }
    }

    fun sqrt(v: View){
        if(stack.size > 0){
            var a = stack.pop()
            stack.add(Math.sqrt(a.toDouble()).toString())
            draw()
        }
    }

}
