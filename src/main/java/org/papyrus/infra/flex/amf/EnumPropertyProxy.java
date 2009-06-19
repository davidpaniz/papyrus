package org.papyrus.infra.flex.amf;

import flex.messaging.io.BeanProxy;

public class EnumPropertyProxy extends BeanProxy {

	public EnumPropertyProxy() {
		super();
	}

	public EnumPropertyProxy(final Enum<?> defaultValue) {
		this(new EnumHolder(defaultValue));
	}

	public EnumPropertyProxy(final EnumHolder defaultValue) {
		super(defaultValue);
	}

	/* Not-so-obvious name to overwrite alias */
	@Override
	protected String getClassName(final Object instance) {
		if (instance instanceof EnumHolder) {
			final EnumHolder enumHolder = (EnumHolder) instance;
			return enumHolder.enumClass.getName();
		}
		return super.getClassName(instance);
	}

	final private static long serialVersionUID = -501356438816742342L;
}
