package com.example.newsify.SQLDatabase

class LoginModel {

    var id = 0
    lateinit var username: String
    lateinit var email: String
    lateinit var gender: String
    lateinit var password: String

    constructor(
        id: Int,
        username: String,
        email: String,
        gender: String,
        password: String
    ) {
        this.id = id
        this.username = username
        this.email = email
        this.gender = gender
        this.password = password
    }

    constructor()

}