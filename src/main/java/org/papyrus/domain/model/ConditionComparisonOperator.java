package org.papyrus.domain.model;

public enum ConditionComparisonOperator {
	EQ {
		@Override
		public boolean test(ConditionComparable param1, ConditionComparable param2) {
			return param1.equal(param2);
		}
	},
	NEQ {
		@Override
		public boolean test(ConditionComparable param1, ConditionComparable param2) {
			return !param1.equal(param2);
		}
	},
	GT {
		@Override
		public boolean test(ConditionComparable param1, ConditionComparable param2) {
			return param1.greater(param2);
		}
	},
	LT {
		@Override
		public boolean test(ConditionComparable param1, ConditionComparable param2) {
			return !param1.greater(param2);
		}
	};

	public abstract boolean test(ConditionComparable param1, ConditionComparable param2);
}
