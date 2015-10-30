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

/**
 * @author GIP RECIA - Julien Gribonvald
 * 30 avr. 2014
 */
public class User {

	/**
	 *
	 */
	private String uai;

	/**
	 *
	 */
	private String userId;

	/**
	 * Contructor of the object User.java.
	 * @param uai
	 * @param userId
	 */
	public User(final String uai, final String userId) {
		super();
		this.uai = uai;
		this.userId = userId;
	}

	/**
	 * Getter of member uai.
	 * @return <code>String</code> the attribute uai
	 */
	public String getUai() {
		return uai;
	}

	/**
	 * Setter of attribute uai.
	 * @param uai the attribute uai to set
	 */
	public void setUai(final String uai) {
		this.uai = uai;
	}

	/**
	 * Getter of member userId.
	 * @return <code>String</code> the attribute userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Setter of attribute userId.
	 * @param userId the attribute userId to set
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}



}
