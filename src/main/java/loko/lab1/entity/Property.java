package loko.lab1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tent_properties")
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String fullText;

    @Column
    private Long tentId;
}
