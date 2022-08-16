package ru.javaops.topjava2.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class Menus {

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


}
