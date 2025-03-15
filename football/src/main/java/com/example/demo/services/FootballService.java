package com.example.demo.services;

import java.rmi.AlreadyBoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Player;

@Service
public class FootballService {
	private final Map<String, Player> players = new HashMap(Map.ofEntries(
			Map.entry("1884823",
					new Player("1884823", 
							5, 
							"Ivana ANDRES", 
							"Defender", 
							LocalDate.of(1994, 07, 13))),
			Map.entry("325636",
					new Player("325636",
							11, 
							"Alexia PUTELLAS",
							"Midfielder",
							LocalDate.of(1994, 02, 04)))));
	
	public List<Player> listPlayers(){
		return players.values().stream().
				collect(Collectors.toList());
	}
	
	public Player getPlayer(String id) {
		Player player = players.get(id);
		if(player == null) {
			throw new NotFoundException("Player not found");
		
		}
		return player;
	}
	
	public Player addPlayer(Player newPlayer) {
		if(players.containsKey(newPlayer.id())) {
			throw new AlreadyExistsException("The playrt already exists");
		} else {
			players.put(newPlayer.id(), newPlayer);
			
		}
		return newPlayer;
	}
	
	public Player updatePlayer(Player player) {
		if(!players.containsKey(player.id())) {
			throw new NotFoundException("The player does not exist");
		} else {
			players.put(player.id(), player);
		}
		return player;
	}
	
	public void deletePlayer(String id) {
		if(players.containsKey(id)) {
			players.remove(id);
		}
	}
}
