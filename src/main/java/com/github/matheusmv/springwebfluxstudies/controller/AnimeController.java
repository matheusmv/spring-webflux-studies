package com.github.matheusmv.springwebfluxstudies.controller;

import com.github.matheusmv.springwebfluxstudies.domain.Anime;
import com.github.matheusmv.springwebfluxstudies.service.AnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/animes")
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    public Flux<Anime> listAll() {
        return animeService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Anime> findById(@PathVariable Long id) {
        return animeService.findById(id);
    }
}
