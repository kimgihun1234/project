package com.example.materialmanagement.DTO

// 입고시 넣는 정보
data class InPostInfo (
    val cust_cd : String,
    val stor_cd : String,
    val loca_cd : String,
    val item_cd : String,
    val qty : Double
)