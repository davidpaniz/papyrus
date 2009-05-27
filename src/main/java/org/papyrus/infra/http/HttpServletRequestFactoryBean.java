/**
 * 
 */
package org.papyrus.infra.http;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author davidpaniz
 * 
 */
public class HttpServletRequestFactoryBean implements FactoryBean {
	/**
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	public Object getObject() throws Exception {
		RequestAttributes attributes = RequestContextHolder.currentRequestAttributes();
		return ((ServletRequestAttributes) attributes).getRequest();
	}

	/**
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	public Class<?> getObjectType() {
		return HttpServletRequest.class;
	}

	/**
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	public boolean isSingleton() {
		return false;
	}
}
