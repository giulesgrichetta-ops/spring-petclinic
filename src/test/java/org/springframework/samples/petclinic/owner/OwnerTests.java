/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Owner}.
 */
class OwnerTests {

	private Owner owner;

	@BeforeEach
	void setup() {
		owner = new Owner();
	}

	@Test
	void testAddPetNewPet() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		assertThat(owner.getPets()).hasSize(1);
		assertThat(owner.getPets()).contains(pet);
	}

	@Test
	void testAddPetExistingPetNotAdded() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		pet.setId(1);
		owner.addPet(pet);
		assertThat(owner.getPets()).isEmpty();
	}

	@Test
	void testGetPetByName() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		assertThat(owner.getPet("Buddy")).isEqualTo(pet);
	}

	@Test
	void testGetPetByNameCaseInsensitive() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		assertThat(owner.getPet("buddy")).isEqualTo(pet);
		assertThat(owner.getPet("BUDDY")).isEqualTo(pet);
	}

	@Test
	void testGetPetByNameNoMatch() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		assertThat(owner.getPet("Max")).isNull();
	}

	@Test
	void testGetPetByIdReturnsCorrectPet() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		pet.setId(1);
		assertThat(owner.getPet(1)).isEqualTo(pet);
	}

	@Test
	void testGetPetByIdReturnsNullForNonExistent() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		pet.setId(1);
		assertThat(owner.getPet(999)).isNull();
	}

	@Test
	void testGetPetByIdSkipsNewPets() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		assertThat(owner.getPet(Integer.valueOf(0))).isNull();
	}

	@Test
	void testGetPetByNameIgnoreNewTrue() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		assertThat(owner.getPet("Buddy", true)).isNull();
	}

	@Test
	void testGetPetByNameIgnoreNewFalse() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		assertThat(owner.getPet("Buddy", false)).isEqualTo(pet);
	}

	@Test
	void testAddVisitSuccess() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		pet.setId(1);
		Visit visit = new Visit();
		visit.setDescription("checkup");
		owner.addVisit(1, visit);
		assertThat(pet.getVisits()).contains(visit);
	}

	@Test
	void testAddVisitThrowsWhenPetIdNull() {
		assertThatIllegalArgumentException().isThrownBy(() -> owner.addVisit(null, new Visit()));
	}

	@Test
	void testAddVisitThrowsWhenVisitNull() {
		assertThatIllegalArgumentException().isThrownBy(() -> owner.addVisit(1, null));
	}

	@Test
	void testAddVisitThrowsWhenPetNotFound() {
		assertThatIllegalArgumentException().isThrownBy(() -> owner.addVisit(999, new Visit()));
	}

	@Test
	void testToString() {
		owner.setFirstName("George");
		owner.setLastName("Franklin");
		owner.setAddress("110 W. Liberty St.");
		owner.setCity("Madison");
		owner.setTelephone("6085551023");
		String result = owner.toString();
		assertThat(result).isNotNull();
		assertThat(result).contains("George");
		assertThat(result).contains("Franklin");
		assertThat(result).contains("110 W. Liberty St.");
		assertThat(result).contains("Madison");
		assertThat(result).contains("6085551023");
	}

}
