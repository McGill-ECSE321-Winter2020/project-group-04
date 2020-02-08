package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;

@Entity
public enum PetType{
	DOG,
	CAT,
	FISH,
	REPTILE,
	BIRD,
	SMALL_MAMMAL,
	LARGE_MAMMAL,
	AMPHIBIAN,
	OTHER
}
