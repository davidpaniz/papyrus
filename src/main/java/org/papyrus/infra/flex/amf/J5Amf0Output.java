package org.papyrus.infra.flex.amf;

import java.io.IOException;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf0Output;

public class J5Amf0Output extends Amf0Output {

	public J5Amf0Output(final SerializationContext context) {
		super(context);
	}

	@Override
	public void writeObject(final Object o) throws IOException {
		if (avmPlus && null == avmPlusOutput) {
			avmPlusOutput = new J5Amf3Output(context);
			avmPlusOutput.setOutputStream(out);
			avmPlusOutput.setDebugTrace(trace);
		}
		super.writeObject(o);
	}

}
