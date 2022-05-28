package io.seamhealthv1.simplecrudapplication.repository;

import io.seamhealthv1.simplecrudapplication.model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctors, String> {

}
