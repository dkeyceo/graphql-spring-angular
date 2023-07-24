package com.dkey.graphql.dto;

import com.dkey.graphql.enums.Country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModelDto {
	private int brandId;
	private String name;
}
