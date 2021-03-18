package com.example.homework_1

class Student(val id: Long, val name: String, val surname: String, val grade: String, val birthdayYear: String) {

    override fun toString() = "[$id]: $name $surname learning in $grade ($birthdayYear)"

}