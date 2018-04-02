package web.action;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import web.action.url.ActionURL;
import web.control.ServerListControl;
import web.control.UserControl;
import web.control.entity.Key;
import web.data.MysqlControl;
import web.data.entity.SysDbList;
import web.data.entity.SysServerList;
import web.msg.Message;

public class DbListAction extends Action
{
//	private ServerListControl slControl;
	private String searchName ;
	private SysServerList sl;
	private UserControl userControl;
	private SysDbList sysDbList;
	
	@Override
	protected void init() {
		onInit();
		
	}	
	public DbListAction() 
	{
	}
	
	@Override
	protected void onInit() 
	{
		userControl = (UserControl)getSession().get(Key.LOGIN_USER_CONTROL);
//		slControl = userControl.getServerListControl(this);
	}
	
	public String toDbListPage()
	{
		ArrayList<SysDbList> list =MysqlControl.getSysDbListMapper().selectAll();
		getSession().put(Key.DB_SERVER_LIST, list);
		return ActionURL.TO_DB_LIST_PAGE;
	}
	
	public String toAddSl()
	{
		MysqlControl.getSysDbListMapper().insert(sysDbList);
		return toDbListPage();
	}
	
	public String toEditSlPage() {
		int DbId = Integer.parseInt(getRequest().getParameter("DbId"));
		SysDbList dbl = MysqlControl.getSysDbListMapper().selectByPrimaryKey(DbId);
		getSession().put("editdb", dbl);
		return ActionURL.TO_DB_EDIT_PAGE;
	}
	
	public String toEditSaveSl()
	{
		MysqlControl.getSysDbListMapper().updateByPrimaryKey(sysDbList);
		getSession().put(Message.MSG, Message.MSG_EDIT_OK);
		return toDbListPage();
	}
	
	public String toDelSl()
	{
		int DbId = Integer.parseInt(getRequest().getParameter("DbId"));
		MysqlControl.getSysDbListMapper().deleteByPrimaryKey(DbId);
		return toDbListPage();
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

	public SysDbList getSysDbList() {
		return sysDbList;
	}

	public void setSysDbList(SysDbList sysDbList) {
		this.sysDbList = sysDbList;
	}


	
	
	
}
