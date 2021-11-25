package com.example.materialmanagement.DTO

data class StoringListGetData (
    val purc_in_dt : String,
    val item_cd : String,
    val item_nm : String,
    val qty : Double,
    val plord_no : String,
    val cust_cd : String,
    val cust_nm : String,
    val stor_cd : String,
    val stor_nm : String,
    val loca_cd : String,
    val loca_nm : String
)