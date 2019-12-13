package com.example.studentlist

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_student.view.*

/**
 * Created by Nadya Aditama on 11 Desember 2019
 */

class StudentAdapter (private val context: Context, private var studentList: ArrayList<Student>) : RecyclerView.Adapter<StudentAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_student,parent,false))
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentAdapter.ViewHolder, position: Int) {
        holder.onBind(studentList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun onBind(student: Student){
            itemView.tv_nim.text = student.nim.toString()
            itemView.tv_name.text = student.name
            itemView.tv_faculty.text = student.faculty
            if (student.gender=="Male"){
                itemView.iv_student.setImageResource(R.drawable.maleprofile)
            }
            else{
                itemView.iv_student.setImageResource(R.drawable.femaleprofile)
            }
            itemView.setOnLongClickListener{
                val alertDialogBuilder = AlertDialog.Builder(itemView.context)
                alertDialogBuilder.setTitle("Konfirmasi")
                    .setMessage("Are you sure to delete "+student.name+"?")
                    .setCancelable(true)
                    .setPositiveButton("No"){dialog,which->
                        Toast.makeText(itemView.context, "cancel delete ", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Yes"){dialog,which->
                        val db = DataHelper(itemView.context)
                        db.deleteStudent(student)
                        studentList.remove(student)
                        notifyDataSetChanged()
                        Toast.makeText(itemView.context,"delete success",Toast.LENGTH_SHORT).show()
                    }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
                true
            }
        }
    }

    fun getUpdate(){
        val db = DataHelper(context)
        studentList = db.getAllStudent()
        notifyDataSetChanged()
    }
}