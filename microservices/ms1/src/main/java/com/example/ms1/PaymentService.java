package com.example.ms1;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	private static final BigDecimal PER_TRANSACTION_LIMIT = new BigDecimal("10000.00");

	public PaymentResult authorize(String account, BigDecimal amount) {
		if (account == null || account.isBlank()) {
			return PaymentResult.rejected("account is required");
		}
		if (amount == null || amount.signum() <= 0) {
			return PaymentResult.rejected("amount must be positive");
		}
		if (amount.compareTo(PER_TRANSACTION_LIMIT) > 0) {
			return PaymentResult.rejected("amount exceeds the per-transaction limit");
		}
		return PaymentResult.approved(account, amount);
	}
}
