package pro.sky.java.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.java.domain.Link;
import pro.sky.java.repository.LinkRepository;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository repository;

    public Link transform(String original) {
        Link existedLink = repository.findByOriginal(original);

        if (existedLink != null) {
            return existedLink;
        }

        String shorten = createShortLink();
        Link link = new Link()
                .setOriginal(original)
                .setShorten(shorten);
        return repository.save(link);
    }

    private String createShortLink() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100_000));
    }

    public String getOriginal(String shortUrl) {
        return repository.findByShorten(shortUrl).getOriginal();
    }

    public Iterable<Link> getAll() {
        return repository.findAll();
    }

    @Async
    @Scheduled(initialDelay = 60_000, fixedDelay = 3_600_000)
    public void removeOldRecords() {
        repository.deleteAllByCreatedAtBefore(LocalDateTime.now().minusHours(1));
    }
}
