package com.artintbakery.receiptservice.controller

import com.artintbakery.receiptservice.service.ReceiptService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

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
                <title>Art Int Bakery Receipt</title>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body {
                        background: #f5f5f5;
                        font-family: Arial, sans-serif;
                        margin: 0;
                        padding: 20px;
                    }
                    .receipt {
                        background: #ffffff;
                        max-width: 420px;
                        margin: 0 auto;
                        padding: 24px;
                        border-radius: 16px;
                        box-shadow: 0 4px 16px rgba(0,0,0,0.12);
                    }
                    .store {
                        text-align: center;
                        font-size: 26px;
                        font-weight: bold;
                        margin-bottom: 4px;
                    }
                    .subtitle {
                        text-align: center;
                        color: #666;
                        margin-bottom: 18px;
                    }
                    pre {
                        font-family: "Courier New", monospace;
                        white-space: pre-wrap;
                        font-size: 14px;
                        line-height: 1.35;
                    }
                    .button {
                        width: 100%;
                        padding: 14px;
                        margin-top: 12px;
                        font-size: 16px;
                        font-weight: bold;
                        border: none;
                        border-radius: 10px;
                        background: #111111;
                        color: white;
                    }
                    .secondary {
                        background: #eeeeee;
                        color: #111111;
                    }
                    @media print {
                        body {
                            background: white;
                            padding: 0;
                        }
                        .receipt {
                            box-shadow: none;
                            border-radius: 0;
                        }
                        .button {
                            display: none;
                        }
                    }
                </style>
            </head>
            <body>
                <div class="receipt">
                    <div class="store">Art Int Bakery</div>
                    <div class="subtitle">Digital Receipt</div>

                    <hr/>

                    <pre>${escapeHtml(receipt.receiptText)}</pre>

                    <hr/>

                    <button class="button" onclick="window.print()">Download / Save PDF</button>
                    <button class="button secondary" onclick="navigator.share ? navigator.share({title: 'Art Int Bakery Receipt', url: window.location.href}) : alert(window.location.href)">
                        Share Receipt
                    </button>
                </div>
            </body>
            </html>
        """.trimIndent()

        return ResponseEntity.ok(html)
    }

    private fun escapeHtml(input: String): String {
        return input
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;")
    }
}