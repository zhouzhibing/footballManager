package web.action.intercept;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import web.action.OperateLogAction;
import web.annotation.Module;
import web.control.entity.Cmd;
import web.control.entity.Key;
import web.control.entity.ModuleData;
import web.data.MysqlControl;
import web.data.entity.OperateLog;

public class OperateLogIntercept extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation action) throws Exception {
		createOperateLog(action);
		return action.invoke();
	}

	public void createOperateLog(ActionInvocation action){
		HttpServletRequest request = ServletActionContext.getRequest();
		String url = request.getServletPath();
		Map<String,String[]> map = request.getParameterMap();
		String param = JSON.toJSONString(map);
		//过长的就不记录了
		if (param.getBytes().length <= 1024 && !isFilter(action)) {
			OperateLog log = new OperateLog();
			log.setIp(getIpAddr(request));
			log.setPara(param);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			log.setTime(sdf.format(new Date()));
			log.setUrl(url);
			Map<String , Object> session = action.getInvocationContext().getSession();
			log.setUserName((String)session.get(Key.LOGIN_USER_NAME));
			String[] cmdArray = map.get("cmd");
			log.setModuleId(ModuleData.UNKNOWN.getId());
			if(cmdArray!=null && cmdArray.length > 0){
				String key = cmdArray[0].split(":")[0];
				ModuleData data = Cmd.getModuleData(key);
				if(data != null){
					log.setModuleId(data.getId());
				}
			}
			else{
				String methods = action.getProxy().getMethod();
				try {
					Method method = action.getAction().getClass().getDeclaredMethod(methods, null);
					Module module = method.getAnnotation(Module.class);
					if(module != null){
						log.setModuleId(module.data().getId());
					}
				}  catch (Exception e) {
					e.printStackTrace();
				}
			}
			
//			System.out.println("log " + JSONObject.toJSONString(log));
   			MysqlControl.getSysOperateLogMapperMapper().insert(log);
		}
	}
	
	public boolean isFilter(ActionInvocation action){
		if(action.getAction() instanceof OperateLogAction){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取Ip地址
	 * @return
	 */
	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
