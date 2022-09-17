package ru.javaops.topjava2.model;

import lombok.*;
import ru.javaops.topjava2.HasId;

import javax.persistence.*;


@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class Menu implements HasId {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id = 1;

    @OneToOne()
    @JoinColumn(name = "restaurant", referencedColumnName = "id",
            insertable = true)
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(name = "owner", referencedColumnName = "id",
            insertable = true)
    private User user;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Menu(Restaurant restaurant, User user) {
        this.restaurant = restaurant;
        this.user = user;
    }
}
