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
package org.esco.gar.ws.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.demogar.gar.ws.ListeRessourcesWSResponseBean;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 10 avr. 2014
 */
@XmlRootElement
public class ListeRessources extends ListeRessourcesWSResponseBean {

	@XmlElement(required = true)
	protected boolean statut;

	/**
	 * Getter of member statut.
	 * @return <code>boolean</code> the attribute statut
	 */
	public boolean isStatut() {
		return statut;
	}

	/**
	 * Setter of attribute statut.
	 * @param statut the attribute statut to set
	 */
	public void setStatut(final boolean statut) {
		this.statut = statut;
	}

	/**
	 * Contructor of the object EscoListeRessources.java.
	 */
	public ListeRessources() {
		super();
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListeRessources [statut=");
		builder.append(statut);
		builder.append(", messageDeRetour=");
		builder.append(messageDeRetour);
		builder.append(", ressources=");
		builder.append(ressources);
		builder.append("]");
		return builder.toString();
	}



}
