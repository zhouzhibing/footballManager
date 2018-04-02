package web.action;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import game.tools.db.conf.ServerConfig;
import web.action.url.ActionURL;
import web.control.ServerControl;
import web.control.UserControl;
import web.control.entity.Cmd;
import web.control.entity.Key;
import web.data.MysqlControl;
import web.msg.Message;
import web.tools.properties.FootballProperties;

public class ServerAction extends Action
{

	private UserControl userControl;
	private ServerControl serverControl;
	private String mailJson;
	
	
	@Override
	protected void init() 
	{
		userControl = getSessionValue(Key.LOGIN_USER_CONTROL);
		serverControl = new ServerControl(this, userControl);
	}

	
	public String doCmd()
	{
		if(!userControl.isConnection())		//没有连接对应的游戏服务器
		{
			userControl.alert(Message.MSG_SELECT_SERVER);
			return ActionURL.TO_SERVER_LIST_PAGE;
		}
		String cmd = getRequest().getParameter("cmd");
		switch (cmd) 
		{
			case Cmd.CMD_ONLINE:
				return serverControl.online();
			case Cmd.CMD_ADD_RESOURCE:
				return serverControl.addResource(getRequest());
			case Cmd.CMD_SHOW_INFO:
				return serverControl.showInfo(getRequest());
			case Cmd.CMD_EXECUTE_CMD:
				return serverControl.executeCmd(getRequest());
			case Cmd.CMD_UPCONF:
				return serverControl.upConf();
			case Cmd.CMD_UP_CACHE:
				return serverControl.upCache();
			case Cmd.CMD_DIRECT_EXIT_SERVER:
				return serverControl.exitLogicServer();
			case Cmd.CMD_ADD_MAIL:
				 return serverControl.addMail(mailJson);
			case Cmd.CMD_NOTICE:
				return serverControl.noticetList();
			case Cmd.CMD_ADD_EDIT_NOTICE:
				return serverControl.addEditNotice(getRequest());
			default:
				break;
		}
		
		return ActionURL.TO_SERVER_ONLINE_PAGE;
	}

	public String changeConfig()
	{
		ArrayList<ServerConfig> serverConfList = MysqlControl.getServerConfMapper().selectAll();
		
		Map<String, String[]> map = getRequest().getParameterMap();
		
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext())
		{
			String key =  iterator.next();
			String value = map.get(key)[0].toString();
			
			for (ServerConfig serverConfig : serverConfList) 
			{
				if(serverConfig.getKey().equals(key))
				{
					if(!serverConfig.getVal().equals(value))
					{
						serverConfig.setVal(value);
						MysqlControl.getServerConfMapper().updateByPrimaryKey(serverConfig);
					}
				}
			}
		}
		
		MysqlControl.reLoadConfig();
		ServerConfig.initServerConfig(FootballProperties.class, serverConfList);
		
		return ActionURL.TO_SERVER_CONFIG_INFO_PAGE;
	}
	
	
	
	public String getMailJson() {		return mailJson;	}
	public void setMailJson(String mailJson) {		this.mailJson = mailJson;	}	
	
}
