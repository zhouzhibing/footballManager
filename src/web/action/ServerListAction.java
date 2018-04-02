package web.action;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import web.action.url.ActionURL;
import web.annotation.Module;
import web.control.ServerListControl;
import web.control.UserControl;
import web.control.entity.Key;
import web.control.entity.ModuleData;
import web.data.MysqlControl;
import web.data.entity.SysDbList;
import web.data.entity.SysServerList;
import web.msg.Message;
import web.tools.ProtocolNo;

public class ServerListAction extends Action
{
//	private ServerListControl slControl;
	private String searchName ;
	private SysServerList sl;
	private UserControl userControl;
	

	@Override
	protected void init() {
		onInit();
		
	}	
	
	@Override
	protected void onInit() 
	{
		userControl = (UserControl)getSession().get(Key.LOGIN_USER_CONTROL);
//		slControl = userControl.getServerListControl(this);
	}
	
	@Module(data=ModuleData.QUERY_SERVER)
	public String toServerListPage()
	{
		ArrayList<SysServerList> list = MysqlControl.getSysServerListMapper().selectAll();
		ArrayList<SysDbList> listDb = MysqlControl.getSysDbListMapper().selectAll();
		
		for (SysServerList sysServerList : list) 
		{
			for (SysDbList sysDbList : listDb) 
			{
				if (sysServerList.getDbId() == sysDbList.getId())
					sysServerList.setDbDesc(sysDbList.getDbDesc());
			}
		}
		setSession(Key.SERVER_LIST, list);
		return ActionURL.TO_SERVER_LIST_PAGE;
		
//		return slControl.toServerListPage();
	}
	
	@Module(data=ModuleData.ADD_SERVER)
	public String toAddSl()
	{
		MysqlControl.getSysServerListMapper().insert(sl);
		return toServerListPage();
		
//		return slControl.toAddSl(sl);
	}
	
	@Module(data=ModuleData.EDIT_SERVER)
	public String toEditSlPage()
	{
		int slId = Integer.parseInt(getRequest().getParameter("slId"));
		SysServerList sl = getServerListById(slId);
		
		setSession("editSl", sl);
		return ActionURL.TO_SERVER_EDIT_PAGE;
		
//		return slControl.toEditSlPage();
	}
	
	@Module(data=ModuleData.EDIT_SERVER)
	public String toEditSaveSl()
	{
		MysqlControl.getSysServerListMapper().updateByPrimaryKey(sl);
		alert(Message.MSG_EDIT_OK);
		
		ArrayList<SysServerList> list = (ArrayList<SysServerList>) getSession().get(Key.SERVER_LIST);
		
		for (int i = 0 , size = list.size(); i < size; i++) 
		{
			if (list.get(i).getId() == sl.getId())
			{
				list.set(i , sl);
				break;
			}
		}
		
		return toServerListPage();
		
//		return slControl.toEditSaveSl(sl);
	}
	
	@Module(data=ModuleData.DELETE_SERVER)
	public String toDelSl()
	{
		int slId = Integer.parseInt(getRequest().getParameter("slId"));
		MysqlControl.getSysServerListMapper().deleteByPrimaryKey(slId);
		return toServerListPage();
		
//		return slControl.toDelSl();
	}
	
	@Module(data=ModuleData.CONNECT_SERVER)
	public String toConnServer()
	{
		int slId = Integer.parseInt(getRequest().getParameter("slId"));
		SysServerList serverList = getServerListById(slId);
		SysDbList sysDb = null;
		
		ArrayList<SysDbList> list = getSessionValue(Key.DB_SERVER_LIST);
		if(list == null)
		{
			list =MysqlControl.getSysDbListMapper().selectAll();
			getSession().put(Key.DB_SERVER_LIST, list);
		}

		for (SysDbList sysDbList : list) 
		{
			if(sysDbList.getId() == serverList.getDbId())
			{
				sysDb = sysDbList;
				break;
			}
		}
		
		
		try 
		{
			userControl.connection(serverList , sysDb);
			
			Object result = userControl.sendLogicServer(ProtocolNo.MSG_S2S_ONLINT_COUNT);
			
			if(result != null)
			{
				alert("已经成功连接到【" + serverList.getName() + "】服务器！");
				getSession().put(Key.ONLINE_NUM, result);
   				getSession().put(Key.IN_SERVER, serverList);
   				getSession().put(Key.CacheServerName, serverList.getName());
				return ActionURL.TO_SERVER_ONLINE_PAGE;
			}
			else
			{
				alert("【" + serverList.getName() + "】服务器，连接失败！");
				return toServerListPage();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			alert("【" + serverList.getName() + "】服务器，连接失败！\n " + e.getMessage() );
			return toServerListPage();
		}
		
		
//		return slControl.toConnServer(serverList , sysDb);
	}
	
	private SysServerList getServerListById(int id) 
	{
		ArrayList<SysServerList> list = (ArrayList<SysServerList>) getSession().get(Key.SERVER_LIST);
		for (SysServerList sl : list) 
		{
			if (sl.getId() == id)
				return sl;
		}
		return null;
	}
	
	public String toSearch()
	{
		String s = null;
		try {
			s = new String(searchName.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		ArrayList<SysServerList> list = MysqlControl.getSysServerListMapper().selectByLikeName(searchName);
		getSession().put(Key.SEARCH_SERVER_LIST, list);
		return ActionURL.TO_SERVER_LIST_PAGE;
		
//		return slControl.toSearch(s);
	}

	public String getSearchName() {		return searchName;	}
	public void setSearchName(String searchName) {		this.searchName = searchName;	}
	public SysServerList getSl() {		return sl;	}
	public void setSl(SysServerList sl) {		this.sl = sl;	}

	
}
