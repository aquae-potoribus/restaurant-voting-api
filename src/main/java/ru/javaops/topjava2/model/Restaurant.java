package ru.javaops.topjava2.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 1;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumns({
//            @JoinColumn(name = "menu_id", referencedColumnName = "id",
//                    insertable = true),
//            @JoinColumn(name = "dish_id", referencedColumnName = "dish",
//                    insertable = true)
//    })
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    //@JoinColumn(name = "dish_id", referencedColumnName = "dish")
    private Menus menus;

    @OneToOne
    @JoinColumn(name = "owner", referencedColumnName = "id",
            insertable = true)
    private User user;

}
