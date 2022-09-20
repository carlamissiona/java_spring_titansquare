package com.tunacoder.titansquares.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunacoder.titansquares.models.Titan;
import com.tunacoder.titansquares.repository.TitanRepository;

@Service
public class TitanService implements ITitanService {

	private TitanRepository titan_repo;

	@Autowired
	public TitanService(TitanRepository titan_repo) {
		this.titan_repo = titan_repo;
	}

	@Override
	public Titan createTitan(Titan titan) {
		return titan_repo.save(titan);
	}
 
	@Override 
	public void updateTitan(long id, Titan titan_inparam) {
		Optional<Titan> optionalTitan = titan_repo.findById(id);

		if(optionalTitan.isPresent()) {

			Titan thiscurrent_titan = optionalTitan.get();
			thiscurrent_titan.setName(titan_inparam.getName());
			thiscurrent_titan.setRace(titan_inparam.getRace());
			thiscurrent_titan.setAge(titan_inparam.getAge());
			thiscurrent_titan.setDescription(titan_inparam.getDescription()); 
			titan_repo.save(thiscurrent_titan);

		}
	}

	@Override
	public Iterable<Titan> getTitans() {
		return titan_repo.findAll();
	}

	@Override
	public void deleteTitan(long id) {
		titan_repo.deleteById(id);
	}

	@Override
	public Optional<Titan> getTitanbyId(long id) {
		return titan_repo.findById(id);
	}

	/*
	
				public Titan(Long id, String name, String race, int age, String description) {
				this.id = id;
				this.name = name;
				this.race = race;
				this.age = age;
				this.description = description;
	}
	*/

}