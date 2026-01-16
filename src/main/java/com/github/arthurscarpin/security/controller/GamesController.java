package com.github.arthurscarpin.security.controller;

import com.github.arthurscarpin.security.model.Games;
import com.github.arthurscarpin.security.service.GamesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/security/games")
@RequiredArgsConstructor
public class GamesController {

    private final GamesService service;

    @PostMapping
    public ResponseEntity<Games> save(@Valid @RequestBody Games games) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(games));
    }

    @GetMapping
    public ResponseEntity<List<Games>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Games> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Games> updateById(@PathVariable Long id, @Valid @RequestBody Games games) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.updateById(id, games));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
