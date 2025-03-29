package com.example.demo.model;

import java.time.LocalDate;

public record Player(String id,
		int jerseyNo, 
		String name,
		String position,
		LocalDate dateOfBirth) {
}
