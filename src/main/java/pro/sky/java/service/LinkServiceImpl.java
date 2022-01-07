package pro.sky.java.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.domain.Link;
import pro.sky.java.repository.LinkRepository;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepository repository;

    @Override
    public Mono<Link> convertToLink(String original) {
        String shorten = createShortLink();
        return repository.save(shorten, original)
                .flatMap(v -> Mono.just(new Link(shorten, original)));
    }

    @Override
    public Mono<String> getOriginal(String shortUrl) {
        return repository.find(shortUrl);
    }

    private String createShortLink() {
        return RandomStringUtils.randomAlphanumeric(6);
    }
}
