package com.github.arthurscarpin.security.service;

import com.github.arthurscarpin.security.model.Games;
import com.github.arthurscarpin.security.repository.GamesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GamesService {

    private final GamesRepository repository;

    public Games save(Games games) {
        return repository.save(games);
    }

    public List<Games> findAll() {
        return repository.findAll();
    }

    public Optional<Games> findById(Long id) {
        return repository.findById(id);
    }

    public Games updateById(Long id, Games games) {
        games.setId(id);
        return repository.save(games);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
