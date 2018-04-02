package web.data;
import java.util.ArrayList;

import game.tools.db.mybatis.MybatisFactoryTools;
import game.tools.db.mybatis.MybatisFactoryToolsObject;
import game.tools.db.mybatis.plush.MybatisSqlTimeInterceptor;
import game.tools.http.HttpHashMap;
import web.action.statistics.JournalPointStatistics;
import web.data.entity.JournalPointDesc;
import web.data.mapper.GiftMapper;
import web.data.mapper.JournalPointDescMapper;
import web.data.mapper.ServerConfMapper;
import web.data.mapper.StatisticsJournalPointCountMapper;
import web.data.mapper.SysDbListMapper;
import web.data.mapper.SysOperateLogMapper;
import web.data.mapper.SysServerListMapper;
import web.data.mapper.SysUserMapper;
import web.tools.properties.FootballProperties;

public class MysqlControl 
{
	/**
	 * 足球游戏后台管理数据源的key ， 足球游戏礼包库的数据源key
	 */
//	private static Object DB_FOOTBALL_MANAGER , GIFT_DB_FOOTBALL_MANAGER;
	
	private static MybatisSqlTimeInterceptor interceptor = new MybatisSqlTimeInterceptor();
	
	private static MybatisFactoryToolsObject DB_FOOTBALL_MANAGER , GIFT_DB_FOOTBALL_MANAGER;
	
	private static ArrayList<JournalPointDesc> JOURNAL_POINT_DESC;
	
	static
	{
		reLoadConfig();
	}
	

	public static void reLoadConfig() 
	{
		initDbFootballManager();
		JournalPointStatistics.getInstance().clearMybatisObject();
		
		if(GIFT_DB_FOOTBALL_MANAGER != null)
		{
			GIFT_DB_FOOTBALL_MANAGER.close();
			GIFT_DB_FOOTBALL_MANAGER = null;
		}
	}
	
	private static void initDbFootballManager()
	{
		if(DB_FOOTBALL_MANAGER != null)
		{
			DB_FOOTBALL_MANAGER.close();
			DB_FOOTBALL_MANAGER = null;
			JOURNAL_POINT_DESC = null;
		}
		
		DB_FOOTBALL_MANAGER = new MybatisFactoryToolsObject(1, 2, 5, 30);
		DB_FOOTBALL_MANAGER.registerMyBatisFactory("web.data.mapper",FootballProperties.getDB_URL(),FootballProperties.getDB_USERNAME() , FootballProperties.getDB_PASS() , interceptor);
		
		if(JOURNAL_POINT_DESC == null)
			JOURNAL_POINT_DESC = MysqlControl.getJournalPointDescMapper().selectAll();
	}
	
	private static void initGiftDbFootballManager()
	{
		if(GIFT_DB_FOOTBALL_MANAGER == null)
		{
			GIFT_DB_FOOTBALL_MANAGER = new MybatisFactoryToolsObject(1, 2, 5, 30);
			GIFT_DB_FOOTBALL_MANAGER.openPrintDbUrl();
			GIFT_DB_FOOTBALL_MANAGER.registerMyBatisFactory("web.data.mapper",FootballProperties.getGIFT_DB_URL(),FootballProperties.getGIFT_DB_USERNAME() , FootballProperties.getGIFT_DB_PASS() , interceptor);
		}
	}
	
	public static JournalPointDesc getJournalPointDesc(String action)
	{
		for (JournalPointDesc journalPointDesc : JOURNAL_POINT_DESC) 
		{
			if(journalPointDesc.getId().equals(action))
				return journalPointDesc;
		}
		return null;
	}

	public static ArrayList<JournalPointDesc> getJOURNAL_POINT_DESCList() {		return JOURNAL_POINT_DESC;	}
	
	public static SysOperateLogMapper getSysOperateLogMapperMapper()	{		return getFootballManager(SysOperateLogMapper.class);	}	
	public static SysUserMapper getSysUserMapper()	{		return getFootballManager(SysUserMapper.class);	}
	public static SysServerListMapper getSysServerListMapper()	{		return getFootballManager(SysServerListMapper.class);	}
	public static GiftMapper getGiftMapper()	{		return getFootballGift(GiftMapper.class);	}
	public static SysDbListMapper getSysDbListMapper()	{		return getFootballManager(SysDbListMapper.class);	}
	
	public static JournalPointDescMapper getJournalPointDescMapper()	{		return getFootballManager(JournalPointDescMapper.class);	}
	public static StatisticsJournalPointCountMapper getStatisticsJournalPointCountMapper()	{		return getFootballManager(StatisticsJournalPointCountMapper.class);	}
	public static ServerConfMapper getServerConfMapper()	{		return getFootballManager(ServerConfMapper.class);	}
	
	private static <T> T getFootballGift(Class clzss ){		initGiftDbFootballManager();		return (T)GIFT_DB_FOOTBALL_MANAGER.getMapper(GIFT_DB_FOOTBALL_MANAGER.getFristSessionKey(),clzss);	}
	private static <T> T getFootballManager(Class clzss )	{		return (T)DB_FOOTBALL_MANAGER.getMapper(DB_FOOTBALL_MANAGER.getFristSessionKey(),clzss);	}

	
	
	
	
	
	
}

