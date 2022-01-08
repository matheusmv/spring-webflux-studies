package com.github.matheusmv.springwebfluxstudies.repository;

import com.github.matheusmv.springwebfluxstudies.domain.Anime;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends ReactiveCrudRepository<Anime, Long> {

}
