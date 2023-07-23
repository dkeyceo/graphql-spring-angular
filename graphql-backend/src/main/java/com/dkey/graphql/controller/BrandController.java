package com.dkey.graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.dkey.graphql.entity.Brand;
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
}
