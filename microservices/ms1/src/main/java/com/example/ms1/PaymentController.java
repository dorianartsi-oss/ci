package com.example.ms1;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

	private final PaymentService payments;

	public PaymentController(PaymentService payments) {
		this.payments = payments;
	}

	@GetMapping("/health")
	public Map<String, String> health() {
		return Map.of("status", "UP");
	}

	@GetMapping("/authorize")
	public PaymentResult authorize(@RequestParam String account, @RequestParam BigDecimal amount) {
		return payments.authorize(account, amount);
	}
}
