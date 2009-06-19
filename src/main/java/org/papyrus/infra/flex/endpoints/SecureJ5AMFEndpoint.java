package org.papyrus.infra.flex.endpoints;

import org.papyrus.infra.flex.amf.J5AmfMessageDeserializer;
import org.papyrus.infra.flex.amf.J5AmfMessageSerializer;

import flex.messaging.endpoints.SecureAMFEndpoint;

public class SecureJ5AMFEndpoint extends SecureAMFEndpoint {

	public SecureJ5AMFEndpoint() {
		this(false);
	}

	public SecureJ5AMFEndpoint(final boolean enableManagement) {
		super(enableManagement);
		deserializerClass = J5AmfMessageDeserializer.class;
		serializerClass = J5AmfMessageSerializer.class;
	}
}
