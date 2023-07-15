package com.example.kotlinpayment.adapter

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class AccountAdapterTest(
        private val accountAdapter: AccountAdapter
) {
    @Test
    fun `ㄱㅖ좌 사용`() {
        //given
        val useBalanceRequest = UseBalanceRequest(
                userId = 1,
                accountNumber = "",
                amount = 0
        )
        //when
        val useBalanceResponse = accountAdapter.useAccount(useBalanceRequest)

        //then
        println(useBalanceResponse)
    }

}