package com.company.brandservice.repository;

import com.company.brandservice.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByName(String name);

    @Query(value = "SELECT B FROM Brand B")
    Optional<List<Brand>> findAllBrand();
}
