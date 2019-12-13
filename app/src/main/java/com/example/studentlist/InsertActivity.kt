package com.example.studentlist

import android.app.AlertDialog
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_insert.*
import kotlinx.android.synthetic.main.component_button_insert_conf.*

/**
 * Created by Nadya Aditama on 11 Desember 2019
 */

class InsertActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        val dataHelper = DataHelper(this)
        b_insert.setOnClickListener{
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Konfirmasi")
                .setMessage("Are you sure to insert it?")
                .setCancelable(true)
                .setPositiveButton("No"){dialog,which->
                }
                .setNegativeButton("Yes"){dialog,which->
                    val nim = Integer.parseInt(et_nim.text.toString())
                    val name = et_name.text.toString()
                    val faculty = et_faculty.text.toString()
                    val gender = findViewById<RadioButton>(rg_gender.checkedRadioButtonId)
                    dataHelper.addStudent(Student(nim,name,gender.text.toString(),faculty))
                    et_name.setText("")
                    et_nim.setText("")
                    et_faculty.setText("")
                }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}