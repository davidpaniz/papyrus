package org.papyrus.testutil;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

/**
 * @author davidpaniz
 * 
 */
public class TestCaseUtils {
	/**
	 * Create a new mockery using ClassImposteriser.INSTANCE
	 * 
	 * @return the mockery instance
	 */
	public static Mockery newMockery() {
		return new Mockery() {
			{
				setImposteriser(ClassImposteriser.INSTANCE);
			}
		};
	}

}
