package web.control;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.SessionActionMapping;

import com.alibaba.fastjson.JSONObject;

import game.tools.db.mybatis.MybatisFactoryToolsObject;
import game.tools.db.mybatis.plush.MybatisSqlTimeInterceptor;
import game.tools.http.HttpTools;
import game.tools.net.netty4.Netty4Decode;
import game.tools.net.netty4.Netty4Encode;
import game.tools.net.netty4.client.sync.INettyChannelRead;
import game.tools.net.netty4.client.sync.Netty4ClientSync;
import game.tools.utils.DateTools;
import game.tools.utils.Symbol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import web.action.Action;
import web.action.UserAction;
import web.action.url.ActionURL;
import web.control.entity.JobType;
import web.control.entity.Key;
import web.data.MysqlControl;
import web.data.entity.JournalPointDesc;
import web.data.entity.SysDbList;
import web.data.entity.SysServerList;
import web.data.entity.SysUser;
import web.data.football.entity.JournalPoint;
import web.data.football.mapper.JournalPointMapper;
import web.data.mapper.GiftMapper;
import web.data.mapper.JournalPointDescMapper;
import web.msg.Message;

public class UserControl extends Control 
{
	private SysUser user;
	private String cacheURL = null;
	private ServerListControl serverListControl;
	private MybatisFactoryToolsObject mysqlFactoryToolsObject;
	
	private SysServerList serverList;
	
	private String socketKey;
	
	private HashMap<String, Netty4ClientSync> socketMap = new HashMap<>();
	
	public UserControl(UserAction userAction, SysUser user) 
	{
		super(userAction);
		this.user = user;
		
		init();
	}

	private void init() 
	{
//		serverListControl = new ServerListControl(this);
	}

	private boolean checkUser() 
	{
		SysUser u = getLoginUser();
		if (u == null) {
			alert(Message.MSG_ILLEGAL_OPERATION);
			return false;
		}
		return true;
	}

	public String toUserList(SysUser user) 
	{
		// SysUser loginUser = getLoginUser();
		SysUser loginUser = this.user;

		if (loginUser.getJob() != JobType.JOB_ADMIN) {
			alert(Message.MSG_NOT_POWER);
			return ActionURL.TO_SERVER_LIST_PAGE;
		}

		ArrayList<SysUser> userList = MysqlControl.getSysUserMapper().selectAll();
		if (userList.size() > 0) {
			getSession().put(Key.SEARCH_LIST, userList);
			return ActionURL.TO_USER_INDEX_PAGE;
		} else
			return ActionURL.FAIL;
	}

	public String toEditUserPage() 
	{
		String reuslt=getRequest().getParameter("userId");
		if(reuslt==null)
			return ActionURL.FAIL;
		int uId = Integer.valueOf(getRequest().getParameter("userId"));
		SysUser user = MysqlControl.getSysUserMapper().selectByPrimaryKey(uId);
		getSession().put(Key.EDIT_USER, user);
		return ActionURL.TO_USER_EDIT_PAGE;
	}

	/**
	 * 执行编辑保存用户
	 * 
	 * @param user
	 * @return
	 */
	public String toEditSaveUserPage(SysUser user) {
		// System.out.println("user.getUserPwd() = " + user.getUserPwd());
		if (!checkUser())
			return ActionURL.TO_USER_EDIT_PAGE;

		SysUser u = (SysUser) getSession().remove(Key.EDIT_USER);
		if (!user.getUserPwd().equals(Key.DEFAULT_PWD))
			u.setUserPwd(user.getUserPwd());

		u.setFullName(user.getFullName());
		u.setMail(user.getMail());
		u.setState(user.getState());
		u.setJob(user.getJob());

		 MysqlControl.getSysUserMapper().updateByPrimaryKey(u);
		getSession().put(Key.EDIT_USER, u);
		if (u.getId() == this.user.getId())
			this.user = u;

		alert(Message.MSG_EDIT_OK);
		return toUserList(null);
		// //是否把是自己修改禁用
		// SysUser loginUser = (SysUser)getSession().remove(Key.LOGIN_USER);
		// SYSUSER EDITUSER= (SYSUSER)GETSESSION().REMOVE(KEY.EDIT_USER);
		// if(loginUser.getId() == editUser.getId() && editUser.getState() ==
		// StateType.DISABLE)
		// {
		// getSession().put("exit", "ok");
		// return ActionURL.TO_INDEX_PAGE;
		// }
		// else
		// {
		// addMsg(Message.MSG_EDIT_USER_OK);
		// return toUserList(null);
		// }
	}

	public String toEditSaveSelfPage(SysUser user) {
		// System.out.println("user.getUserPwd() = " + user.getUserPwd());
		if (!checkUser())
			return ActionURL.TO_USER_EDIT_PAGE;

		SysUser u = (SysUser) getSession().remove(Key.EDIT_USER);
		if (!user.getUserPwd().equals(Key.DEFAULT_PWD))
			u.setUserPwd(user.getUserPwd());

		u.setFullName(user.getFullName());
		u.setMail(user.getMail());
		u.setState(user.getState());
		u.setJob(user.getJob());

		 MysqlControl.getSysUserMapper().updateByPrimaryKey(u);
		getSession().put(Key.EDIT_USER, u);
		if (u.getId() == this.user.getId())
			this.user = u;

		alert(Message.MSG_EDIT_OK);
		return ActionURL.TO_EDIT_SELF_PATE;
		// //是否把是自己修改禁用
		// SysUser loginUser = (SysUser)getSession().remove(Key.LOGIN_USER);
		// SYSUSER EDITUSER= (SYSUSER)GETSESSION().REMOVE(KEY.EDIT_USER);
		// if(loginUser.getId() == editUser.getId() && editUser.getState() ==
		// StateType.DISABLE)
		// {
		// getSession().put("exit", "ok");
		// return ActionURL.TO_INDEX_PAGE;
		// }
		// else
		// {
		// addMsg(Message.MSG_EDIT_USER_OK);
		// return toUserList(null);
		// }
	}

	public String toAddUser(SysUser user) {
		ArrayList<SysUser> list =  MysqlControl.getSysUserMapper().selectByName(user.getUserName());
		if (list.size() > 0) {
			alert(Message.MSG_ALERY_EXIST_USER);
			return ActionURL.TO_USER_ADD_PAGE;
		}
		user.setCreateTime(DateTools.getCurrentTimeString());
		MysqlControl.getSysUserMapper().insert(user);
		alert(Message.MSG_ADD_USER_OK);
		return toUserList(null);
	}

	public String toSearch(String searchName) {
		ArrayList<SysUser> userList =  MysqlControl.getSysUserMapper().selectByLikeName(searchName);
		getSession().put(Key.SEARCH_LIST, userList);
		return ActionURL.TO_USER_INDEX_PAGE;
	}

	public String toDelUser() {
		String reuslt=getRequest().getParameter("userId");
		if(reuslt==null)
			return ActionURL.FAIL;
		int uId = Integer.valueOf(getRequest().getParameter("userId"));
		 MysqlControl.getSysUserMapper().deleteByPrimaryKey(uId);
		return toUserList(null);
	}

	private SysUser getLoginUser() 
	{
		UserControl uc = (UserControl) getSession().get(Key.LOGIN_USER_CONTROL);
		return uc.getUser();
	}

	public void crowdOutLine(Action action) 
	{
		setAction(action);
		alert(Message.MSG_ALTER_CROWD_OUT_LINE);
		getSession().remove(Key.LOGIN_USER_NAME);
	}
	
	
	public ServerListControl getServerListControl(Action serverListAction) 
	{
		serverListControl.setAction(serverListAction);
		return serverListControl;
	}

	
	public void exit()
	{
		
	}

	public boolean isConnection()
	{
		if(this.serverList == null)
			return false;
		return true;
	}
	
	public <T> T sendLogicServer(Object ...params)
	{
		try 
		{
			Object result = null;
			
			Netty4ClientSync nettyClient = this.socketMap.get(this.socketKey);
			
			if(nettyClient != null)
			{
				Map<String , Object> map = HttpTools.getArrayToMap(params);
				
				System.out.println("Send : " + JSONObject.toJSONString(map));
				
				Object o = nettyClient.send(map);
				  
				System.out.println("Revc : " + o);
				
				result = o;
			}
				
			return (T)result;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
//	public <T> T sendLogicServer(JSONObject o)
//	{
//		try 
//		{
//			Object result = null;
//			
//			Netty4ClientSync nettyClient = this.socketMap.get(this.socketKey);
//			
//			if(nettyClient != null)
//			{
//				Map<String , Object> map = o;
//				
//				System.out.println("Send : " + JSONObject.toJSONString(map));
//				
//				Object o = nettyClient.send(o);
//				  
//				System.out.println("Revc : " + o);
//				
//				result = o;
//			}
//				
//			return (T)result;
//			
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public void connection(SysServerList serverList, SysDbList sysDb) 
	{
		String ip = serverList.getIp();
		int port = serverList.getPort();
		
		this.socketKey = ip + "-" + port;
		
		this.serverList = serverList;
		
		Netty4ClientSync nettyClient = socketMap.get(socketKey);
		if(nettyClient != null)
		{
			try 
			{
				nettyClient.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			socketMap.remove(socketKey);
		}
		
		//创建nettyClient
		nettyClient = new Netty4ClientSync(ip, port, new Netty4Decode()
		{
			protected void decode(ChannelHandlerContext arg0, io.netty.buffer.ByteBuf buffer, java.util.List<Object> out) throws Exception
			{
//				buffer.order(ByteOrder.BIG_ENDIAN);
				
				try 
				{
					if (buffer.readableBytes() < 4) 
						return ;

//					byte[] arr = new byte[buffer.readableBytes()];
//					buffer.getBytes(0,arr);
//					System.out.println("buffer.array = " + Arrays.toString(arr));
					
					buffer.markReaderIndex();
					
					int totalLength = readInt(buffer);
					
					if(buffer.readableBytes() < totalLength)		//数据体长度不对
					{
						buffer.resetReaderIndex() ;
						return ;
					}
					
					byte m = buffer.readByte();
					byte n = buffer.readByte();
					
					if (m != 62 && n != 62)			//如果不是指定的协议头编号,则丢弃该数据
					{
//						buffer.resetReaderIndex();
						buffer.readBytes(new byte[totalLength - 2]);
//						System.out.println(" m = " + m + " n = " + n);
						return ;
					}
					
					byte[] data = new byte[totalLength - 2];
					buffer.readBytes(data, 0, data.length);
					
//					System.out.println("totalLength = " + totalLength);
					
					String content = new String(data, "utf-8").trim();
					JSONObject o = JSONObject.parseObject(content);
					
					out.add(o);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		, 
		new Netty4Encode()
		{
			@Override
			protected void encode(ChannelHandlerContext arg0, Object arg1, ByteBuf arg2) throws Exception 
			{
				try 
				{
					Map<String , Object> map = (Map<String , Object>)arg1;
					String content = JSONObject.toJSONString(map);
					
					byte [] array =  content.getBytes();
					
					int size = array.length + 6;
					ByteBuffer buffer = ByteBuffer.allocate(size).order(ByteOrder.LITTLE_ENDIAN);
					buffer.putInt(array.length + 2);
					buffer.put((byte)0);
					buffer.put((byte)62);
					buffer.put(array);
					
					arg2.writeBytes(buffer.array());
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}, 
		new INettyChannelRead() 
		{
			@Override
			public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception 
			{
				System.out.println("channelRead  msg = " + msg);
			}
		});
		
		socketMap.put(socketKey , nettyClient);
		
		
		connectionMysql(sysDb);
	}
	
	private void connectionMysql(SysDbList sysDb) 
	{
		if(this.mysqlFactoryToolsObject == null)
			this.mysqlFactoryToolsObject = new MybatisFactoryToolsObject(1,2 , 5 , 30);
		
		this.mysqlFactoryToolsObject.close();
		
		this.mysqlFactoryToolsObject.registerMyBatisFactory("web.data.football.mapper", sysDb.getDbUrl(), sysDb.getDbUserName(), sysDb.getDbUserPwd());
		
		initCache();
  	}
	
	private void initCache()
	{
		ArrayList<JournalPointDesc> list = MysqlControl.getJournalPointDescMapper().selectAll();
		getSession().put(Key.JOURNALPOINT_DESC, list);
	}
	
	public static JournalPointDesc getJournalPointDesc(HttpSession session , String idString)
	{
		ArrayList<JournalPointDesc> list = (ArrayList<JournalPointDesc>)session.getAttribute(Key.JOURNALPOINT_DESC);
		
		for (JournalPointDesc journalPointDesc : list) 
		{
			if(journalPointDesc.getId().equals(idString))
				return journalPointDesc;
		}
		return null;
	}
	
	
	private static int readInt(ByteBuf buffer ) 
	{
		byte [] b = new byte[4];
		buffer.readBytes(b);
		
		return byteToInt(b);
	}
	
	private static int byteToInt(byte [] b)
	{
		int res = 0;
		int bLen = b.length;
		if (bLen < 5) {	// int 最大到4个字节
			for (int i = 0; i < bLen; i++) {
				res += (b[i] & 0xFF) << (8 * i);
			}
		}
		return res;
	}
	
	public SysUser getUser() {		return user;	}
	public String getCacheURL() {		return cacheURL;	}
	public void setCacheURL(String cacheURL) {		this.cacheURL = cacheURL;	}	
	public ServerListControl getServerListControl() {		return serverListControl;	}
	public void setServerListControl(ServerListControl serverListControl) {		this.serverListControl = serverListControl;	}

	private <T> T getMapper(Class clzss)	{		return (T)mysqlFactoryToolsObject.getMapper(mysqlFactoryToolsObject.getFristSessionKey(), clzss);	}
	public JournalPointMapper getJournalPointMapper()	{	return getMapper(JournalPointMapper.class);}
	


}
