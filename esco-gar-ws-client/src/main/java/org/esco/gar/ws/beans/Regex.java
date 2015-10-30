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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 25 avr. 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "regex")
public class Regex {

	@XmlAttribute
	protected String attribute;
	@XmlAttribute
	protected String pattern;
	/**
	 * Contructor of the object Regex.java.
	 */
	public Regex() {
		super();
		this.attribute = "isMemberOf";
		this.pattern = "((esco)|(agri)|(clg37)):Applications:Ressources_Editoriales:GAR:.*";
	}
	/**
	 * Getter of member attribute.
	 * @return <code>String</code> the attribute attribute
	 */
	public String getAttribute() {
		return attribute;
	}
	/**
	 * Setter of attribute attribute.
	 * @param attribute the attribute attribute to set
	 */
	public void setAttribute(final String attribute) {
		this.attribute = attribute;
	}
	/**
	 * Getter of member pattern.
	 * @return <code>String</code> the attribute pattern
	 */
	public String getPattern() {
		return pattern;
	}
	/**
	 * Setter of attribute pattern.
	 * @param pattern the attribute pattern to set
	 */
	public void setPattern(final String pattern) {
		this.pattern = pattern;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Regex [attribute=");
		builder.append(attribute);
		builder.append(", pattern=");
		builder.append(pattern);
		builder.append("]");
		return builder.toString();
	}

}
