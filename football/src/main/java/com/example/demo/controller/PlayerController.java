package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Player;
import com.example.demo.services.FootballService;

import org.springframework.web.bind.annotation.*;
@RequestMapping("/api/v1")
@RestController
public class PlayerController {
	
	private FootballService footballService;
	
	public PlayerController(FootballService footballService) {
		this.footballService = footballService;
	}

	@GetMapping("/players")
	public List<Player> listPlayers(){
//		return List.of("Iavna Andres","Alex Ferguson");
		return footballService.listPlayers();
	}
	
	@PostMapping("/players")
	public Player createPlayer(@RequestBody Player player) {
//		return "Player " + name + " create";
		return footballService.addPlayer(player);
	}
	
	@GetMapping("/players/{id}")
	public Player readPlayer(@PathVariable String id) {
//		return name;
		return footballService.getPlayer(id);
	}
	
	@DeleteMapping("/players/{id}")
	public void deletePlayer(@PathVariable String id) {
//		return "Player " + name + " deleted";
		footballService.deletePlayer(id);
	}
	
//	@PutMapping("/players/{name}")
//	public String updatePlayer(
//			@PathVariable String name,
//			@RequestBody String newName) {
//		return "Player "+ name + " is changed to " + newName;
//	}
	@PutMapping("/players/{id}")
	public void updatePlayer(
			@PathVariable String id,
			@RequestBody Player player) {
		footballService.updatePlayer(player);
	}
}
