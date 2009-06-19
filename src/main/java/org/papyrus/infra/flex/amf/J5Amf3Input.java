package org.papyrus.infra.flex.amf;

import java.io.IOException;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;

public class J5Amf3Input extends Amf3Input {

	public J5Amf3Input(final SerializationContext context) {
		super(context);
		beanProxy = new J5BeanProxy();
	}

	@Override
	public Object readObject() throws ClassNotFoundException, IOException {
		final Object value = super.readObject();
		if (value instanceof EnumHolder) {
			final EnumHolder enumHolder = (EnumHolder) value;
			return enumHolder.enumValue();
		} else
			return value;
	}
}
