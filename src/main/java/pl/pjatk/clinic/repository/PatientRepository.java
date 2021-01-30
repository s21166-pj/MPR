package pl.pjatk.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.clinic.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
