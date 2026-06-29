package com.artintbakery.receiptservice.model

data class ReceiptRequest(
    val storeName: String,
    val receiptText: String,
    val totalCents: Int
)