package org.papyrus.infra.flex.amf;

import java.io.IOException;
import java.util.IdentityHashMap;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Output;

public class J5Amf3Output extends Amf3Output {

	public J5Amf3Output(final SerializationContext context) {
		super(context);
	}

	@Override
	public void reset() {
		super.reset();
		enumTable.clear();
	}

	@Override
	public void writeObject(final Object o) throws IOException {
		if (o instanceof Enum) {
			final Enum<?> e = (Enum<?>) o;
			EnumHolder holder = enumTable.get(o);
			if (holder == null) {
				holder = new EnumHolder(e);
				enumTable.put(e, holder);
			}
			super.writeObject(holder);
		} else
			super.writeObject(o);
	}

	final private IdentityHashMap<Enum<?>, EnumHolder> enumTable = new IdentityHashMap<Enum<?>, EnumHolder>();
}
