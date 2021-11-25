package com.example.materialmanagement.DTO

// 바코드 입력 후 받아오는 정보
data class BarcodeInfo (
    val item_nm : String,
    val item_cd : String,
    val qty : Double
)