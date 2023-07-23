package com.dkey.graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.dkey.graphql.entity.Brand;
import com.dkey.graphql.entity.Model;
import com.dkey.graphql.enums.Country;
import com.dkey.graphql.service.BrandService;

@Controller
public class BrandController {
	@Autowired
	private BrandService brandService;
	
	@QueryMapping
	public List<Brand> findAllBrands(){
		return brandService.findAllBrands();
	}
	
	@QueryMapping
	public Brand findOneBrand(@Argument int id){
		return brandService.findOneBrand(id);
	}
	
	@MutationMapping
	public Brand saveBrand(@Argument String name, @Argument Country country){
		return brandService.saveBrand(name, country);
	}
	
	@MutationMapping
	public Brand updateBrand(@Argument int id, @Argument String name, @Argument Country country){
		return brandService.updateBrand(id, name, country);
	}
	
	@MutationMapping
	public Brand deleteBrand(@Argument int id){
		return brandService.deleteBrand(id);
	}
	
	// MODEL
	
	@QueryMapping
	public List<Model> findAllModels(){
		return brandService.findAllModels();
	}
	
	@QueryMapping
	public Model findOneModel(@Argument int id){
		return brandService.findOneModel(id);
	}
	
	@MutationMapping
	public Model saveModel(@Argument int brandId, @Argument String name){
		return brandService.saveModel(brandId, name);
	}
	
	@MutationMapping
	public Model updateModel(@Argument int id, @Argument String name){
		return brandService.updateModel(id, name);
	}
	
	@MutationMapping
	public Model deleteModel(@Argument int id){
		return brandService.deleteModel(id);
	}
}
