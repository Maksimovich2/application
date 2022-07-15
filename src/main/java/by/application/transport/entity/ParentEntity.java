package by.application.transport.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
@MappedSuperclass
@Getter
@Setter
public abstract class ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid_d")
    private UUID uuid = UUID.randomUUID();
}