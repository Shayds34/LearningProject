package com.example.common.network

interface BaseErrorBody<ERROR_DETAIL : BaseErrorDetail> {
    val code: String?
    val message: String?
    val cause: String?
    val errorDetails: Map<String, String>?
    val errors: List<ERROR_DETAIL>?
}

interface BaseErrorDetail {
    val code: String?
    val message: String?
    val cause: String?
}