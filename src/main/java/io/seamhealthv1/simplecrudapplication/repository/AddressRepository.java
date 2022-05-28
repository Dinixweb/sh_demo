package io.seamhealthv1.simplecrudapplication.repository;

import io.seamhealthv1.simplecrudapplication.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, String> {
    @Modifying
    @Query("update Address x set x.homeAddress=:homeAddress,x.lga=:lga, x.state=:state, x.dateUpdated =:dateUpdated where x.doctorId =:doctorId")
    void updateAddress(@Param("homeAddress")String homeAddress, @Param("lga")String lga,
                       @Param("state")String state, @Param("doctorId")String doctorId, @Param("dateUpdated")String dateUpdated);
}
