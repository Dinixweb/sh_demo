package io.seamhealthv1.simplecrudapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Doctors {

    @Id
    private String doctorId;
    @Column(nullable=false)
    private String firstName;
    @Column(nullable=false)
    private String lastName;
    @Column(nullable=false, unique = true)
    private String email;
    @Column(nullable=false, unique = true)
    private String phone;
    private String addressId;
    private String dateCreated;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    private Address address;

}
