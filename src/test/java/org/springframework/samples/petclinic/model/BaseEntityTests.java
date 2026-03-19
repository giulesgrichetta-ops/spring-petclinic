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
package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BaseEntityTests {

	@Test
	void shouldReturnNullIdByDefault() {
		Person entity = new Person();
		assertThat(entity.getId()).isNull();
	}

	@Test
	void shouldSetAndGetId() {
		Person entity = new Person();
		entity.setId(42);
		assertThat(entity.getId()).isEqualTo(42);
	}

	@Test
	void shouldBeNewWhenIdIsNull() {
		Person entity = new Person();
		assertThat(entity.isNew()).isTrue();
	}

	@Test
	void shouldNotBeNewWhenIdIsSet() {
		Person entity = new Person();
		entity.setId(1);
		assertThat(entity.isNew()).isFalse();
	}

}
