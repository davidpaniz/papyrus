package org.papyrus.infra.hibernate;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;

public class PrefixedImprovedNamingStrategy extends ImprovedNamingStrategy implements NamingStrategy {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2936848319768067674L;

	private final String tablePrefix;

	public PrefixedImprovedNamingStrategy(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	@Override
	public String tableName(String tableName) {
		return tablePrefix + addUnderscores(tableName).toUpperCase();
	}

}
