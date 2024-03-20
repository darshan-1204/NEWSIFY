package com.example.newsify.SQLDatabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteDatabase(
    context: Context?
) : SQLiteOpenHelper(context, "Login.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        var sql = "CREATE TABLE login(id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,email TEXT,gender TEXT,password TEXT)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun AddData(username : String,email : String,gender : String,password : String){
        var db = writableDatabase
        var values = ContentValues()
        values.put("username",username)
        values.put("email",email)
        values.put("gender",gender)
        values.put("password",password)
        db.insert("login",null,values)
    }

    fun showData(): ArrayList<LoginModel> {
        var modelList = ArrayList<LoginModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM login"
        var cursor = db.rawQuery(sql,null)
        cursor.moveToFirst()

        for (x in 0 until cursor.count) {
            var id = cursor.getInt(0)
            var username = cursor.getString(1)
            var email = cursor.getString(2)
            var gender = cursor.getString(3)
            var password = cursor.getString(4)
            var model = LoginModel(id, username, email, gender, password)
            modelList.add(model)
            cursor.moveToNext()
        }
        return modelList
    }

}