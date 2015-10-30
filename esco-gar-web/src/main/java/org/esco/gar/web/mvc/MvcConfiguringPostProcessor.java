/**
 * Copyright (C) 2014 GIP RECIA http://www.recia.fr
 * @Author (C) 2014 Julien Gribonvald - julien.gribonvald@recia.fr
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 				http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 *
 */
package org.esco.gar.web.mvc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 8 avr. 2014
 */
public class MvcConfiguringPostProcessor implements BeanPostProcessor{

	/**
	 * Enable pretty print on any bean of type
	 * {@link MappingJacksonHttpMessageConverter} or
	 * {@link MappingJackson2HttpMessageConverter}.
	 */
	public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException 	{
		if (bean instanceof HttpMessageConverter<?>)

			if (bean instanceof MappingJacksonHttpMessageConverter) {
				((MappingJacksonHttpMessageConverter) bean).setPrettyPrint(true);
			}
			else if (bean instanceof MappingJackson2HttpMessageConverter) {
				((MappingJackson2HttpMessageConverter) bean).setPrettyPrint(true);
			}

		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// Nothing to do
		return bean;
	}

}
