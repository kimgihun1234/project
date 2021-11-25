package com.example.materialmanagement.DTO

// 로그인 후 받아오는 jwt 값
data class LoginInfo (
    val emp_nm : String,
    val data : String // json token
)