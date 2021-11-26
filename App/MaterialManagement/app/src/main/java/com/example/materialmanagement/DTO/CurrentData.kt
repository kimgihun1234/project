package com.example.materialmanagement.DTO

data class CurrentData (
    val item_cd : String,
    val item_nm : String,
    val stan : String,
    val comm_cd_nm : String, //계정구분
    val qty : Double
)