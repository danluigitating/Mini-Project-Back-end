package com.example.demo.service;

import com.example.demo.model.Package;
import com.example.demo.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    public List<Package> findAll() {
        return packageRepository.findAll();
    }

    public Package save(Package package2) {
        return packageRepository.save(package2);
    }

    public Optional<Package> findById(Long id) {
        return packageRepository.findById(id);
    }
}
