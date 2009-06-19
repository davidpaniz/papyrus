package org.papyrus.infra.flex.amf;

import flex.messaging.io.BeanProxy;
import flex.messaging.io.SerializationContext;
import flex.messaging.util.ClassUtil;

public class J5BeanProxy extends BeanProxy {

	public J5BeanProxy() {
		super();
	}

	public J5BeanProxy(final Object defaultInstance) {
		super(defaultInstance);
	}

	@Override
	public Object createInstance(final String className) {
		if (className != null && className.length() > 0 && !className.startsWith(">")) {
			final SerializationContext context = getSerializationContext();
			if (context.instantiateTypes) {
				final Class<?> desiredClass = ClassUtil.createClass(className);
				if (Enum.class.isAssignableFrom(desiredClass)) {
					@SuppressWarnings("unchecked")
					final Class<Enum<?>> enumClass = (Class<Enum<?>>) desiredClass;
					return new EnumHolder(enumClass);
				}
			}
		}
		return super.createInstance(className);
	}

	final private static long serialVersionUID = -6338998417427610624L;
}
