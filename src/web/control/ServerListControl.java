package web.control;
import java.util.ArrayList;

import game.share.protocol.CenterProtocol;
import web.action.url.ActionURL;
import web.control.entity.Key;
import web.data.MysqlControl;
import web.data.entity.SysDbList;
import web.data.entity.SysServerList;
import web.msg.Message;
import web.tools.ProtocolNo;

public class ServerListControl extends Control {
//	private UserControl userControl;
//	private SysServerList serverList;
//	private SysDbList sysDb;
//	
//	public ServerListControl(UserControl userControl) 
//	{
//		this.userControl = userControl;
//	}
//
//	public String toServerListPage() {
//		ArrayList<SysServerList> list = MysqlControl.getSysServerListMapper().selectAll();
//		ArrayList<SysDbList> listDb = MysqlControl.getSysDbListMapper().selectAll();
//		for (SysServerList sysServerList : list) {
//			for (SysDbList sysDbList : listDb) {
//				if (sysServerList.getDbId() == sysDbList.getId())
//					sysServerList.setDbDesc(sysDbList.getDbDesc());
//			}
//		}
//		getSession().put(Key.SERVER_LIST, list);
//		return ActionURL.TO_SERVER_LIST_PAGE;
//	}
//
//	public String toSearch(String searchName) {
//		ArrayList<SysServerList> list = MysqlControl.getSysServerListMapper().selectByLikeName(searchName);
//		getSession().put(Key.SEARCH_SERVER_LIST, list);
//		return ActionURL.TO_SERVER_LIST_PAGE;
//	}
//
//	public String toAddSl(SysServerList sl) {
//		MysqlControl.getSysServerListMapper().insert(sl);
//		return toServerListPage();
//	}
//
//	public String toEditSlPage() {
//		int slId = Integer.parseInt(getRequest().getParameter("slId"));
//		SysServerList sl = getServerListById(slId);
//		
//		getSession().put("editSl", sl);
//		return ActionURL.TO_SERVER_EDIT_PAGE;
//	}
//
//	public String toDelSl() {
//		int slId = Integer.parseInt(getRequest().getParameter("slId"));
//		MysqlControl.getSysServerListMapper().deleteByPrimaryKey(slId);
//		return toServerListPage();
//	}
//
//	public String toConnServer(SysServerList serverList , SysDbList sysDb ) 
//	{
//		try 
//		{
//			this.serverList = serverList;
//			this.sysDb = sysDb;
//			
//			userControl.connection(serverList , sysDb);
//			
//			Object result = userControl.sendLogicServer(ProtocolNo.MSG_S2S_ONLINT_COUNT);
//			
//			if(result != null)
//			{
//				alert("已经成功连接到【" + serverList.getName() + "】服务器！");
//				getSession().put(Key.ONLINE_NUM, result);
//   				getSession().put(Key.IN_SERVER, serverList);
//   				getSession().put(Key.CacheServerName, serverList.getName());
//				return ActionURL.TO_SERVER_ONLINE_PAGE;
//			}
//			else
//			{
//				alert("【" + serverList.getName() + "】服务器，连接失败！");
//				return toServerListPage();
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			alert("【" + serverList.getName() + "】服务器，连接失败！\n " + e.getMessage() );
//			return toServerListPage();
//		}
//
//	}
//
//	private SysServerList getServerListById(int id) 
//	{
//		ArrayList<SysServerList> list = (ArrayList<SysServerList>) getSession().get(Key.SERVER_LIST);
//		for (SysServerList sl : list) 
//		{
//			if (sl.getId() == id)
//				return sl;
//		}
//		return null;
//	}
//
//	public String toEditSaveSl(SysServerList sl) {
//		MysqlControl.getSysServerListMapper().updateByPrimaryKey(sl);
//		alert(Message.MSG_EDIT_OK);
//		
//		ArrayList<SysServerList> list = (ArrayList<SysServerList>) getSession().get(Key.SERVER_LIST);
//		
//		for (int i = 0 , size = list.size(); i < size; i++) 
//		{
//			if (list.get(i).getId() == sl.getId())
//			{
//				list.set(i , sl);
//				break;
//			}
//		}
//		
//		return toServerListPage();
//	}
//
//	public SysServerList getServerList() {
//		return serverList;
//	}
//
//	public void setServerList(SysServerList serverList) {
//		this.serverList = serverList;
//	}

}
