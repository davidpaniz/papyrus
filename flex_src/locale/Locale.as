package locale
{
	public class Locale
	{
		private var _location:String;
		private var _description:String;
		
		public function get location():String 
		{
			return _location;
		}
		
		public function get description():String 
		{
			return _description;
		}
		
		public function Locale(location:String, description:String)
		{
			this._location = location;
			this._description = description;
		}

	}
}