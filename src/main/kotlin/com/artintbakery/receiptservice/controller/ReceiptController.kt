package com.artintbakery.receiptservice.controller

import com.artintbakery.receiptservice.model.ReceiptRequest
import com.artintbakery.receiptservice.model.ReceiptResponse
import com.artintbakery.receiptservice.service.ReceiptService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ReceiptController(
    private val receiptService: ReceiptService
) {

    @PostMapping("/receipts")
    fun createReceipt(@RequestBody request: ReceiptRequest): ReceiptResponse {
        val receipt = receiptService.createReceipt(request)

        return ReceiptResponse(
            receiptId = receipt.receiptId,
            receiptUrl = "http://192.168.2.64:8080/r/${receipt.receiptId}"
        )
    }

    @GetMapping("/receipts/{id}")
    fun getReceipt(@PathVariable id: String): ResponseEntity<Any> {
        val receipt = receiptService.getReceipt(id)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(receipt)
    }
}