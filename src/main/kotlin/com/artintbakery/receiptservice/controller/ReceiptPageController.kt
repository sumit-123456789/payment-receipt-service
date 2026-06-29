package com.artintbakery.receiptservice.controller

import com.artintbakery.receiptservice.service.ReceiptService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ReceiptPageController(
    private val receiptService: ReceiptService
) {

    @GetMapping("/r/{id}", produces = [MediaType.TEXT_HTML_VALUE])
    fun viewReceipt(@PathVariable id: String): ResponseEntity<String> {
        val receipt = receiptService.getReceipt(id)
            ?: return ResponseEntity.notFound().build()

        val html = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>${receipt.storeName} Receipt</title>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
            </head>
            <body style="font-family: Arial, sans-serif; max-width: 420px; margin: 32px auto; padding: 16px;">
                <div style="border: 1px solid #ddd; border-radius: 12px; padding: 20px;">
                    <h2 style="text-align:center; margin-bottom: 4px;">${receipt.storeName}</h2>
                    <p style="text-align:center; color:#666;">Digital Receipt</p>
                    <hr/>
                    <pre style="font-family: monospace; white-space: pre-wrap;">${receipt.receiptText}</pre>
                    <hr/>
                    <button onclick="window.print()" style="width:100%; padding:14px; font-size:16px;">
                        Print / Save as PDF
                    </button>
                </div>
            </body>
            </html>
        """.trimIndent()

        return ResponseEntity.ok(html)
    }
}