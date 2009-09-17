package org.papyrus.model
{
	[RemoteClass(alias="org.papyrus.domain.model.Client")]
	[Bindable]
	public class Client extends User
	{
		public var company:Company;
	}
}