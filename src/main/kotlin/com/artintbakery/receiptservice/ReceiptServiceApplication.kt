package com.artintbakery.receiptservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReceiptServiceApplication

fun main(args: Array<String>) {
	runApplication<ReceiptServiceApplication>(*args)
}
