package web.control.entity;

import java.util.HashMap;
import java.util.Map;

public enum ModuleData{
	UNKNOWN(0,"未知"),
	ONLINE(1,"在线人数"),
	SHOW_LOGIN_ID(2,"当天登录ID"),
	SHOW_CONSUME(3,"当天消费钻石"),
	SHOW_LOGIN_RATE(4,"查看留存率"),
	SHOW_ROLE_LIST(5,"角色列表"),
	SEARCH_ROLE(6,"查询角色信息"),
	SHOW_ONLINE_TIME(7,"查看在线时长"),
	EXPORT_ROLE_LIST(8,"导出角色列表信息"),
	UP_CACHE(9,"更新缓存"),
	ADD(10,"添加资源"),
	SYS_MAIL(11,"发送邮件"),
	SYS_MSG(12,"发送系统消息"),
	MODULE(13,"功能统计"),
	ADD_USER(14,"添加账号"),
	EDIT_USER(15,"编辑账号"),
	DELETE_USER(16,"删除账号"),
	QUERY_USER(17,"查询账号"),
	ADD_SERVER(18,"添加服务器"),
	DELETE_SERVER(19,"删除服务器"),
	EDIT_SERVER(20,"编辑服务器"),
	CONNECT_SERVER(21,"连接服务器"),
	QUERY_SERVER(22,"查询服务器"),
	FORBIDDEN_ACCOUNT(23,"封号"),
	RELIEVE_ACCOUNT(24,"解除封号"),
	FORCE_OFFLINE(25,"强制下线"),
	SEARCH_ROLE_NAME(26,"角色名查找"),
	UP_CONF(27,"加载服务器配置"),
	NOTICE(28,"公告"),
	SAVE_NOTICE(29,"保存公告"),
	NOTICE_CONTENT(30,"公告内容"),
	SAVE_NOTICE_CONTENT(31,"保存公告内容"),
	UPDATE_NOTICE_CONTENT(32,"修改公告内容"),
	DELETE_NOTICE_CONTENT(33,"删除公告内容"),
	UP_FILE(34,"上传公告图片"),
	RECHARGE_LIST(35,"获取充值记录"),
	DAILY_ACCOUNT(36,"每日人数统计");
	private int id;
	private String name;
	
	private ModuleData(int id,String name){
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	private static Map<Integer,String> map = new HashMap<>();
	static{
		ModuleData[] data = ModuleData.values();
		for(ModuleData e : data){
			map.put(e.getId(), e.getName());
		}
	}
	
	public static String getModuleName(int id){
		
		return map.get(id);
	}
}
