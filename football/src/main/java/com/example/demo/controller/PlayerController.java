package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Player;
import com.example.demo.services.FootballService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
	
	@Operation(summary = "Get a player by their ID")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Found the player", 
					content = {
							@Content(mediaType = "application/json", 
									schema = @Schema(implementation = Player.class))
					}
					),
			@ApiResponse(
					responseCode = "400", 
					description = "Invalid ID supplied", 
					content = {
							@Content
					}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Player not found", 
					content = {
							@Content
					}
					)
			
	})
	@GetMapping("/players/{id}")
	public Player readPlayer(@Parameter(description = "id of player to be searched")
	@PathVariable String id) {
//		return name;
		return footballService.getPlayer(id);
	}
	
//	@GetMapping("/players/{id}")
//	public ResponseEntity<Player> readPlayer(@PathVariable String id) {
////		return name;
////		return footballService.getPlayer(id);
//		try {
//			Player player = footballService.getPlayer(id);
//			return new ResponseEntity<>(player, HttpStatus.OK);
//		} catch (NotFoundException e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	@PostMapping("/players")
	public Player createPlayer(@RequestBody Player player) {
//		return "Player " + name + " create";
		return footballService.addPlayer(player);
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
	
	//exception handler
	//they will be automatically called whenever the exceptions are thrown new
//	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
//	@ExceptionHandler(NotFoundException.class)
//	public void notFoundHandler() {		
//	}
//	
//	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Already exists")
//	@ExceptionHandler(AlreadyExistsException.class)
//	public void alreadyExsitsHandler() {		
//	}
	
}
