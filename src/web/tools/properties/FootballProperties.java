package web.tools.properties;
import java.util.ArrayList;
import java.util.Properties;

import game.tools.db.conf.ServerConfig;
import web.data.MysqlControl;

public class FootballProperties extends game.tools.properties.Properties 
{
	private static String EVENT_NAME;
	private static String GIFT_DB_URL;
	private static String GIFT_DB_USERNAME;
	private static String GIFT_DB_PASS;
	private static String JOURNAL_POINT_DB_URL;
	private static String JOURNAL_POINT_DB_USERNAME;
	private static String JOURNAL_POINT_DB_PASS;
	private static int EXPORT_COUNT;
	private static int PAGE_SIZE;

	static 
	{
		initProperties(FootballProperties.class.getResourceAsStream("/footballManager.properties") , FootballProperties.class);
		
		initProperties();
	}
	
	public static void initProperties()
	{
		ArrayList<ServerConfig> serverConfList = MysqlControl.getServerConfMapper().selectAll();
		ServerConfig.initServerConfig(FootballProperties.class, serverConfList);
	}
	
	public static String getDB_URL() {		return getValue("DB_URL");	}
	public static String getDB_USERNAME() {		return getValue("DB_USERNAME");	}
	public static String getDB_PASS() {		return getValue("DB_PASS");	}


	public static String getEVENT_NAME() {	return EVENT_NAME;	}
	public static String getGIFT_DB_URL() {		return GIFT_DB_URL;	}
	public static String getGIFT_DB_USERNAME() {		return GIFT_DB_USERNAME;	}
	public static String getGIFT_DB_PASS() {		return GIFT_DB_PASS;	}
	public static String getJOURNAL_POINT_DB_URL() {		return JOURNAL_POINT_DB_URL;	}
	public static String getJOURNAL_POINT_DB_USERNAME() {		return JOURNAL_POINT_DB_USERNAME;	}
	public static String getJOURNAL_POINT_DB_PASS() {		return JOURNAL_POINT_DB_PASS;	}
	public static int getPAGE_SIZE() {		return PAGE_SIZE;	}
	public static int getEXPORT_COUNT() {		return EXPORT_COUNT;	}	
	
}
