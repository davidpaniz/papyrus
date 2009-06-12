package org.papyrus.utils
{
	import flash.display.DisplayObject;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.core.UIComponent;
	import mx.events.CollectionEvent;
	import mx.events.CollectionEventKind;
	import mx.formatters.NumberFormatter;
	import mx.utils.ObjectUtil;

	public class Utils
	{
		// método default de posicionamento de popups
		public static function positionPopup( popup:UIComponent, reference:UIComponent, positionFunction:Function = null, margin:int = 5 ):void
		{
			if( positionFunction == null )
				defaultPositionFunction( popup, reference )
			else
				positionFunction( popup, reference );

			repositionPopup( popup, margin );
		}
		private static function defaultPositionFunction( popup:UIComponent, reference:UIComponent ):void
		{
			var app:DisplayObject = Application.application as DisplayObject;
			popup.x = reference.getRect( app ).x;
			popup.y = reference.getRect( app ).y + reference.height;
		}

			
		// verifica se a popup passada por parâmetro não ultrapassa os limites da Aplicação, e
		// se sim, reposiciona
		public static function repositionPopup( popup:UIComponent, margin:int = 5 ):void
		{
			var app:DisplayObject = Application.application as DisplayObject;
			if( popup.x + popup.width > app.width )
				popup.x -= popup.x + popup.width - app.width + margin;
			if( popup.y + popup.height > app.height )
				popup.y -= popup.y + popup.height - app.height + margin;
		}

		private static var fmt:NumberFormatter = new NumberFormatter();
		public static function formatNumber( number:Number, precision:Number = 2 ):String
		{
			fmt.precision = precision;
			return fmt.format( number );
		}

		public static function classToObject( klass:Object ):Object
		{
			var obj:Object = new Object();

			var props:Array = ObjectUtil.getClassInfo(klass).properties;
			for( var i:int = 0; i < props.length; i++ )
			{
				var s:String = props[i];
				try {
					obj[s] = klass[s];
				} catch( e:Error ) {
					trace( e );
				}
			}
			return obj;
		}

		public static function createWithId( klass:Class, id:Object ):Object
		{
			var object:Object = new klass();
			object.id = id;

			return object;
		}
		public static function createEmptyDomain( klass:Class, description:String = "--", descriptionField:String = "description" ):Object
		{
			var object:Object = new klass();
			object.id = 0;
			object[ descriptionField ] = description;

			return object;
		}

		/**
		 * Remove um objeto complexo de uma coleção.
		 * @param collection a coleção a ser pesquisado
		 * @param object o objeto a ser encontrado
		 * @param property a propriedade dos objetos a ser comparada
		 * @return se removeu algum item ou não
		 */
		public static function remove( collection:ArrayCollection, object:Object, property:String = "id" ):Boolean
		{
			var count:int = 0;
			for each( var item:Object in collection )
			{
				if( item[ property ] == object[ property ] )
				{
					collection.removeItemAt( count );
					return true;
				}
				count++;
			}
			
			return false;
		}
		
		public static function contains( collection:ArrayCollection, object:Object, property:String = "id" ):Boolean
		{
			for each( var item:Object in collection )
				if( item[ property ] == object[ property ] )
					return true;

			return false;
		}

		/**
		 * Remove todos os itens da coleção A que são comuns à coleção B.
		 * @param toClean a coleção a ter os elementos removidos
		 * @param pivot a coleção que possui os elementos a serem comparados
		 * @param property a propriedade de cada item de ambas as collections que deve ser comparada
		 */
		public static function removeIntersection( toClean:ArrayCollection, pivot:ArrayCollection, property:String = "id" ):void
		{
			for( var i:Number = 0; i < pivot.length; i++ )
			{
				for( var j:Number = toClean.length - 1; j >= 0; j-- )
				{
					if( pivot.getItemAt( i )[ property ] == toClean.getItemAt(j)[ property ] )
					{
						toClean.removeItemAt( j );

						// não deveria ter este break, mas o metodo que chama garante que há
						// apenas um item com determinado id na intersecção
						break;
					}
				}
			}
		}

		/**
		 * Descobre se o objeto passado como parâmetro já existe na coleção. Se sim, faz o update
		 * do índice. Se não, adicione o item à coleção.
		 * @param collection a coleção a ser altedada
		 * @param object o objeto a ser pesquisado
		 * @param property a propriedade dos objetos a ser comparada
		 *
		 */
		public static function setOrInsert( collection:ArrayCollection, object:Object, property:String ):void
		{
			var count:int = 0;
			for each( var item:Object in collection )
			{
				if( item[ property ] == object[ property ] )
				{
					collection.setItemAt( object, count );
					return;
				}
				count++;
			}
			// se chegou aqui é porque não encontrou o objeto
			collection.addItem( object );
		}

		/**
		 * Retorna uma string contendo os itens do ArrayCollection separados por vírgulas ou pelo parâmetro "separator".
		 * O parâmetro "property" define qual propriedade dos objetos contidos no ArrayCollection será usada
		 * na string.
		 *
		 * Poderia usar new Array().join(), mas este método não suporta propriedades aninhadas para a string de resultado.
		 */
		public static function join( a:ArrayCollection, property:String = "description", separator:String = ", " ):String
		{
			var s:String = "";
			for each( var obj:Object in a )
				if( obj[ property ] )
					s += obj[ property ] + separator;

			return s.substr( 0, s.length - separator.length );
		}

		public static function join2( a:ArrayCollection, property1:String, property2:String, separator:String = ", " ):String
		{
			var s:String = "";
			for each( var obj:Object in a )
				if( obj[ property1 ][ property2 ] )
					s += obj[ property1 ][ property2 ] + separator;

			return s.substr( 0, s.length - separator.length );
		}

		public static function addCollectionChangeListener( data:ArrayCollection, handler:Function ):void
		{
			data.removeEventListener( CollectionEvent.COLLECTION_CHANGE, handler );

			data.addEventListener( CollectionEvent.COLLECTION_CHANGE, function( event:CollectionEvent ):void {
	        	if( event.kind == CollectionEventKind.ADD
	        		|| event.kind == CollectionEventKind.REMOVE
	        		|| event.kind == CollectionEventKind.REFRESH )
	        		handler();
	  		} );
		}
		public static function addCollectionChangeListener2( data:ArrayCollection, handler:Function ):void
		{
			data.removeEventListener( CollectionEvent.COLLECTION_CHANGE, handler );

			data.addEventListener( CollectionEvent.COLLECTION_CHANGE, function( event:CollectionEvent ):void {
	        	if( event.kind == CollectionEventKind.ADD
	        		|| event.kind == CollectionEventKind.REMOVE
	        		|| event.kind == CollectionEventKind.REFRESH )
	        		handler( event );
	  		} );
		}

	}
}