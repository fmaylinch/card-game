package tech.bts.cardgame;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.bts.cardgame.repository.GameRepositoryJdbc;
import tech.bts.cardgame.service.GameService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * This will be executed when we start the application.
     * We can try things here.
     */
    @Bean
    public CommandLineRunner test(
            GameService gameService,
            GameRepositoryJdbc gameRepository) {

        return args -> {

            //Game game = gameService.getGameById(1);
            //gameRepository.update(game);
        };
    }
}