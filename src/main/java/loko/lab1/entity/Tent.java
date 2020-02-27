package loko.lab1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "tents")
@AllArgsConstructor
@NoArgsConstructor
public class Tent {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Integer places;

    @Column
    private Double weight;

    @Column
    private String tentName;
    @Column
    private String tentDescription;

    @Column
    private Integer heightFull;
    @Column
    private Integer lengthFull;
    @Column
    private Integer widthFull;

    @Column
    private Integer heightSmall;
    @Column
    private Integer lengthSmall;
    @Column
    private Integer widthSmall;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tentId")
    private List<Property> properties;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    private Manufacturer manufacturer;
}
