package io.seamhealthv1.simplecrudapplication.resources;

import io.seamhealthv1.simplecrudapplication.model.Address;
import io.seamhealthv1.simplecrudapplication.model.Doctors;
import io.seamhealthv1.simplecrudapplication.response.DefaultResponse;
import io.seamhealthv1.simplecrudapplication.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class DoctorResource {

    @Autowired
    private final DoctorService doctorService;

    public DoctorResource(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    /**
     * This method will add a new doctor on each request
     * @param doctor
     * @return (header location with path and doctorId)
     * Note: header location will return a query path with doctor's Id
     */
    @PostMapping(value = "/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createDoctorAccount(@RequestBody Doctors doctor){
        Doctors doctor1 = doctorService.createAccount(doctor);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(doctor1.getDoctorId())
                .toUri();
        //Note: no response body:: response will be in the Headers Location with path
     return ResponseEntity.created(location).build();
    }


    /**
     * this will return all the doctors
     * @return (return type will be a list)
     */
    @GetMapping(value = "/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Doctors>> getDoctorList(){
        List<Doctors> people = doctorService.getAllDoctor();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }


    /**
     * @param (doctorId)
     * @return (Object)
     * this will return doctor by their Id
     */
    @GetMapping(value = "/doctor/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<?> getPeopleById(@PathVariable("doctorId") String doctorId){
        return doctorService.getDoctorById(doctorId);
    }


    /**
     * This method will modify doctors address
     * @param address
     * @return Message
     */
    @PutMapping(value = "/editDoctorsAddress", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDoctorsAddress(@RequestBody Address address){
        doctorService.editAddress(address);
        return ResponseEntity.ok(new DefaultResponse("updated successfully"));
    }

    /**
     * Doctors account will be deleted
     * @param doctorId
     * @return
     */
    @DeleteMapping(value = "/deleteAccount/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDoctorsAccount(@PathVariable("doctorId") String doctorId){
        doctorService.deleteDoctorRecord(doctorId);
        return ResponseEntity.ok(new DefaultResponse("account successfully deleted"));
    }



}
