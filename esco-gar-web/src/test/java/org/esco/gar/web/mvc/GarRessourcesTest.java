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
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.esco.gar.ws.beans.ListeCategories;
import org.esco.gar.ws.beans.ListeRessources;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demogar.gar.ws.RessourceBean;

/**
 * @author GIP RECIA - Julien Gribonvald
 * 10 avr. 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/gar-servlet-test.xml")
//@ContextConfiguration(locations = "/context/gar-servlet.xml")
public class GarRessourcesTest {

	private GarRessourcesController grc;
	/**
	 * Getter of member grc.
	 * @return <code>GarRessourcesController</code> the attribute grc
	 */
	public GarRessourcesController getGrc() {
		return grc;
	}

	/**
	 * Setter of attribute grc.
	 * @param grc the attribute grc to set
	 */
	@Autowired(required=true)
	public void setGrc(GarRessourcesController grc) {
		this.grc = grc;
	}


	/**
	 * Test method for {@link org.esco.gar.ws.client.GARWSClient#getRessources(java.lang.String, java.lang.String)}.
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void testGetRessources() {
		//GarRessourcesController grc = new GarRessourcesController();
		ListeRessources lr = grc.getRessourcesForUser("0455007Y", "F1300m82");
		Assert.assertTrue(lr.isStatut());
		Assert.assertTrue(lr.getRessources().size() >= 2);

		ListeRessources lr2 = grc.getRessourcesForUser("0455006X", "F1300m7r");
		Assert.assertTrue(lr2.isStatut());
		Assert.assertTrue(lr2.getRessources().size() >= 1);

		lr = grc.getRessourcesForUser("0455007Y", "F1300m82");

		ListeCategories lc;
		try {
			lc = grc.listCategories("http://localhost:8080/gar-web/category/0455007Y/F1300m82/",lr);
			Assert.assertNotNull(lc.getSourceProfiles());
			Assert.assertTrue(lc.getSourceProfiles().size() > 0);

			JAXBContext jc = JAXBContext.newInstance(ListeCategories.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(lc, System.out);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<RessourceBean> lrb = grc.organizedRessouces(lr).get("Ressources pour s'entraîner");
		Assert.assertNotNull(lrb);
		Assert.assertTrue(lrb.size() > 0);

	}

}
