package com.example.materialmanagement.DTO

// 품목 상태 정보
data class StoreStateInfo (
    val no : String,
    val cust_cd : String,
    val stor_cd : String,
    val loca_cd : String,
    val item_cd : String,
    val item_nm : String,
    val qty : Double
)