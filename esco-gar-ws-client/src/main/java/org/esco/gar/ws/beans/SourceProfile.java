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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 25 avr. 2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sourceProfile")
public class SourceProfile {

	@XmlAttribute
	protected String id;//"RN-EDITEUR-eduMedia"
	@XmlAttribute
	protected String access = "cas";
	@XmlAttribute
	protected String name;//"eduMedia"
	@XmlAttribute
	protected String specificUserContent = "yes";
	@XmlAttribute
	protected String url;

	@XmlElement
	protected Visibility visibility = new Visibility();
	/**
	 * Contructor of the object SourceProfile.java.
	 */
	public SourceProfile() {
		this.visibility.getObliged().add(new Regex());
	}
	/**
	 * Getter of member id.
	 * @return <code>String</code> the attribute id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Setter of attribute id.
	 * @param id the attribute id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}
	/**
	 * Getter of member access.
	 * @return <code>String</code> the attribute access
	 */
	public String getAccess() {
		return access;
	}
	/**
	 * Setter of attribute access.
	 * @param access the attribute access to set
	 */
	public void setAccess(final String access) {
		this.access = access;
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
	 * Getter of member specificUserContent.
	 * @return <code>String</code> the attribute specificUserContent
	 */
	public String getSpecificUserContent() {
		return specificUserContent;
	}
	/**
	 * Setter of attribute specificUserContent.
	 * @param specificUserContent the attribute specificUserContent to set
	 */
	public void setSpecificUserContent(final String specificUserContent) {
		this.specificUserContent = specificUserContent;
	}
	/**
	 * Getter of member url.
	 * @return <code>String</code> the attribute url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Setter of attribute url.
	 * @param url the attribute url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SourceProfile [id=");
		builder.append(id);
		builder.append(", access=");
		builder.append(access);
		builder.append(", name=");
		builder.append(name);
		builder.append(", specificUserContent=");
		builder.append(specificUserContent);
		builder.append(", url=");
		builder.append(url);
		builder.append(", visibility=");
		builder.append(visibility);
		builder.append("]");
		return builder.toString();
	}

}
