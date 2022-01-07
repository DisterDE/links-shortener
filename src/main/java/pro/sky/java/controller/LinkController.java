package pro.sky.java.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.domain.Link;
import pro.sky.java.service.LinkService;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.HttpStatus.TEMPORARY_REDIRECT;

@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService service;

    @GetMapping("/shortener/{original}")
    public Mono<Link> convertLink(@PathVariable String original) {
        return service.convertToLink(original);
    }

    @GetMapping("/{shorten}")
    public Mono<Void> redirect(@PathVariable String shorten, ServerHttpResponse response) {
        return service.getOriginal(shorten).flatMap(v -> {
            if (!v.startsWith("http")) {
                v = "https://" + v;
            }
            response.setStatusCode(TEMPORARY_REDIRECT);
            response.getHeaders().setLocation(URI.create(v));
            return response.setComplete();
        });
    }
}