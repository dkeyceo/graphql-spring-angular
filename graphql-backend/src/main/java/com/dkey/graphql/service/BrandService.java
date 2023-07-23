package com.dkey.graphql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkey.graphql.entity.Brand;
import com.dkey.graphql.entity.Model;
import com.dkey.graphql.enums.Country;
import com.dkey.graphql.repository.BrandRepository;
import com.dkey.graphql.repository.ModelRepository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private ModelRepository modelRepository;
	
	public List<Brand> findAllBrands(){
		return brandRepository.findAll();
	}
	
	public Brand findOneBrand(int id) {
		return brandRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not exists"));
	}
	
	public Brand saveBrand(String name, Country country) {
		Brand brand = Brand.builder().name(name).country(country).build();
		return brandRepository.save(brand);
	}
	
	public Brand updateBrand(int id, String name, Country country) {
		Brand brand = brandRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not exists"));
		brand.setName(name);
		brand.setCountry(country);
		return brandRepository.save(brand);
	}
	
	public Brand deleteBrand(int id) {
		Brand brand = brandRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not exists"));
		
		brandRepository.delete(brand);
		return brand;
	}
	
	public List<Model> findAllModels(){
		return modelRepository.findAll();
	}
	
	public Model findOneModel(int id) {
		return modelRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not exists"));
	}
	
	public Model saveModel(int brandId, String name) {
		Brand brand = brandRepository.findById(brandId)
				.orElseThrow(()->new RuntimeException("Id not exists"));
		
		return modelRepository.save(Model.builder().name(name).brand(brand).build());
	}
	
	public Model updateModel(int id, String name) {
		Model model = modelRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not exists"));
		model.setName(name);
		return modelRepository.save(model);
	}
	
	public Model deleteModel(int id) {
		Model model = modelRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not exists"));
		
		modelRepository.delete(model);
		return model;
	}
	
	@PostConstruct
	private void loadData() {
		saveBrand("Mers", Country.GER);
		saveBrand("BMW", Country.GER);
		saveBrand("Jaguar", Country.ENG);
		
		saveModel(1, "Benz");
		saveModel(2, "X5");
		saveModel(3, "F-TYPE");
		
	}
}
