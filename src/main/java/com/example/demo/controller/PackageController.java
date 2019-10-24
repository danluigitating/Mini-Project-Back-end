package com.example.demo.controller;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.demo.repository.PackageRepository;
import com.example.demo.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Package;

@RestController
@RequestMapping("/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping(produces = {"application/json"})
    public List<Package> getAll() {
        return packageService.findAll();
    }

    @PostMapping(produces = {"application/json"})
    @ResponseStatus(code = HttpStatus.CREATED)
    public Package add(@RequestBody Package package2) {
        return packageService.save(package2);
    }

    @PatchMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Package> update(@PathVariable Long id, @RequestBody Package package1) {
        Optional<Package> fetchedPackage = packageService.findById(id);
        if (fetchedPackage.isPresent()) {
            Package modifyPackage = fetchedPackage.get();
            modifyPackage.setBookingStatus("Booked");
            modifyPackage.setBookingTime(package1.getBookingTime());
            Package savedPackage = packageService.save(modifyPackage);
            return new ResponseEntity<>(savedPackage, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
