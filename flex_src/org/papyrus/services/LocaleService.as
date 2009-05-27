package org.papyrus.services
{
	import org.papyrus.services.Service;
	
	public class LocaleService
	{
		
		public static function saveLocale( locale:String ):void
		{
	    	Service.getLSO().data.locale = locale;
		}

		public static function getLocale():String
		{
			return Service.getLSO().data.locale;
		}
	}
}