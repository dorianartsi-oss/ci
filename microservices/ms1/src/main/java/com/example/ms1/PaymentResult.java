package com.example.ms1;

import java.math.BigDecimal;

public record PaymentResult(boolean approved, String account, BigDecimal amount, String reason) {

	public static PaymentResult approved(String account, BigDecimal amount) {
		return new PaymentResult(true, account, amount, "approved");
	}

	public static PaymentResult rejected(String reason) {
		return new PaymentResult(false, null, null, reason);
	}
}
