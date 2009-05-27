<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/extjs/resources/css/ext-all.css" />">
    <script type="text/javascript" src="<c:url value="/extjs/adapter/ext/ext-base.js" />"></script>
    <script type="text/javascript" src="<c:url value="/extjs/ext-all-debug.js" />"></script>
    <style type="text/css">
		html, body {font: normal 11px verdana;}
		#main-panel td {padding:5px;}
		.add-feed {
		    background-image: url(feed-viewer/images/rss_add.gif) !important;
		}
		.remove-feed {
		    background-image: url(feed-viewer/images/rss_delete.gif) !important;
		}
	</style>
	<script type="text/javascript">
	 
	var simple = new Ext.form.FormPanel({
 
 
        standardSubmit: true,
 
 
        frame:true,
        title: 'Register',
 
        width: 350,
        defaults: {width: 230},
        defaultType: 'textfield',
		items: [
			{
	            fieldLabel: 'Name',
	            name: 'urgency.name',
	            allowBlank:false
	        },
	        {
	            fieldLabel: 'Descrição',
	            name: 'urgency.description',
	            allowBlank:false
	        },
			{
                inputType: 'hidden',
                name: 'urgency.id'
            }
 
        ],
        buttons: [{
            text: 'Submit',
            handler: function() {
		simple.getForm().getEl().dom.action = '<c:url value="/urgency.add.logic" />';
	        simple.getForm().getEl().dom.method = 'POST';
                simple.getForm().submit();
            }
        }]
 
 
    });
 
		    	 
	
		var menuRoot = new Ext.tree.TreeNode({
		    expanded: true
		    , text: 'Menu'
		});
		
		var extJsCardPanel = {
		    xtype: 'panel'
		    , title: 'ExtJS.com'
			, items: [simple]
		}
		
		Ext.onReady(function() {
		    var painelCentro = { 
			    xtype: 'tabpanel'
				, region: 'center'
			    , items: [extJsCardPanel]
				, activeTab: 0
			}
		
		    var painelEsquerda = new Ext.tree.TreePanel({
		        xtype: 'panel'
		        , region: 'west'
		        , width: 200
		        , title: 'Menu'
		        , root: menuRoot
		        , collapsible: true
		    });
		
		 
		
		    var viewport = new Ext.Viewport({
		        layout: 'border'
		        , items: [painelEsquerda, painelCentro]
		    });


		    
		});
	</script>
    <title>Application Layout Tutorial</title>
</head>
<body>

</body>
</html>
<!--<c:forEach items="${urgencies}" var="urgency">-->
<!--	<h1>${urgency.name}</h1>-->
<!--</c:forEach>-->
<!--<fmt:message key="urgency" />-->