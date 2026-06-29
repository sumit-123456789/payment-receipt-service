package com.artintbakery.receiptservice.model

import java.time.Instant

data class Receipt(
    val receiptId: String,
    val storeName: String,
    val receiptText: String,
    val totalCents: Int,
    val createdAt: Instant = Instant.now()
)