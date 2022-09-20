package com.tunacoder.titansquares.services;

import java.util.Optional;

import com.tunacoder.titansquares.models.Titan;

public interface ITitanService {

	Titan createTitan(Titan titan);
	
	void updateTitan(long id, Titan Titan);
	
	Iterable<Titan> getTitans();
	
	Optional<Titan> getTitanbyId(long id);
	
	void deleteTitan(long id);
}