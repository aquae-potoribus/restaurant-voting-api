package ru.javaops.topjava2.model;

import lombok.*;
import ru.javaops.topjava2.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote implements HasId {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id = 1;

    @ManyToOne
    @JoinColumn(name = "restaurant", referencedColumnName = "id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "registered", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

//    public Vote(Restaurant restaurant, User user) {
//        this.restaurant = restaurant;
//        this.user = user;
//    }

    public Vote(Restaurant restaurant, User user, LocalDateTime dateTime) {
        this.restaurant = restaurant;
        this.user = user;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "restaurant=" + restaurant.getId() +
                ", user=" + user.getId() +
                ", dateTime=" + dateTime +
                '}';
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
