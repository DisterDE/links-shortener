package pro.sky.java.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Accessors(chain = true)
public class Link {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String shorten;

    @Column(unique = true)
    private String original;

    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdAt = LocalDateTime.now();
}

