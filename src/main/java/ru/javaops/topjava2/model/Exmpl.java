package ru.javaops.topjava2.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;

@Entity
@Table(name = "exmpl")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Exmpl {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Serial
    @Id
    private Integer id = 1;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String name;


}
