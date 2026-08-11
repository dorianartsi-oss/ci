package com.example.ms1;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class PaymentControllerTest {

	private final PaymentController controller = new PaymentController(new PaymentService());

	@Test
	void reportsHealth() {
		assertThat(controller.health()).containsEntry("status", "UP");
	}

	@Test
	void delegatesAuthorizationToTheService() {
		assertThat(controller.authorize("acct", new BigDecimal("10.00")).approved()).isTrue();
	}

	@Test
	void surfacesARejectionFromTheService() {
		assertThat(controller.authorize("acct", new BigDecimal("-5")).approved()).isFalse();
	}
}
