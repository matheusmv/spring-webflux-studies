package com.github.matheusmv.springwebfluxstudies.controller;

import com.github.matheusmv.springwebfluxstudies.domain.Anime;
import com.github.matheusmv.springwebfluxstudies.service.AnimeService;
import com.github.matheusmv.springwebfluxstudies.util.AnimeCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
public class AnimeControllerTest {

    @Mock
    private AnimeService animeService;

    @InjectMocks
    private AnimeController animeController;

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
        BDDMockito.when(animeService.findAll())
                .thenReturn(Flux.just(anime));
        BDDMockito.when(animeService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.just(anime));
        BDDMockito.when(animeService.save(AnimeCreator.createAnimeToBeSaved()))
                .thenReturn(Mono.just(anime));
    }

    @Test
    @DisplayName("listAll returns a flux of Anime")
    public void listAllReturnFluxOfAnime_WhenSuccessful() {
        StepVerifier.create(animeController.listAll())
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }

    @Test
    @DisplayName("findById returns a Mono with Anime when it exists")
    public void findByIdReturnMonoOfAnime_WhenItExists() {
        StepVerifier.create(animeController.findById(1L))
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }

    @Test
    @DisplayName("save creates an Anime when successful")
    public void saveCreatesAnime_WhenSuccessful() {
        var animeToBeSaved = AnimeCreator.createAnimeToBeSaved();

        StepVerifier.create(animeController.save(animeToBeSaved))
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }
}
