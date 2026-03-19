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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class OwnerTests {

	@Test
	void shouldAddNewPet() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Fido");
		owner.addPet(pet);
		assertThat(owner.getPets()).contains(pet);
	}

	@Test
	void shouldNotAddExistingPet() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(1);
		pet.setName("Fido");
		owner.addPet(pet);
		assertThat(owner.getPets()).doesNotContain(pet);
	}

	@Test
	void shouldGetPetByName() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		assertThat(owner.getPet("Buddy")).isEqualTo(pet);
	}

	@Test
	void shouldGetPetByNameCaseInsensitive() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		assertThat(owner.getPet("buddy")).isEqualTo(pet);
	}

	@Test
	void shouldReturnNullForUnknownPetName() {
		Owner owner = new Owner();
		assertThat(owner.getPet("Unknown")).isNull();
	}

	@Test
	void shouldGetPetById() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(7);
		pet.setName("Max");
		owner.getPets().add(pet);
		assertThat(owner.getPet(7)).isEqualTo(pet);
	}

	@Test
	void shouldReturnNullForUnknownPetId() {
		Owner owner = new Owner();
		assertThat(owner.getPet(999)).isNull();
	}

	@Test
	void shouldGetPetByNameIgnoringNew() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Rex");
		owner.addPet(pet);
		assertThat(owner.getPet("Rex", true)).isNull();
		assertThat(owner.getPet("Rex", false)).isEqualTo(pet);
	}

	@Test
	void shouldAddVisitToPet() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(10);
		pet.setName("Bella");
		owner.getPets().add(pet);
		Visit visit = new Visit();
		visit.setDescription("checkup");
		owner.addVisit(10, visit);
		assertThat(pet.getVisits()).contains(visit);
	}

	@Test
	void shouldThrowWhenAddVisitWithNullPetId() {
		Owner owner = new Owner();
		Visit visit = new Visit();
		assertThatThrownBy(() -> owner.addVisit(null, visit)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void shouldThrowWhenAddVisitWithNullVisit() {
		Owner owner = new Owner();
		assertThatThrownBy(() -> owner.addVisit(1, null)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void shouldThrowWhenAddVisitWithInvalidPetId() {
		Owner owner = new Owner();
		Visit visit = new Visit();
		assertThatThrownBy(() -> owner.addVisit(999, visit)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void shouldReturnToString() {
		Owner owner = new Owner();
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main St");
		owner.setCity("Springfield");
		owner.setTelephone("1234567890");
		String result = owner.toString();
		assertThat(result).isNotNull();
		assertThat(result).contains("John");
	}

}
