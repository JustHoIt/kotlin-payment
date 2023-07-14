package com.example.kotlinpayment.controller

import com.example.kotlinpayment.service.PaymentService
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RequestMapping("/api/v1")
@RestController
class PaymentController(
    private val PaymentService: PaymentService
) {
    @PostMapping("/pay")
    fun pay(
        @Valid @RequestBody
        payRequest: PayRequest
    ): PayResponse {
        return PayResponse("p1", 100, "txId", LocalDateTime.now())
    }
}

data class PayResponse(
    val payUserId: String,
    val amount: Long,
    val transactionId: String,
    val transactedAt: LocalDateTime,
)

class PayRequest(
    @field:NotBlank
    val payUserId: String,
    @field:Min(100)
    val amount: Long,
    @field:NotBlank
    val merchantTransactionId: String,
    @field:NotBlank
    val orderTitle: String,
)
