package com.github.matheusmv.springwebfluxstudies.controller;

import com.github.matheusmv.springwebfluxstudies.domain.Anime;
import com.github.matheusmv.springwebfluxstudies.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/animes")
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeRepository animeRepository;

    @GetMapping
    public Flux<Anime> listAll() {
        return animeRepository.findAll();
    }
}
