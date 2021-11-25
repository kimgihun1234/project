package com.example.materialmanagement.DTO

//삭제시 보내는 품목 정보
data class DeleteStateInfo (
    val no : String,
    val item_cd : String,
    val qty : Double
)