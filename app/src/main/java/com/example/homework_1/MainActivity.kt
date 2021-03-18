package com.example.homework_1

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var editView: EditText

    private val mmap = mutableMapOf<Long, Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        editView = findViewById(R.id.editTextTextPersonName)

        editView.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                val meString = editView.text.toString()
                val id = System.currentTimeMillis()

                if (meString.split(" ").size  != 4) {
                    Toast.makeText(applicationContext, "Неверный ввод данных", Toast.LENGTH_SHORT).show()
                    return@setOnKeyListener true
                }

                val pFamil = Pattern.compile("\\b[A-Z]{1}[a-z]+\\b")
                val mFamil = pFamil.matcher(meString.split(" ")[0])
                if (!mFamil.find()) {
                    Toast.makeText(applicationContext, "Неверная фамилия", Toast.LENGTH_SHORT).show()
                    return@setOnKeyListener true
                }
                val mName = pFamil.matcher(meString.split(" ")[1])
                if (!mName.find()) {
                    Toast.makeText(applicationContext, "Неверное имя", Toast.LENGTH_SHORT).show()
                    return@setOnKeyListener true
                }
                val pGrade = Pattern.compile("\\b\\d+[a-zA-Z]{1}\\b")
                val mGrade = pGrade.matcher(meString.split(" ")[2])
                if (!mGrade.find()) {
                    Toast.makeText(applicationContext, "Неверный класс", Toast.LENGTH_SHORT).show()
                    return@setOnKeyListener true
                }
                val pBdate = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[-,/.](0[1-9]|1[012])[-,/.](19|20)\\d\\d")
                val mBdate = pBdate.matcher(meString.split(" ")[3])
                if (!mBdate.find()) {
                    Toast.makeText(applicationContext, "Неверная дата рождения", Toast.LENGTH_SHORT).show()
                    return@setOnKeyListener true
                }
                val Bdate = meString.split(" ")[3].split("-","/",".",",").joinToString(".")


                val student = Student(id, meString.split(" ")[0],meString.split(" ")[1],meString.split(" ")[2],Bdate)
                editView.text = null;
                mmap.put(id, student)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        val ButtonAdd: Button = findViewById(R.id.button)
        ButtonAdd.setOnClickListener { makeMe() }

    }

    fun makeMe() {

        textView.text =mmap.values.joinToString("\n")

    }
    
}