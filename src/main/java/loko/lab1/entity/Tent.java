package loko.lab1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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
    private Integer price;

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

    @Column
    private String imageFile;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tentId")
    private List<Property> properties;

    @OneToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    private Manufacturer manufacturer;

    public String getFullSize() {
        return heightFull.toString() + "*" + lengthFull.toString() + "*" + widthFull.toString();
    }

    public String getSmallSize() {
        return heightSmall.toString() + "*" + lengthSmall.toString() + "*" + widthSmall.toString();
    }
}
