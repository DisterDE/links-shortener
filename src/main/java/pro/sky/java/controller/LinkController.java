package pro.sky.java.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.domain.Link;
import pro.sky.java.service.LinkService;

import java.net.URI;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService service;

    @GetMapping("/shortener/{url}")
    public Link convertLink(@PathVariable String url) {
        return service.transform(url);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        if (shortUrl.equals("favicon.ico")) {
            return ok().build();
        }
        return status(HttpStatus.FOUND)
                .location(URI.create("https://" + service.getOriginal(shortUrl)))
                .build();
    }

    @GetMapping("/")
    public Iterable<Link> getAll() {
        return service.getAll();
    }
}
