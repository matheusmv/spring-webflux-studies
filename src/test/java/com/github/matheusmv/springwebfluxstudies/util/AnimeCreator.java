package com.github.matheusmv.springwebfluxstudies.util;

import com.github.matheusmv.springwebfluxstudies.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
                .name("Tensei Shitara Slime Datta Ken").
                build();
    }

    public static Anime createValidAnime() {
        return Anime.builder()
                .id(1L)
                .name("Tensei Shitara Slime Datta Ken").
                build();
    }

    public static Anime createValidUpdatedAnime() {
        return Anime.builder()
                .id(1L)
                .name("Tensei Shitara Slime Datta Ken 2").
                build();
    }
}
