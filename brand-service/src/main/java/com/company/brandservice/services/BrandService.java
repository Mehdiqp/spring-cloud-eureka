package com.company.brandservice.services;

import com.company.brandservice.entity.Brand;
import com.company.brandservice.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository repository;

    public BrandService(BrandRepository repository) {
        this.repository = repository;
    }

    public Optional<Brand> getByName(String name){
        return repository.findByName(name);
    }

    public Optional<List<Brand>> getAllBrand(){
        return repository.findAllBrand();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Brand createAndUpdateBrand(Brand brand){
        return repository.save(brand);
    }
}
