package pl.pjatk.clinic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.clinic.exception.PeselException;
import pl.pjatk.clinic.model.Patient;
import pl.pjatk.clinic.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @PostMapping
    public ResponseEntity<Patient> save (@RequestBody Patient patient) throws PeselException {
        return ResponseEntity.ok(patientService.save(patient));
    }
}
