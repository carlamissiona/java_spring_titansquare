package com.tunacoder.titansquares.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tunacoder.titansquares.models.Titan;

@Repository
public interface TitanRepository extends CrudRepository<Titan, Long>{

}