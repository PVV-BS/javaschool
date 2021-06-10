package ru.bs.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "Accounts")
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name="client_id")
    private Client client;

    private Double balance;

    @Length(max = 20)
    private String account;

    private int pin;

    @Length(max = 3)
    private String currency;

}
