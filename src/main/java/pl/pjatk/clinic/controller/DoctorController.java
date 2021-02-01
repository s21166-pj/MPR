package pl.pjatk.clinic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.clinic.exception.DoctorException;
import pl.pjatk.clinic.model.Doctor;
import pl.pjatk.clinic.service.DoctorService;
import pl.pjatk.clinic.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> findAll() {
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Doctor>> findById(@PathVariable int id) throws DoctorException {
        Optional<Doctor> byId = doctorService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId);
        } else {
            throw new DoctorException("No match found");
        }
    }

    @PostMapping
    public ResponseEntity<Doctor> save(@RequestBody Doctor doctor) {
        Validator validator = new Validator();
        List<String> messages;
        messages = validator.validateDoctor(doctor);
        if (messages.isEmpty()) {
            return ResponseEntity.ok(doctorService.save(doctor));
        } else {
            throw new IllegalArgumentException(messages.toString());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable int id, @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.update(id, doctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws DoctorException {
        doctorService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<List<Doctor>> deleteAll() throws DoctorException {
        doctorService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
