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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 25 avr. 2014
 */
@XmlRootElement(name="category")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "category", propOrder = {
		"description",
		"visibility",
		"sourceProfiles"
	})
public class ListeCategories {

	@XmlAttribute
	protected String edit = "all";
	@XmlAttribute
	protected String name = "Ressources du GAR";
	@XmlAttribute
	protected int ttl = 1;

	@XmlElement
	protected String description = "Ressources du GAR";
	@XmlElement
	protected Visibility visibility = new Visibility();
	@XmlElementWrapper(name="sourceProfiles")
	@XmlElement(name="sourceProfile")
	protected List<SourceProfile> sourceProfiles= new ArrayList<SourceProfile>();
	/**
	 * Contructor of the object ListeCategories.java.
	 */
	public ListeCategories() {
		super();
		this.visibility.getObliged().add(new Regex());
	}

	/**
	 * Getter of member edit.
	 * @return <code>String</code> the attribute edit
	 */
	public String getEdit() {
		return edit;
	}

	/**
	 * Setter of attribute edit.
	 * @param edit the attribute edit to set
	 */
	public void setEdit(final String edit) {
		this.edit = edit;
	}

	/**
	 * Getter of member name.
	 * @return <code>String</code> the attribute name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of attribute name.
	 * @param name the attribute name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Getter of member ttl.
	 * @return <code>int</code> the attribute ttl
	 */
	public int getTtl() {
		return ttl;
	}

	/**
	 * Setter of attribute ttl.
	 * @param ttl the attribute ttl to set
	 */
	public void setTtl(final int ttl) {
		this.ttl = ttl;
	}



	/**
	 * Getter of member description.
	 * @return <code>String</code> the attribute description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter of attribute description.
	 * @param description the attribute description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Getter of member visibility.
	 * @return <code>Visibility</code> the attribute visibility
	 */
	public Visibility getVisibility() {
		return visibility;
	}

	/**
	 * Setter of attribute visibility.
	 * @param visibility the attribute visibility to set
	 */
	public void setVisibility(final Visibility visibility) {
		this.visibility = visibility;
	}

	/**
	 * Getter of member sourceProfiles.
	 * @return <code>List<SourceProfile></code> the attribute sourceProfiles
	 */
	public List<SourceProfile> getSourceProfiles() {
		if (sourceProfiles == null) {
			sourceProfiles = new ArrayList<SourceProfile>();
		}
		return sourceProfiles;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListeCategories [edit=");
		builder.append(edit);
		builder.append(", name=");
		builder.append(name);
		builder.append(", ttl=");
		builder.append(ttl);
		builder.append(", description=");
		builder.append(description);
		builder.append(", visibility=");
		builder.append(visibility);
		builder.append(", sourceProfiles=");
		builder.append(sourceProfiles);
		builder.append("]");
		return builder.toString();
	}



}
