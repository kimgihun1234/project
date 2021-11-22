package com.example.materialmanagement.DTO

//출고 정보 입력
data class OutPostInfo (
    val cust_cd : String,
    val stor_cd : String,
    val loca_cd : String,
    val item_cd : String,
    val qty : Double
)