package pro.sky.java.service;

import pro.sky.java.domain.Link;
import reactor.core.publisher.Mono;

public interface LinkService {
    Mono<Link> convertToLink(String original);

    Mono<String> getOriginal(String shortUrl);
}
