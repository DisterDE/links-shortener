package pro.sky.java.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pro.sky.java.domain.Link;

import java.time.LocalDateTime;

@Repository
public interface LinkRepository extends PagingAndSortingRepository<Link, Integer> {
    Link findByShorten(String shorten);

    void deleteAllByCreatedAtBefore(LocalDateTime dateTime);

    Link findByOriginal(String original);
}
