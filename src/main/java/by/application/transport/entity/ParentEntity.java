package by.application.transport.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Maksim Maksimovich
 */
@MappedSuperclass
@Getter
@Setter
public class ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
