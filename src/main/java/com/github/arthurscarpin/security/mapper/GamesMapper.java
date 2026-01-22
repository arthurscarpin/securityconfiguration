package com.github.arthurscarpin.security.mapper;

import com.github.arthurscarpin.security.controller.request.GamesRequest;
import com.github.arthurscarpin.security.controller.response.GamesResponse;
import com.github.arthurscarpin.security.entity.Games;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GamesMapper {

    public static Games toGames(GamesRequest request) {
        return Games.builder()
                .title(request.title())
                .description(request.description())
                .build();
    }

    public static GamesResponse toGamesResponse(Games games) {
        return GamesResponse.builder()
                .id(games.getId())
                .title(games.getTitle())
                .description(games.getDescription())
                .build();
    }
}
