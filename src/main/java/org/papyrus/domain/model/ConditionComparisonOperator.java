package org.papyrus.domain.model;

@SuppressWarnings("unchecked")
public enum ConditionComparisonOperator {
	EQ {
		@Override
		public boolean compare(Object firstParam, Object secondParam) {
			return firstParam.equals(secondParam);
		}
	},
	NEQ {
		@Override
		public boolean compare(Object firstParam, Object secondParam) {
			return !firstParam.equals(secondParam);
		}
	},
	GT {
		@Override
		public boolean compare(Object firstParam, Object secondParam) {
			return ((Comparable) firstParam).compareTo((secondParam)) > 0;
		}
	},
	LT {
		@Override
		public boolean compare(Object firstParam, Object secondParam) {
			return ((Comparable) firstParam).compareTo((secondParam)) < 0;
		}
	};

	public abstract boolean compare(Object firstParam, Object secondParam);
}
