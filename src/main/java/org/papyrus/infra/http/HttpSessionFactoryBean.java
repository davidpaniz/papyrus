/**
 * 
 */
package org.papyrus.infra.http;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author davidpaniz
 * 
 */
public class HttpSessionFactoryBean implements FactoryBean {
	public Object getObject() throws Exception {
		RequestAttributes attributes = RequestContextHolder.currentRequestAttributes();
		return ((ServletRequestAttributes) attributes).getRequest()
				.getSession();
	}

	/**
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	public Class<?> getObjectType() {
		return HttpSession.class;
	}

	/**
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	public boolean isSingleton() {
		return false;
	}
}
