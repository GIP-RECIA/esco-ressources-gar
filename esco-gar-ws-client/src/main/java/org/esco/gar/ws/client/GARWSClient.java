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
package org.esco.gar.ws.client;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.esco.gar.ws.beans.ListeRessources;
import org.esco.gar.ws.services.IGARRessources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demogar.gar.ws.ListeRessourcesWS;
import com.demogar.gar.ws.ListeRessourcesWSResponseBean;
import com.demogar.gar.ws.impl.DefautListeRessourcesWSService;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 13 mars 2014
 */
public class GARWSClient implements IGARRessources {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(GARWSClient.class);

	/** identifiant ENT GAR. */
	private final static String idEnt = "netocentre";

	/**  */
	private ListeRessourcesWS lrWS = new DefautListeRessourcesWSService().getDefautListeRessourcesWSPort();

	/* (non-Javadoc)
	 * @see org.esco.gar.ws.client.IGARRessources#getRessources(java.lang.String, java.lang.String)
	 */
	public ListeRessources getRessources(final String uai, final String userId) {
		try {
			LOG.info("Nouvelle requête de récupération de la liste des ressource pour le user {} dans l'établissement {}", userId, uai);
			ListeRessourcesWSResponseBean resp = lrWS.recupererListeEtiquettes(idEnt, uai, userId);
			LOG.debug(formatSimpleResponse(resp));

			/*if (resp.getRessources() != null && !resp.getRessources().isEmpty()) {
				for (RessourceBean rb : resp.getRessources()) {
					System.out.println(rb.toString());
				}
			}*/

			ListeRessources lr = new ListeRessources();
			lr.setStatut(resp.getStatut().equalsIgnoreCase("OK"));
			lr.setMessageDeRetour(resp.getMessageDeRetour());
			if (resp.getRessources() != null) {
				lr.getRessources().addAll(resp.getRessources());
			}

			StringWriter stringWriter = new StringWriter();

			//JAXBContext context = JAXBContext.newInstance(ListeRessourcesWSResponseBean.class);
			JAXBContext context = JAXBContext.newInstance(ListeRessources.class);

			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			/*QName qName = new QName("http://impl.ws.gar.demogar.com/", "DefautListeRessourcesWSService");
			JAXBElement<ListeRessourcesWSResponseBean> root = new JAXBElement<ListeRessourcesWSResponseBean>(qName, ListeRessourcesWSResponseBean.class, resp);
			m.marshal(root, stringWriter);*/
			m.marshal(lr, stringWriter);
			String result = stringWriter.toString();
			LOG.debug(result);

			return lr;

		} catch (Exception e) {
			LOG.error("Exception: " + e.getMessage());
		}
		return null;
	}

	private String formatSimpleResponse(ListeRessourcesWSResponseBean resp) {
		StringBuffer sb = new StringBuffer();
		sb.append("Réponse [ status = " + resp.getStatut());
		sb.append(", ");
		sb.append("\nmessageRetour = " + resp.getMessageDeRetour());
		sb.append(", ");
		sb.append("\nnb ressources: " + resp.getRessources().size());
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: WSClient &lt;uai&gt; &lt;uid&gt;");
			System.exit(-1);
		}

		IGARRessources wsc = new GARWSClient();
		System.out.println(wsc.getRessources(args[0], args[1]).toString());
	}

}
