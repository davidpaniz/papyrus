package org.papyrus.infra.flex.amf;

import java.io.InputStream;

import flex.messaging.io.PropertyProxyRegistry;
import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.AmfMessageDeserializer;
import flex.messaging.io.amf.AmfTrace;

public class J5AmfMessageDeserializer extends AmfMessageDeserializer {

	public J5AmfMessageDeserializer() {
		super();
	}

	@Override
	public void initialize(SerializationContext context, InputStream in, AmfTrace trace) {
		super.initialize(context, in, trace);
		amfIn = new J5Amf0Input(context);
	}

	static {
		PropertyProxyRegistry.getRegistry()
				.register(EnumHolder.class, new EnumPropertyProxy());

		PropertyProxyRegistry.getRegistry()
				.register(Enum.class, new EnumPropertyProxy());
	}

}
