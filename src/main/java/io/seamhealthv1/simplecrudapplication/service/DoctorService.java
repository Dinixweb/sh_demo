package io.seamhealthv1.simplecrudapplication.service;

import io.seamhealthv1.simplecrudapplication.model.Address;
import io.seamhealthv1.simplecrudapplication.model.Doctors;
import io.seamhealthv1.simplecrudapplication.repository.AddressRepository;
import io.seamhealthv1.simplecrudapplication.repository.DoctorRepository;
import io.seamhealthv1.simplecrudapplication.response.DefaultResponse;
import io.seamhealthv1.simplecrudapplication.utility.UtilityFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final AddressRepository addressRepository;

    @Autowired
    private final UtilityFunction utilityFunction;



    /**
     * @param doctorRepository
     * @param addressRepository
     * @param utilityFunction
     */
    public DoctorService(DoctorRepository doctorRepository, AddressRepository addressRepository, UtilityFunction utilityFunction) {
        this.doctorRepository = doctorRepository;
        this.addressRepository = addressRepository;
        this.utilityFunction = utilityFunction;

    }

    /**
     * It will create doctors account with the payload received
     * from resource controller
     * @param doctor
     * @return
     */
    public Doctors createAccount(Doctors doctor){
        String doctorId = utilityFunction.generatedId(); // generate unique id
        String addressId = utilityFunction.generatedId(); // generate unique id
        String createdDate = utilityFunction.generateDate();
        doctor.setDoctorId(doctorId);
        doctor.setAddressId(addressId);
        doctor.getAddress().setAddressId(addressId);
        doctor.getAddress().setDoctorId(doctorId);
        doctor.setAddress(doctor.getAddress());
        doctor.setDateCreated(createdDate);
        doctor.getAddress().setDateCreated(createdDate);
        doctorRepository.save(doctor);
        return doctor;
    }

    /**
     * this will return all records in the
     * database provided is exist
     * @return
     */
    public List<Doctors> getAllDoctor(){
        List<Doctors> doctors = new ArrayList<>();
        doctorRepository.findAll().forEach(doctors::add);
        return doctors;
    }

    /**
     * this will fetch a single doctor based
     * on the id provided
     * @param doctorId
     * @return
     */
    public Optional<?> getDoctorById(String doctorId){
        Optional<Doctors> doctor = doctorRepository.findById(doctorId);
        System.out.println(doctor);
        if(doctor.isEmpty()){
            return Optional.of(new DefaultResponse("no user with such Id"));
        }
        return doctor;
    }

    /**
     * Doctors address will be modified with this method
     * Note: this method uses jpa native query. you can find it
     * in AddressRepository
     * @param address
     */
    @Transactional
    public void editAddress(Address address){
        String updateDate = utilityFunction.generateDate();
        address.setDateUpdated(updateDate);
        addressRepository.updateAddress(address.getHomeAddress(), address.getLga(),address.getState(), address.getDoctorId(), address.getDateUpdated());
    }

    /**
     * this method delete all records belonging to doctor
     * @param doctorId
     */
    public void deleteDoctorRecord(String doctorId){
        doctorRepository.deleteById(doctorId);
    }
}
