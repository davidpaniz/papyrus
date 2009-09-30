package org.papyrus.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class ExpressionResolverTestCase {
	@Test
	public void testWithSimpleAttribute() {
		Incident incident = new Incident();
		incident.setDescription("a");

		Assert.assertEquals("a", new ExpressionResolver(incident, null).valueOf("#{description}"));
	}

	@Test
	public void testWithSubAttribute() {
		Client client = new Client();
		client.setEmail("a@a.com");
		Incident incident = new Incident();
		incident.setClient(client);

		Assert.assertEquals("a@a.com", new ExpressionResolver(incident, null).valueOf("#{client.email}"));
	}

	@Test
	public void testWithTextAndSelector() {
		Incident incident = new Incident();
		incident.setDescription("a");

		Assert.assertEquals("Bla - a", new ExpressionResolver(incident, null).valueOf("Bla - #{description}"));
	}

	@Test
	public void testWithTextAndSelectorWithTextInTheEnd() {
		Incident incident = new Incident();
		incident.setDescription("a");

		Assert.assertEquals("aTesting", new ExpressionResolver(incident, null).valueOf("#{description}Testing"));
	}

	@Test
	public void testWithTwoExpressionsAndText() {
		Client client = new Client();
		client.setName("David Paniz");
		Incident incident = new Incident();
		incident.setDescription("Testing");
		incident.setClient(client);

		Assert.assertEquals(
				"Client: David Paniz opened incident: Testing",
				new ExpressionResolver(incident, null).valueOf("Client: #{client.name} opened incident: #{description}"));
	}
}