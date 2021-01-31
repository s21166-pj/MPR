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
        if (doctor.getName().matches("[a-zA-Z]+") && doctor.getSurname().matches("[a-zA-Z]+")) {
            return doctorRepository.save(doctor);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
