package com.github.matheusmv.springwebfluxstudies.service;

import com.github.matheusmv.springwebfluxstudies.domain.Anime;
import com.github.matheusmv.springwebfluxstudies.repository.AnimeRepository;
import com.github.matheusmv.springwebfluxstudies.util.AnimeCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
public class AnimeServiceTest {

    @Mock
    private AnimeRepository animeRepository;

    @InjectMocks
    private AnimeService animeService;

    private final Anime anime = AnimeCreator.createValidAnime();

    @BeforeAll
    public static void blockHoundSetUp() {
        BlockHound.install();
    }

    @Test
    public void blockHoundWorks() {
        try {
            var task = new FutureTask<>(() -> {
                Thread.sleep(0);
                return "";
            });
            Schedulers.parallel().schedule(task);

            task.get(10, TimeUnit.SECONDS);
            Assertions.fail("should fail");
        } catch (Exception e) {
            Assertions.assertTrue(e.getCause() instanceof BlockingOperationError);
        }
    }

    @BeforeEach
    public void setUp() {
        BDDMockito.when(animeRepository.findAll())
                .thenReturn(Flux.just(anime));
    }

    @Test
    @DisplayName("findAll returns a flux of Anime")
    public void findAllReturnFluxOfAnime_WhenSuccessful() {
        StepVerifier.create(animeService.findAll())
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }
}
