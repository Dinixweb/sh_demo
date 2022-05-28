package io.seamhealthv1.simplecrudapplication.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    private String addressId;
    private String homeAddress;
    private String lga;
    private String state;
    private String doctorId;
    private String dateCreated;
    @JsonInclude(NON_NULL)
    private String dateUpdated;
}
