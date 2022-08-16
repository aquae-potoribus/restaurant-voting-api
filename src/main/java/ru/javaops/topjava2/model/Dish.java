package ru.javaops.topjava2.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import ru.javaops.topjava2.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class Dish implements HasId {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id = 1;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String name;

    @Column(name = "price", nullable = false)
    @Range(min = 0)
    private int price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu", referencedColumnName = "id",
            insertable = true)
    private Menus menu;

    public Dish(String name, int price, Menus menu) {
        this.name = name;
        this.price = price;
        this.menu = menu;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

}
