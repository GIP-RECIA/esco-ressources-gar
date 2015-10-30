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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 25 avr. 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visibility", propOrder = {
	"allowed",
	"autoSubscribed",
	"obliged"
})
public class Visibility {

	@XmlElementWrapper(name="allowed")
	@XmlElement(name="regex")
	protected List<Regex> allowed = new ArrayList<Regex>();
	@XmlElementWrapper(name="autoSubscribed")
	@XmlElement(name="regex")
	protected List<Regex> autoSubscribed = new ArrayList<Regex>();
	@XmlElementWrapper(name="obliged")
	@XmlElement(name="regex")
	protected List<Regex> obliged = new ArrayList<Regex>();

	/**
	 * Contructor of the object Visibility.java.
	 */
	public Visibility() {
		super();
	}

	/**
	 * Getter of member allowed.
	 * @return <code>List<Regex></code> the attribute allowed
	 */
	public List<Regex> getAllowed() {
		if (allowed == null) {
			allowed = new ArrayList<Regex>();
		}
		return allowed;
	}

	/**
	 * Getter of member autoSubscribed.
	 * @return <code>List<Regex></code> the attribute autoSubscribed
	 */
	public List<Regex> getAutoSubscribed() {
		if (autoSubscribed == null) {
			autoSubscribed = new ArrayList<Regex>();
		}
		return autoSubscribed;
	}

	/**
	 * Getter of member obliged.
	 * @return <code>List<Regex></code> the attribute obliged
	 */
	public List<Regex> getObliged() {
		if (obliged == null) {
			obliged = new ArrayList<Regex>();
		}
		return obliged;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Visibility [allowed=");
		builder.append(allowed);
		builder.append(", autoSubscribed=");
		builder.append(autoSubscribed);
		builder.append(", obliged=");
		builder.append(obliged);
		builder.append("]");
		return builder.toString();
	}


}
