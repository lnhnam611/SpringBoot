package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/football")
@RestController
public class FootballController {
	
	@GetMapping("/teams")
	public List<String> getTeams() {
		return List.of("Argentina","Australia","Brazil");
	}
}
