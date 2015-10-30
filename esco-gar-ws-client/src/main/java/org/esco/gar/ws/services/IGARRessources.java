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
package org.esco.gar.ws.services;

import org.esco.gar.ws.beans.ListeRessources;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 28 avr. 2014
 */
public interface IGARRessources {

	/**
	 * @param uai
	 * @param userId
	 * @return <code>ListeRessources</code>
	 */
	@Cacheable(value = "org.esco.gar.ws.services.IGARRessources.getRessources")
	ListeRessources getRessources(String uai, String userId);

}