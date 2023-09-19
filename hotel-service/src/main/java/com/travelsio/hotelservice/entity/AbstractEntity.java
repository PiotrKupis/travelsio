package com.travelsio.hotelservice.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "last_modification_time")
    private LocalDateTime lastModificationTime;
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @PrePersist
    private void initializeTime() {
        this.creationTime = LocalDateTime.now();
        this.lastModificationTime = LocalDateTime.now();
    }

    @PostUpdate
    private void updateModificationTime() {
        this.lastModificationTime = LocalDateTime.now();
    }
}
