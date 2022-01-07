package pro.sky.java.repository;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Repository
public class LinkRepositoryImpl implements LinkRepository {

    private final ReactiveValueOperations<String, String> redisOps;

    public LinkRepositoryImpl(ReactiveRedisTemplate<String, String> template) {
        this.redisOps = template.opsForValue();
    }

    @Override
    public Mono<String> find(String shorten) {
        return redisOps.getAndExpire(shorten, Duration.ofHours(1));
    }

    @Override
    public Mono<Boolean> save(String shorten, String original) {
        return redisOps.set(shorten, original, Duration.ofHours(1));
    }
}
