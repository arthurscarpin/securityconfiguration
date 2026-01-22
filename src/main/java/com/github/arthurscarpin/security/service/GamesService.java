package com.github.arthurscarpin.security.service;

import com.github.arthurscarpin.security.controller.request.GamesRequest;
import com.github.arthurscarpin.security.controller.response.GamesResponse;
import com.github.arthurscarpin.security.entity.Games;
import com.github.arthurscarpin.security.mapper.GamesMapper;
import com.github.arthurscarpin.security.repository.GamesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GamesService {

    private final GamesRepository repository;

    public GamesResponse save(GamesRequest request) {
        return GamesMapper.toGamesResponse(repository.save(GamesMapper.toGames(request)));
    }

    public List<GamesResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(GamesMapper::toGamesResponse)
                .toList();
    }

    public Optional<GamesResponse> findById(Long id) {
        return repository.findById(id)
                .map(GamesMapper::toGamesResponse);
    }

    public GamesResponse updateById(Long id, GamesRequest request) {
        Games updatedGames = GamesMapper.toGames(request);
        updatedGames.setId(id);
        return GamesMapper.toGamesResponse(repository.save(updatedGames));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
