package org.papyrus.domain.model;

public enum ConditionLogicalOperator {
	AND {
		@Override
		public boolean compare(boolean oldResult, boolean currentResult) {
			return oldResult && currentResult;
		}
	},
	OR {
		@Override
		public boolean compare(boolean oldResult, boolean currentResult) {
			return oldResult || currentResult;
		}
	};

	abstract boolean compare(boolean oldResult, boolean currentResult);

}
