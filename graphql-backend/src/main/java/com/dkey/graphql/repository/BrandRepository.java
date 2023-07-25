package com.dkey.graphql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dkey.graphql.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
	Optional<Brand> findByName(String name);

}
