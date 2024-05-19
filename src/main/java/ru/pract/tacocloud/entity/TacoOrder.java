package ru.pract.tacocloud.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class TacoOrder implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date placed_at = new Date();


    @NotBlank(message = "Delivery name is required")
    private String delivery_name;

    @NotBlank(message="Street is required")
    private String delivery_street;

    @NotBlank(message="City is required")
    private String delivery_city;

    @NotBlank(message="State is required")
    private String delivery_state;

    @NotBlank(message="Zip code is required")
    private String delivery_zip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String cc_number;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String cc_expiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String cc_cvv;

    @OneToMany(mappedBy = "taco_order_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_table_id", referencedColumnName = "id")
    private UserTable user_table_id;

    public void addTaco(Taco taco){
        this.tacos.add(taco);
        taco.setTaco_order_id(TacoOrder.this);
    }

    @Transactional
    public void setUserTable(UserTable userTable) {
        userTable.getTacoOrders().add(TacoOrder.this);
        this.setUser_table_id(userTable);
    }
}
