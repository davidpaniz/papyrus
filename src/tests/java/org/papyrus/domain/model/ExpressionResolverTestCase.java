package org.papyrus.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class ExpressionResolverTestCase {
	@Test
	public void testWithSimpleAttribute() {
		Incident incident = new Incident();
		incident.setTitle("a");

		Assert.assertEquals("a", new ExpressionResolver(incident, null).valueOf("#{title}"));
	}

	@Test
	public void testWithSubAttribute() {
		User client = new User();
		client.setEmail("a@a.com");
		Incident incident = new Incident();
		incident.setRequester(client);

		Assert.assertEquals("a@a.com", new ExpressionResolver(incident, null).valueOf("#{requester.email}"));
	}

	@Test
	public void testWithTextAndSelector() {
		Incident incident = new Incident();
		incident.setTitle("a");

		Assert.assertEquals("Bla - a", new ExpressionResolver(incident, null).valueOf("Bla - #{title}"));
	}

	@Test
	public void testWithTextAndSelectorWithTextInTheEnd() {
		Incident incident = new Incident();
		incident.setTitle("a");

		Assert.assertEquals("aTesting", new ExpressionResolver(incident, null).valueOf("#{title}Testing"));
	}

	@Test
	public void testWithTwoExpressionsAndText() {
		User client = new User();
		client.setName("David Paniz");
		Incident incident = new Incident();
		incident.setTitle("Testing");
		incident.setRequester(client);

		Assert.assertEquals("Client: David Paniz opened incident: Testing",
				new ExpressionResolver(incident, null).valueOf("Client: #{requester.name} opened incident: #{title}"));
	}

	@Test
	public void testWithEnum() {
		Incident incident = new Incident();
		incident.setStatus(IncidentStatus.OPENED);

		Assert.assertEquals("OPENED", new ExpressionResolver(incident, null).valueOf("#{status}"));
	}

	@Test
	public void testWithNewValue() {
		Incident incident = new Incident();
		incident.setTitle("bla");

		Assert.assertEquals("bla", new ExpressionResolver(null, incident).valueOf("${title}"));
	}

	@Test
	public void testWithOldAndNewValues() {
		Incident incident = new Incident();
		incident.setTitle("bla");

		Assert.assertEquals("bla = bla", new ExpressionResolver(incident, incident).valueOf("${title} = #{title}"));
	}

	@Test
	public void testWithOldAndNewValuesInAnotherOrder() {
		Incident incident = new Incident();
		incident.setTitle("bla");

		Assert.assertEquals("bla = bla", new ExpressionResolver(incident, incident).valueOf("#{title} = ${title}"));
	}

	@Test
	public void testWithInvalidAttributeShouldReturnTheExpressionAsItIs() {
		Incident incident = new Incident();
		incident.setTitle("bla");

		Assert.assertEquals("#{testando} = bla",
				new ExpressionResolver(incident, incident).valueOf("#{testando} = ${title}"));
	}

}
