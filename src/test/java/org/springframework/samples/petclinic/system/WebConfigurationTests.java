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
package org.springframework.samples.petclinic.system;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * Unit tests for {@link WebConfiguration}.
 */
class WebConfigurationTests {

	private WebConfiguration config = new WebConfiguration();

	@Test
	void localeResolverDefaultsToEnglish() {
		LocaleResolver resolver = config.localeResolver();
		Locale locale = resolver.resolveLocale(new MockHttpServletRequest());
		assertThat(locale).isEqualTo(Locale.ENGLISH);
	}

	@Test
	void localeChangeInterceptorUsesLangParam() {
		LocaleChangeInterceptor interceptor = config.localeChangeInterceptor();
		assertThat(interceptor.getParamName()).isEqualTo("lang");
	}

}
