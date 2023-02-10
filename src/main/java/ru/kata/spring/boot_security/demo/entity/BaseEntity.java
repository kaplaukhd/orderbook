package ru.kata.spring.boot_security.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@MappedSuperclass
public class BaseEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime date = LocalDateTime.now();

    @Transient
    private Boolean justCreated = false;

    public BaseEntity(Long id) {
        this.id = id;
        this.justCreated = true;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return justCreated;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
