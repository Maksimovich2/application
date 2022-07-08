package by.application.transport.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Maksim Maksimovich
 */
@Entity
@Table(name = "firms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Firm extends ParentEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "firm", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Application> applications;
}
