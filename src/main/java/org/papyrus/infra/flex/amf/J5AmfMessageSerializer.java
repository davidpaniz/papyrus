package org.papyrus.infra.flex.amf;

import java.io.OutputStream;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.AmfMessageSerializer;
import flex.messaging.io.amf.AmfTrace;

public class J5AmfMessageSerializer extends AmfMessageSerializer {

	public J5AmfMessageSerializer() {
		super();
	}

	@Override
	public void initialize(SerializationContext context, OutputStream out, AmfTrace trace) {
		super.initialize(context, out, trace);
		amfOut = new J5Amf0Output(context);
	}
}
