package com.dkey.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dkey.graphql.entity.Model;


@Repository
public interface ModelRepository extends JpaRepository<Model, Integer>{

}