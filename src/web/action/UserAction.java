package web.action;

import java.util.ArrayList;

import game.tools.utils.DateTools;
import web.action.url.ActionURL;
import web.control.UserControl;
import web.control.entity.Key;
import web.control.entity.StateType;
import web.data.MysqlControl;
import web.data.entity.SysUser;
import web.msg.Message;

public class UserAction extends Action
{
	private String username;
	private String password;
	private String searchName ;
	private UserControl userControl;
	private SysUser user = null;

	public UserAction() 
	{
//		updateAction();
	}
	
	@Override
	protected void init() 
	{
		userControl = getSessionValue(Key.LOGIN_USER_CONTROL);
	}	
	
	
	private void updateAction()
	{
		UserControl uc = (UserControl)getSession().get(user.getUserName());
		if(uc != null)
			uc.setAction(this);
	}
	
	public String toLoginPage()
	{
		return ActionURL.TO_LGOIN_PAGE;
	}
	
	public String doLogin()
	{
		SysUser user = MysqlControl.getSysUserMapper().selectByNameAndPwd(username , password);
		if(user == null)
		{
			getSession().put(Message.MSG,Message.MSG_USER_NAME_OR_PWD_ERR);
			return ActionURL.TO_INDEX_PAGE;
		} 
		
		if(user.getState() == StateType.DISABLE)
		{
			getSession().put(Message.MSG,Message.MSG_USER_NOT_ENABLE);
			return ActionURL.TO_INDEX_PAGE;
		}
		
		if(user != null)
		{
			UserControl uc = (UserControl)getSession().get(user.getUserName());
			if(uc != null)
				userControl = uc;
			else
				userControl = new UserControl(this , user);
				

			userControl.setAction(this);
			
			getSession().put(Key.LOGIN_USER_NAME, user.getUserName());
			getSession().put(Key.LOGIN_USER_CONTROL, userControl);
			user.setLastLoginTime(user.getCreateTime());
			user.setCreateTime(DateTools.getCurrentTimeString());
			MysqlControl.getSysUserMapper().updateByPrimaryKey(user);
			return ActionURL.SUCCESS;
		}
		
		return ActionURL.FAIL;
	}
	
	public String toEditSaveUserPage()
	{
		return userControl.toEditSaveUserPage(user);
	}
	
	public String toEditUserPage()
	{
		return userControl.toEditUserPage();
	}
	
	public String toUserList()
	{
		ArrayList<SysUser> userList = MysqlControl.getSysUserMapper().selectAll();
		getSession().put(Key.SEARCH_LIST, userList);
		
		return ActionURL.TO_USER_INDEX_PAGE;
	}
	
	public String toEditSaveSelfPage()
	{
		return userControl.toEditSaveSelfPage(user);
	}
	
	
	public String toAddUser()
	{
		return userControl.toAddUser(user);
	}
	
	public String toDelUser()
	{
		return userControl.toDelUser();
	}
	
	public String toSearch()
	{
		return userControl.toSearch(searchName);
	}
	public String getUsername() {		return username;	}
	public void setUsername(String username) {		this.username = username;	}
	public String getPassword() {		return password;	}
	public void setPassword(String password) {		this.password = password;	}
	public SysUser getUser() {	return user;}
	public void setUser(SysUser user) {		this.user = user;	}
	public String getSearchName() {		return searchName;	}
	public void setSearchName(String searchName) {		this.searchName = searchName;	}
	
}
