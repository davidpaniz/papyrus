package org.papyrus.infra.flex.endpoints;

import org.papyrus.infra.flex.amf.J5AmfMessageDeserializer;
import org.papyrus.infra.flex.amf.J5AmfMessageSerializer;

import flex.messaging.endpoints.AMFEndpoint;

public class J5AMFEndpoint extends AMFEndpoint {

	public J5AMFEndpoint() {
		this(false);
	}

	public J5AMFEndpoint(final boolean enableManagement) {
		super(enableManagement);
		deserializerClass = J5AmfMessageDeserializer.class;
		serializerClass = J5AmfMessageSerializer.class;
	}
}
