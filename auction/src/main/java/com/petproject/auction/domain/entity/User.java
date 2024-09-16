package com.petproject.auction.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;
}
