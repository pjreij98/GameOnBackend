package com.gameon.controller;

import com.gameon.Exception.ResourceNotFoundException;
import com.gameon.entity.Player;
import com.gameon.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // build create player REST API
    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    //build get player by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Player player = playerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Player with ID " + id + " does not exist"));
        return ResponseEntity.ok(player);
    }

    //build update player REST API
    @PutMapping("{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player playerDetails) {
        Player updatePlayer = playerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Player with ID " + id + " does not exist"));
        updatePlayer.setFirstName(playerDetails.getFirstName());
        updatePlayer.setLastName(playerDetails.getLastName());
        updatePlayer.setEmailId(playerDetails.getEmailId());

        playerRepository.save(updatePlayer);

        return ResponseEntity.ok(updatePlayer);
    }

    // build delete player REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePlayer(@PathVariable Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player with ID " + id + " does not exist"));
        playerRepository.delete(player);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
