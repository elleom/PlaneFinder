package eu.oelleo.pf;

import eu.oelleo.pf.domain.Aircraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Component
public class PlainFinderPoller {
    private WebClient webClient = WebClient.create("http://localhost:7634/aircraft");
    private final RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private final RedisOperations<String, Aircraft> redisOperations;

    public PlainFinderPoller(RedisConnectionFactory redisConnectionFactory, RedisOperations<String, Aircraft> redisOperations) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.redisOperations = redisOperations;
    }

    @Scheduled(fixedRate = 1000)
    private void pollPlanes() {
        redisConnectionFactory.getConnection().serverCommands().flushDb();

        webClient.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter(plane -> !plane.getReg().isEmpty())
                .toStream()
                .forEach(ac -> redisOperations.opsForValue().set(ac.getReg(), ac));

        redisOperations.opsForValue()
                .getOperations()
                .keys("*")
                .forEach(ac -> {
                    System.out.println(redisOperations.opsForValue().get(ac));
                });

    }
}
