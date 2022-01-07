package pro.sky.java.repository;

import reactor.core.publisher.Mono;

public interface LinkRepository {
    Mono<String> find(String shorten);

    Mono<Boolean> save(String shorten, String original);
}
