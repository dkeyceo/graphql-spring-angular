package com.dkey.graphql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkey.graphql.entity.Brand;
import com.dkey.graphql.enums.Country;
import com.dkey.graphql.repository.BrandRepository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandService {
	@Autowired
	private BrandRepository brandRepository;
	
	public List<Brand> findAllBrands(){
		return brandRepository.findAll();
	}
	
	public Brand findOneBrand(int id) {
		return brandRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not exists"));
	}
	
	public Brand save(String name, Country country) {
		Brand brand = Brand.builder().name(name).country(country).build();
		return brandRepository.save(brand);
	}
	
	@PostConstruct
	private void loadData() {
		save("Mers", Country.GER);
		save("BMW", Country.GER);
		save("Jaguar", Country.ENG);
		
	}
}
