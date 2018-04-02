package web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionServlet;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.web.struts.ActionSupport;

import game.tools.utils.StringTools;
import web.msg.Message;


public abstract class Action extends ActionSupport implements  ServletRequestAware , ServletResponseAware ,SessionAware  , ApplicationAware
{
	private Map<String, Object> session , application;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	protected abstract void init();
	
	@Override
	public void setApplication(Map<String, Object> arg0) {		this.application = arg0;	}
	@Override
	public void setServlet(ActionServlet arg0) 	{		this.servlet = arg0;	}
	@Override
	public void setSession(Map<String, Object> arg0)	{		this.session = arg0;		init();	}
	@Override
	public void setServletResponse(HttpServletResponse arg0)	{		this.response = arg0;	}
	@Override
	public void setServletRequest(HttpServletRequest arg0)	{		this.request = arg0;	}
	
	
	public HttpServletRequest getRequest() {		return request;	}
	public HttpServletResponse getResponse() {		return response;	}
	public Map<String, Object> getSession() {		return session;	}	
	public Map<String, Object> getApplication() {		return application;	}
	
	protected void alert(String msg)	{		this.setSession(Message.MSG, msg);	}	
	
	protected void setSession(String key , Object value)	
	{
		if(session != null)
			this.session.put(key, value);
	}
	
	protected void setSessionValue(String key , Object value)
	{
		setSession(key , value);
	}
	
	protected <T> T getSession(String key)	
	{
		if(session == null)
			return null;
		
		Object result = session.get(key);
		return (T)result;
	}
	
	protected <T> T getSessionValue(String key)
	{
		return getSession(key);
	}
	
	protected <T> T removeSession(String key) 
	{
		return (T)session.remove(key);
	}
	
	protected String getRequeseParameter(String key)	
	{
		if(request == null)
			return null;
		return request.getParameter(key);	
	}
	
	
	protected int getRequeseParameterInteger(String key)	
	{
		if(request == null)
			return -1;
		
		String string = request.getParameter(key);
		
		if(!StringTools.empty(string))
			return Integer.parseInt(string);
		return -1;
	}
}
