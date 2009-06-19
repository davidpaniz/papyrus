package org.papyrus.infra.flex.amf;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.IOException;

public class EnumHolder implements Externalizable {
	protected Class<Enum<?>> enumClass;
	protected int ordinal = -1;
	
	public EnumHolder() {}
	
	public EnumHolder(final Enum<?> value) {
    	@SuppressWarnings("unchecked")
    	final Class<Enum<?>> enumClass = (Class<Enum<?>>)value.getClass();
    	this.enumClass = enumClass;
    	this.ordinal   = value.ordinal();
	}
	
	public EnumHolder(final Class<Enum<?>> enumClass) {
		this(enumClass, -1);
	}
	
	public EnumHolder(final Class<Enum<?>> enumClass, final int ordinal) {
		this.enumClass = enumClass;
		this.ordinal   = ordinal;
	}
	
	public Enum<?> enumValue() {
		if (ordinal >= 0 && null != enumClass)
			return enumClass.getEnumConstants()[ordinal];
		else
			return null;
	}
	
    public void writeExternal(final ObjectOutput out) throws IOException {
    	out.writeInt(ordinal);
    }
    
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
    	ordinal = in.readInt();
    }
    
	final private static long serialVersionUID = 9115528827295212944L;
}