package ru.javaops.topjava2.model;

import lombok.*;
import ru.javaops.topjava2.HasId;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Restaurant implements HasId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;

    @OneToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User user;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Restaurant(Menu menu, User user) {
        this.menu = menu;
        this.user = user;
    }
}
