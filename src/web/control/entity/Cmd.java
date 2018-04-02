package web.control.entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import web.annotation.Module;

public class Cmd 
{
	
	@Module(data=ModuleData.ONLINE)
	public static final String CMD_ONLINE="online";
	@Module(data=ModuleData.SHOW_LOGIN_ID)
	public static final String CMD_SHOW_LOGIN_ID="showLoginId";
	@Module(data=ModuleData.SHOW_CONSUME)
	public static final String CMD_SHOW_CONSUME="showConsume";
	@Module(data=ModuleData.SHOW_LOGIN_RATE)
	public static final String CMD_SHOW_LOGIN_RATE="showLoginRate";
	@Module(data=ModuleData.SHOW_ROLE_LIST)
	public static final String CMD_SHOW_ROLE_LIST="showRoleList";
	@Module(data=ModuleData.SEARCH_ROLE)
	public static final String CMD_SEARCH_ROLE="searchRole";
	@Module(data=ModuleData.SEARCH_ROLE)
	public static final String CMD_SEARCH_ROLE_IN_USER_ID="searchRoleByInUserId";
	@Module(data=ModuleData.SEARCH_ROLE_NAME)
	public static final String CMD_SEARCH_ROLE_BY_LIKE_NAME = "searchRoleByLikeName";
	@Module(data=ModuleData.SEARCH_ROLE)
	public static final String CMD_SHOW_ROLE_INFO="showRoleInfo";
	@Module(data=ModuleData.SHOW_ONLINE_TIME)
	public static final String CMD_SHOW_ONLINE_TIME="showOnlineTime";
	@Module(data=ModuleData.EXPORT_ROLE_LIST)
	public static final String CMD_EXPORT_ROLE_LIST = "exportRoleList";
	public static final String CMD_DIRECT_EXIT_SERVER="directExit";
	@Module(data=ModuleData.FORBIDDEN_ACCOUNT)
	public static final String CMD_FORBIDDEN_ACCOUNT = "forbiddenAccount";
	@Module(data=ModuleData.FORBIDDEN_ACCOUNT)
	public static final String CMD_RELIEVE_ACCOUNT = "relieveAccount";
	@Module(data=ModuleData.FORCE_OFFLINE)
	public static final String CMD_KICK_PLAY = "kickPlay";
	
	@Module(data=ModuleData.UP_CACHE)
	public static final String CMD_UP_CACHE="upCache";
	@Module(data=ModuleData.ADD)
	public static final String CMD_ADD="add";
	@Module(data=ModuleData.SYS_MAIL)
	public static final String CMD_ADD_MAIL="addMail";
	@Module(data=ModuleData.SYS_MSG)
	public static final String CMD_SEND_SYS_MSG="sendSysMsg";
	@Module(data=ModuleData.MODULE)
	public static final String CMD_MODULE = "module";
	@Module(data=ModuleData.UP_CONF)
	public static final String CMD_UPCONF = "upConf";
	@Module(data=ModuleData.NOTICE)
	public static final String CMD_NOTICE="notice";
	@Module(data=ModuleData.SAVE_NOTICE)
	public static final String CMD_SAVE_NOTICE="saveNotice";
	@Module(data=ModuleData.NOTICE_CONTENT)
	public static final String CMD_NOTICE_CONTENT="noticeContent";
	@Module(data=ModuleData.SAVE_NOTICE_CONTENT)
	public static final String CMD_SAVE_NOTICE_CONTENT="saveNoticeContent";
	@Module(data=ModuleData.UPDATE_NOTICE_CONTENT)
	public static final String CMD_UPDATE_NOTICE_CONTENT="updateNoticeContent";
	@Module(data=ModuleData.DELETE_NOTICE_CONTENT)
	public static final String CMD_DELETE_NOTICE_CONTENT="deleteNoticeContent";
	@Module(data=ModuleData.UP_FILE)
	public static final String CMD_UP_FILE="upFile";
	@Module(data=ModuleData.RECHARGE_LIST)
	public static final String RECHARGE_LIST="rechargeList";
	@Module(data=ModuleData.DAILY_ACCOUNT)
	public static final String DAILY_ACCOUNT="dailyAccount";
	public static final String CMD_ADD_RESOURCE = "addResource";
	public static final String CMD_COST_RESOURCE = "costResource";
	public static final String CMD_SHOW_INFO = "showInfo";
	public static final String CMD_EXECUTE_CMD = "executeCmd";
	public static final String CMD_ADD_EDIT_NOTICE = "addEcitNotice";
	public static final String CMD_JOURNALPOINT_LIST = "JOURNALPOINTLIST";
	public static final String CMD_SHOW_GIFT = "SHOW_GIFT";
	public static final String CMD_GENERATE_GIFT = "GENERATE_GIFT";
	
	
	
	private static Map<String,ModuleData> moduleMap = new HashMap<>();
	
	static{
		Class cls = Cmd.class;
		Cmd cmd;
		try {
			cmd = (Cmd) cls.newInstance();
			Field[] fieldArray = cls.getDeclaredFields();
			for(Field e : fieldArray){
				Module module = e.getAnnotation(Module.class);
				if(module != null){
					moduleMap.put((String)e.get(cmd), module.data());
				}
			}
		}  catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public final static void initModule(){
		
	}
	
	public final static ModuleData getModuleData(String key){
		
		return moduleMap.get(key);
	}
	
	
	public static void main(String[] args) {
		Cmd.initModule();
	}
}
