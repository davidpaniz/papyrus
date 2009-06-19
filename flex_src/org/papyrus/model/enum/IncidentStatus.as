package org.papyrus.model.enum
{
	[RemoteClass(alias="org.papyrus.domain.model.IncidentStatus")]
	public class IncidentStatus extends EnumBase
	{
		private static const Self:EnumClass = as3_lang::enumOf( IncidentStatus );
		public static const values:Array = Self.values;

		public function IncidentStatus( name:String=null )
		{
			super( Self, name );
		}

		public function intern():IncidentStatus
		{
			return IncidentStatus( super.as3_lang::intern() );
		}

		public static function valueOf( name:String ):IncidentStatus
		{
			return Self.valueMap[name];
		}

		private static function _( name:String ):IncidentStatus
		{
			return IncidentStatus( Self.declare( name ) );
		}

		public static const OPENED:IncidentStatus   = _( "OPENED" );
		public static const CLOSED:IncidentStatus   = _("CLOSED");
		
	}
}