package pl.pjatk.clinic.service;


import org.springframework.stereotype.Service;
import pl.pjatk.clinic.model.Doctor;
import pl.pjatk.clinic.repository.DoctorRepository;
import pl.pjatk.clinic.validators.Validator;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> findById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("There is no negative IDs");
        } else {
            return doctorRepository.findById(id);
        }
    }

    public Doctor update(int id, Doctor updatedDoctor) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            doctor.setName(updatedDoctor.getName());
            doctor.setSurname(updatedDoctor.getSurname());
            return doctorRepository.save(doctor);
        } else {
            return null;
        }
    }

    public void deleteById(int id) {
        doctorRepository.deleteById(id);
    }

    public void deleteAll() {
        doctorRepository.deleteAll();
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

}
