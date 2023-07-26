package com.dkey.graphql.service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkey.graphql.dto.BrandDto;
import com.dkey.graphql.dto.ModelDto;
import com.dkey.graphql.entity.Brand;
import com.dkey.graphql.entity.Model;
import com.dkey.graphql.enums.Country;
import com.dkey.graphql.repository.BrandRepository;
import com.dkey.graphql.repository.ModelRepository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
	
	public Flux<Brand> findAllBrandsFlux(){
		return Flux.fromStream(brandRepository.findAll().stream())
				.delayElements(Duration.ofSeconds(1)).take(10);
	}
	
	public Brand findOneBrand(int id) {
		return brandRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not exists"));
	}
	
	public Mono<Brand> findOneBrandMono(int id) {
		return Mono.justOrEmpty(brandRepository.findById(id))
				.switchIfEmpty(Mono.error(new RuntimeException("brand not found")));
	}
	
	public Brand saveBrand(BrandDto brandDto) {
		Optional<Brand> brandOpt = brandRepository.findByName(brandDto.getName());
		if(brandOpt.isPresent())
			throw new RuntimeException("Brand name already in use");
		Brand brand = Brand.builder().name(brandDto.getName()).country(brandDto.getCountry()).build();
		return brandRepository.save(brand);
	}
	
	public Brand updateBrand(int id, BrandDto brandDto) {
		Brand brand = brandRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not exists"));
		
		Optional<Brand> brandOpt = brandRepository.findByName(brandDto.getName());
		if(brandOpt.isPresent() && brandOpt.get().getId() != id)
			throw new RuntimeException("Brand name already in use");
		
		brand.setName(brandDto.getName());
		brand.setCountry(brandDto.getCountry());
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
	
	public List<Model> findModelsByBrandId(int brandId) {
		Optional<Brand> brandOpt = brandRepository.findById(brandId);
		if(!brandOpt.isPresent())
			throw new RuntimeException("Brand not found");
		
		return modelRepository.findModelsByBrandId(brandId);
	}
	
	public Model findOneModel(int id) {
		return modelRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not exists"));
	}
	
	public Model saveModel(ModelDto modelDto) {
		
		Optional<Model> modelOpt = modelRepository.findByName(modelDto.getName());
		if(modelOpt.isPresent())
			throw new RuntimeException("Model name already in use");
		
		Brand brand = brandRepository.findById(modelDto.getBrandId())
				.orElseThrow(()->new RuntimeException("Id not exists"));
		
		return modelRepository.save(Model.builder().name(modelDto.getName()).brand(brand).build());
	}
	
	public Model updateModel(int id, String name) {
		Optional<Model> modelOpt = modelRepository.findByName(name);
		if(modelOpt.isPresent() && modelOpt.get().getId() != id) {
			throw new RuntimeException("Model name already in use");
		}
		
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
		saveBrand(new BrandDto ("Mers", Country.GER));
		saveBrand(new BrandDto ("BMW", Country.GER));
		saveBrand(new BrandDto ("Jaguar", Country.ENG));
		
		saveModel(new ModelDto (1, "Benz"));
		saveModel(new ModelDto (2, "X5"));
		saveModel(new ModelDto (3, "F-TYPE"));
		
	}

	
}
