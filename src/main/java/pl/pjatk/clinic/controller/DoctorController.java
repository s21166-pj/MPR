package pl.pjatk.clinic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.clinic.exception.PeselException;
import pl.pjatk.clinic.model.Doctor;
import pl.pjatk.clinic.model.Patient;
import pl.pjatk.clinic.service.DoctorService;
import pl.pjatk.clinic.service.PatientService;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<Doctor> save(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.save(doctor));
    }

}
