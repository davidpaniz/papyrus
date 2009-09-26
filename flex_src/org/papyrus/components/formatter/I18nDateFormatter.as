package org.papyrus.components.formatter
{
	import mx.formatters.DateFormatter;
	
	public class I18nDateFormatter extends DateFormatter
	{
		public function I18nDateFormatter()
		{
			super();
			this.formatString = resourceManager.getString('formatter', 'date_timestamp'); 
		}
	}
}