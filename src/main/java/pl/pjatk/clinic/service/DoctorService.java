package pl.pjatk.clinic.service;


import org.springframework.stereotype.Service;
import pl.pjatk.clinic.exception.DoctorException;
import pl.pjatk.clinic.model.Doctor;
import pl.pjatk.clinic.repository.DoctorRepository;

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

    public Optional<Doctor> findById(int id) throws DoctorException {
        if (id < 0) {
            throw new DoctorException("There is no negative IDs");
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

    public void deleteById(int id) throws DoctorException {
        if (doctorRepository.findById(id).isPresent()) {
            doctorRepository.deleteById(id);
        } else {
            throw new DoctorException("There is no such patient to delete");
        }
    }

    public void deleteAll() throws DoctorException {
        if (doctorRepository.findAll().isEmpty()) {
            throw new DoctorException("Repository is empty");
        } else {
            doctorRepository.deleteAll();
        }
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

}
