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
package org.esco.gar.web.mvc;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.esco.gar.ws.beans.ListeCategories;
import org.esco.gar.ws.beans.ListeRessources;
import org.esco.gar.ws.beans.SourceProfile;
import org.esco.gar.ws.services.IGARRessources;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.demogar.gar.ws.RessourceBean;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 10 avr. 2014
 */
@Controller
public class GarRessourcesController {

	/**LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(GarRessourcesController.class);

	@Autowired(required=true)
	private IGARRessources garWS;

	/**
	 * @return ListeRessources
	 */
	@RequestMapping(value="/auth/ressources",
			produces={"application/xml", "application/json"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ListeRessources listWithJSON() {
		User user = getUser();
		return getRessourcesForUser(user.getUai(), user.getUserId());
	}

	/**
	 * @return ListeCategories
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/auth/categories",
			produces={"application/xml", "application/json"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ListeCategories listCategoriesWithJSON(HttpServletRequest request) throws UnsupportedEncodingException {
		User user = getUser();
		String catUrl = request.getRequestURL().toString().replaceAll(request.getServletPath(),	"") + "/category/" + user.getUai() + "/" + user.getUserId() + "/";
		return listCategories(catUrl, getRessourcesForUser(user.getUai(), user.getUserId()));
	}

	/**
	 * @return ListeCategorie
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/category/{uai}/{userId}/{catname}",
			produces={"application/xml", "application/json"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ListeRessources listCategorieOfNameWithJSON(@PathVariable String catname, @PathVariable String userId, @PathVariable String uai, HttpServletRequest request) throws UnsupportedEncodingException {
		LOG.debug("Request on ressources of category '{}'", catname);
		String cat = URLDecoder.decode(catname, "UTF-8");
		LOG.debug("Category name after decoding '{}'", cat);
		return this.listeRessourcesFromCategory(this.getRessourcesForUser(uai, userId), cat);
	}

	/**
	 * Attempt to validate the proxy ticket supplied to the UserInfo map and
	 * display the result in the main view of the portlet.  If the ticket is
	 * not found or cannot be validated, a short debugging message will be
	 * displayed in the portlet.
	 * @param model
	 * @return String
	 */
	@RequestMapping(value="/auth/ressources")
	public String listWithView(ModelMap model) {
		User user = getUser();
		ListeRessources lr = getRessourcesForUser(user.getUai(), user.getUserId());
		model.put("success", lr.isStatut());
		model.put("ressources", lr.getRessources());
		return "ressources";
	}

	/**
	 * @return ListeCategories
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/auth/categories")
	public String listCategoriesWithView(HttpServletRequest request, ModelMap model) throws UnsupportedEncodingException {
		User user = getUser();
		String catUrl = request.getRequestURL().toString().replaceAll(request.getServletPath(),	"") + "/category/" + user.getUai() + "/" + user.getUserId() + "/";
		ListeCategories lc = listCategories(catUrl, getRessourcesForUser(user.getUai(), user.getUserId()));
		model.put("success", "OK");
		model.put("categories", lc.getSourceProfiles());
		return "categories";
	}


	protected ListeCategories listCategories(String catUrl, ListeRessources lr) throws UnsupportedEncodingException {
		ListeCategories lc = new ListeCategories();
		Map<String, List<RessourceBean>> catmap = organizedRessouces(lr);
		for (String cat : catmap.keySet()) {
			SourceProfile sp = new SourceProfile();
			sp.setId("RN-GAR-" + this.normalize(cat));
			sp.setName(cat);
			sp.setAccess("public");
			sp.setSpecificUserContent("yes");
			sp.setVisibility(lc.getVisibility());
			sp.setUrl(catUrl + URLEncoder.encode(cat + ".xml", "UTF-8"));
			lc.getSourceProfiles().add(sp);
		}

		return lc;
	}

	protected Map <String, List<RessourceBean>> organizedRessouces (final ListeRessources lr) {
		Map<String, List<RessourceBean>> catmap = new TreeMap<String, List<RessourceBean>>();
		if (lr.getRessources() != null) {
			for (RessourceBean rs : lr.getRessources()) {
				if (rs.getTypologieRessource() != null) {
					for (String cat :rs.getTypologieRessource()) {
						if (catmap.containsKey(cat)) {
							catmap.get(cat).add(rs);
						} else {
							ArrayList<RessourceBean> array = new ArrayList<RessourceBean>();
							array.add(rs);
							catmap.put(cat, array);
						}
					}
				}
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug(catmap.toString());
			for (Map.Entry<String, List<RessourceBean>> entry : catmap.entrySet()) {
				LOG.debug("Catégorie : {} , ressources : {}", entry.getKey(), entry.getValue().toString());
			}
		}
		return catmap;
	}

	private ListeRessources listeRessourcesFromCategory(final ListeRessources lr, final String catname) {
		ListeRessources lr2 = new ListeRessources();
		lr2.setMessageDeRetour(lr.getMessageDeRetour());
		lr2.setStatut(lr.getStatut());
		Map<String, List<RessourceBean>> or = this.organizedRessouces(lr);
		List<RessourceBean> lrs = new ArrayList<RessourceBean>();
		if (or != null && or.containsKey(catname))
			lrs = or.get(catname);
		lr2.getRessources().addAll(lrs);
		return lr2;
	}

	/**
	 * @return ListeRessources
	 */
	private User getUser() {
		Assertion assertion = AssertionHolder.getAssertion();
		if (assertion == null) {
			throw new RuntimeException("null assertion in getUserProxyTicketCAS. Check ticketValidationFilter and ticketValidator beans configuration");
		}
		//String ret = assertion.getPrincipal().getProxyTicketFor(serviceUrl);

		String userId = assertion.getPrincipal().getName();

		LOG.info("UserId : {}", userId);

		String uai = (String) assertion.getPrincipal().getAttributes().get("ESCOUAICourant");
		LOG.info("UAI courant : {}", uai);
		LOG.debug("User Attributes : {}", assertion.getPrincipal().getAttributes().toString());

		if (userId.equalsIgnoreCase("F08001ut")) {
			/*Random generator = new Random();
			int i = 5 - generator.nextInt(5);
			String[] uids = {"F1300m71","F1300m75", "F1300m6d", "F1300m6r","F1300m6f"};
			userId = uids[i];*/
			userId="F1300m6r";
			uai = "0455006X";
			LOG.info("===> Change user uid : {}", userId);
		}

		return new User(uai, userId);
	}

	/**
	 * @return ListeRessources
	 */
	protected ListeRessources getRessourcesForUser(final String uai, final String userId) {
		return garWS.getRessources(uai, userId);
	}

	private String normalize(final String chaine) {
		// on normalise la chaine
		String temp =  Normalizer.normalize(chaine, Normalizer.Form.NFC);
		// on remplace les caratères entrelacés
		temp = temp.replaceAll("Æ", "AE");
		temp = temp.replaceAll("æ", "ae");
		temp = temp.replaceAll("Œ", "OE");
		temp = temp.replaceAll("œ", "oe");
		// et on remplace les caractères avec accents par décomposition des caratères :
		// caractère primaire suivi du caractère d'accent et suppression des caractères non ascii
		temp =  Normalizer.normalize(temp, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll("\\s", "");
		return temp;
	}

	/**
	 * Getter of member garWS.
	 * @return <code>GARWSClient</code> the attribute garWS
	 */
	public IGARRessources getGarWS() {
		return garWS;
	}

	/**
	 * Setter of attribute garWS.
	 * @param garWS the attribute garWS to set
	 */
	public void setGarWS(final IGARRessources garWS) {
		this.garWS = garWS;
	}


}
