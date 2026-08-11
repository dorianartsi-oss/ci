package com.example.ms1;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class PaymentServiceTest {

	private final PaymentService service = new PaymentService();

	@Test
	void approvesAnAmountWithinTheLimit() {
		PaymentResult result = service.authorize("IL120108000000099999999", new BigDecimal("250.00"));

		assertThat(result.approved()).isTrue();
		assertThat(result.account()).isEqualTo("IL120108000000099999999");
		assertThat(result.amount()).isEqualByComparingTo("250.00");
		assertThat(result.reason()).isEqualTo("approved");
	}

	@Test
	void approvesExactlyTheLimit() {
		assertThat(service.authorize("acct", new BigDecimal("10000.00")).approved()).isTrue();
	}

	@Test
	void rejectsAMissingAccount() {
		assertThat(service.authorize(null, BigDecimal.TEN).reason()).isEqualTo("account is required");
		assertThat(service.authorize("   ", BigDecimal.TEN).reason()).isEqualTo("account is required");
	}

	@Test
	void rejectsANonPositiveAmount() {
		assertThat(service.authorize("acct", null).reason()).isEqualTo("amount must be positive");
		assertThat(service.authorize("acct", BigDecimal.ZERO).reason()).isEqualTo("amount must be positive");
		assertThat(service.authorize("acct", new BigDecimal("-1")).reason()).isEqualTo("amount must be positive");
	}

	@Test
	void rejectsAnAmountOverTheLimit() {
		PaymentResult result = service.authorize("acct", new BigDecimal("10000.01"));

		assertThat(result.approved()).isFalse();
		assertThat(result.reason()).isEqualTo("amount exceeds the per-transaction limit");
	}
}
