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
package org.esco.gar.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jasig.cas.client.util.XmlUtils;
import org.jasig.cas.client.validation.Cas20ProxyTicketValidator;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 8 avr. 2014
 */
public class EscoCas20ProxyTicketValidator extends Cas20ProxyTicketValidator {

	/**
	 * Contructor of the object EscoCas20ProxyTicketValidator.java.
	 */
	public EscoCas20ProxyTicketValidator(String casServerUrlPrefix) {
		super(casServerUrlPrefix);
	}

	/* (non-Javadoc)
	 * @see org.jasig.cas.client.validation.Cas20ServiceTicketValidator#extractCustomAttributes(java.lang.String)
	 */
	@Override
	protected Map extractCustomAttributes(String casxml) {
		String xml = casxml.replaceAll("<cas:attribute name=\".*/>", "");

		final int pos1 = xml.indexOf("</cas:user>");
		final int pos2 = xml.indexOf("<cas:proxies>") > 1 ? xml.indexOf("<cas:proxies>") : xml.indexOf("</cas:authenticationSuccess>");

		if (pos1 == -1 || pos2 == -1) {
			return Collections.EMPTY_MAP;
		}

		final String attributesText = xml.substring(pos1+11, pos2);

		final Map attributes = new HashMap();
		final BufferedReader br = new BufferedReader(new StringReader(attributesText));

		String line;
		final List attributeNames = new ArrayList();
		try {
			while ((line = br.readLine()) != null) {
				final String trimmedLine = line.trim();
				if (trimmedLine.length() > 0) {
					final int leftPos = trimmedLine.indexOf(":");
					final int rightPos = trimmedLine.indexOf(">");
					attributeNames.add(trimmedLine.substring(leftPos+1, rightPos));
				}
			}
			br.close();
		} catch (final IOException e) {
			//ignore
		}

		for (final Iterator iter = attributeNames.iterator(); iter.hasNext();) {
			final String name = (String) iter.next();
			attributes.put(name, XmlUtils.getTextForElement(xml, name));
		}

		return attributes;
	}

	public static void main(String[] args) {
		final String xml = "<cas:serviceResponse xmlns:cas='http://www.yale.edu/tp/cas'>\n<cas:authenticationSuccess>\n<cas:user>F08001ut</cas:user>\n" +
			"\n" + "\n" + "\n" +
			"            <cas:ENTPersonProfils>National_7</cas:ENTPersonProfils><cas:attribute name=\"ENTPersonProfils\" value=\"National_7\"/>\n" +
			"            <cas:displayName>GRIBONVALD Julien</cas:displayName><cas:attribute name=\"displayName\" value=\"GRIBONVALD Julien\"/>\n" +
			"\n" + "\n" + "\n" +


			"<cas:proxies>\n" +

			"<cas:proxy>https://carpe.giprecia.net:8443/esup-lecture/proxy/receptor</cas:proxy>\n" +

			"<cas:proxy>https://carpe.giprecia.net:8443/portail/CasProxyServlet</cas:proxy>\n" +

			"</cas:proxies>\n" +

			"</cas:authenticationSuccess>\n</cas:serviceResponse>\n";

		EscoCas20ProxyTicketValidator ptv = new EscoCas20ProxyTicketValidator("https://test.cas.ent");
		System.out.println(ptv.extractCustomAttributes(xml));

	}

}
