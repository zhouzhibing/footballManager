package web.action.intercept;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import web.control.UserControl;
import web.control.entity.Key;
import web.msg.Message;

public class MyIntercept extends AbstractInterceptor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception 
	{
		Map<String , Object> session = arg0.getInvocationContext().getSession();
		HttpServletRequest request = (HttpServletRequest)arg0.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse)arg0.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		
		UserControl userControl = (UserControl)session.get(Key.LOGIN_USER_CONTROL);
		String uri = request.getRequestURI();
		
		if(uri.indexOf(".jsp") > 0 )
		{
			if(userControl == null && uri.indexOf("login.jsp") < 0)		//不是登录页面
			{
				String msg = (String)session.get(Message.MSG);
				if(msg != Message.MSG_USER_NOT_ENABLE && msg != null)
					session.put(Message.MSG, Message.MSG_ILLEGAL_OPERATION);
				if(userControl == null)
					return "tologin";
			}
		}
		else if(uri.indexOf("doLogin") < 0)
		{
			if(userControl == null && uri.indexOf("login.jsp") < 0)		//不是登录页面
			{
				String msg = (String)session.get(Message.MSG);
				if(msg == null)
					session.put(Message.MSG, Message.MSG_ILLEGAL_OPERATION);
   				return "exit";
			}
		}
		
		
		String result = arg0.invoke();
		return result;
	}
	

}
