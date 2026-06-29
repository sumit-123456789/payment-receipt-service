package com.artintbakery.receiptservice.service

import com.artintbakery.receiptservice.model.Receipt
import com.artintbakery.receiptservice.model.ReceiptRequest
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Service
class ReceiptService {

    private val receipts = ConcurrentHashMap<String, Receipt>()

    fun createReceipt(request: ReceiptRequest): Receipt {
        val id = UUID.randomUUID()
            .toString()
            .replace("-", "")
            .take(8)
            .uppercase()

        val receipt = Receipt(
            receiptId = id,
            storeName = request.storeName,
            receiptText = request.receiptText,
            totalCents = request.totalCents
        )

        receipts[id] = receipt
        return receipt
    }

    fun getReceipt(id: String): Receipt? {
        return receipts[id.uppercase()]
    }
}