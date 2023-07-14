package com.example.kotlinpayment.util

import java.util.*

fun generateOrderId() = "PO" + generateUUID()
fun generateTransactionId() = "PT" + generateUUID()


private fun generateUUID() = UUID.randomUUID().toString().replace("-", "")