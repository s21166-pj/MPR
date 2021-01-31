package pl.pjatk.clinic.service;


import org.springframework.stereotype.Service;
import pl.pjatk.clinic.model.Doctor;
import pl.pjatk.clinic.model.Patient;
import pl.pjatk.clinic.repository.DoctorRepository;

import java.util.List;

@Service
public class DoctorService {

    DoctorRepository doctorRepository;


    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor save(Doctor doctor) throws IllegalArgumentException {
        if (!NameValidator.isValidName(doctor.getName())) {
            throw new IllegalArgumentException("Name can consist of only letters or is empty");
        } else if (!NameValidator.isValidName(doctor.getSurname())) {
            throw new IllegalArgumentException("Surname can consist of only letters or is empty");
        } else {
            return doctorRepository.save(doctor);
        }
    }
}
