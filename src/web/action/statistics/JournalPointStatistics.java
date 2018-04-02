package web.action.statistics;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import com.alibaba.fastjson.JSONObject;
import game.tools.db.mybatis.MybatisFactoryToolsObject;
import game.tools.db.mybatis.plush.MybatisSqlTimeInterceptor;
import game.tools.page.Page;
import game.tools.page.PageWork;
import game.tools.utils.DateTools;
import web.data.MysqlControl;
import web.data.entity.JournalPointDesc;
import web.data.entity.StatisticsJournalPointCount;
import web.data.football.entity.JournalPoint;
import web.data.football.mapper.JournalPointMapper;
import web.tools.properties.FootballProperties;

public class JournalPointStatistics 
{
	private static final JournalPointStatistics JPS = new JournalPointStatistics();
	
	private JournalPointStatistics() { }
	
	public static JournalPointStatistics getInstance()	{	return JPS;	}
	
	private MybatisFactoryToolsObject mybatisObject;
	
	private int PAGE_SIZE = 100;
	
	private HashMap<String ,Object> session = new HashMap<String ,Object>();
	
	private JournalPointMapper getJournalPointMapper() 
	{
		init();
		
		if(mybatisObject != null)
			return mybatisObject.getMapper(mybatisObject.getFristSessionKey(), JournalPointMapper.class);
		return null;
	}
	
	private void init()
	{
		if(mybatisObject == null)
		{
			mybatisObject = new MybatisFactoryToolsObject(1,2 , 5 , 30);
			mybatisObject.registerMyBatisFactory("web.data.football.mapper",FootballProperties.getJOURNAL_POINT_DB_URL(),
					FootballProperties.getJOURNAL_POINT_DB_USERNAME() , FootballProperties.getJOURNAL_POINT_DB_PASS() , new MybatisSqlTimeInterceptor());
		}
	}
	
	public void clearMybatisObject()
	{
		if(this.mybatisObject != null)
			this.mybatisObject.close();
		this.mybatisObject = null;
	}
	
	public void statistics() 
	{
//		String startDate = DateTools.getCurrentDateString().split(" ")[0].replaceAll("-", "");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);			//昨天的时间
		String startDate = DateTools.getCurrentDateString(calendar.getTimeInMillis()).split(" ")[0].replaceAll("-", "");
		
		statistics(startDate);
	}
	
	public void statistics(String startDate) 
	{
		
		ArrayList<JournalPoint> jpList = new ArrayList<>();
		jpList.add(new JournalPoint());
		
		HashMap<String ,Integer> jpCountMap = new HashMap<String ,Integer>();
		
//		for (JournalPointDesc journalPointDesc : jpDesclist) 
//		{
//			int count = getJournalPointMapper().selectByDateAndAction(startDate, journalPointDesc.getId());
//			
//			String keyName = journalPointDesc.getActionName() + "(" + journalPointDesc.getId() + ")";
//			
//			jpCountMap.put(keyName, count);
//		}
		
		while(jpList != null && jpList.size() != 0)
		{
			jpList = (ArrayList<JournalPoint>)Page.selectByPageNo(session, true, PAGE_SIZE, new PageWork() {			//数据库中查询
				@Override
				public Object selectPageNo(int pageNo, int pageSize) 
				{
					return getJournalPointMapper().selectByDate(startDate, pageNo, pageSize);
				}
			});
			
			if(jpList != null )
			{
				String keyName = null;
				for (JournalPoint journalPoint : jpList) 		//统计数量
				{
					JournalPointDesc desc = MysqlControl.getJournalPointDesc(journalPoint.getAction());
					
					if(desc != null)
					{
						keyName = desc.getActionDesc() + "(" + journalPoint.getAction() + ")";
						
						Integer count = jpCountMap.get(keyName);
						if(count == null)
							count = new Integer(0);
						
						count ++;
						
						jpCountMap.put(keyName, count);
					}
				}
			}
			else
			{
//				System.out.println();
			}
		}
		
		Page.clear(session);
		
		StatisticsJournalPointCount sjpc = MysqlControl.getStatisticsJournalPointCountMapper().selectByDate(startDate);
		
		String jsCountString = JSONObject.toJSONString(jpCountMap, true).replaceAll("\n", "<br/>") ;
		if(sjpc == null)
		{
			sjpc = new StatisticsJournalPointCount();
			sjpc.setDate(startDate);
			sjpc.setJpCount(jsCountString);
			sjpc.setTime(System.currentTimeMillis());
			
			MysqlControl.getStatisticsJournalPointCountMapper().insert(sjpc);
		}
		else
		{
			sjpc.setJpCount(jsCountString);
			sjpc.setTime(System.currentTimeMillis());
			
			MysqlControl.getStatisticsJournalPointCountMapper().updateByPrimaryKey(sjpc);
		}
		
//		System.out.println(JSONObject.toJSONString(jpCountMap));
	}
	
}
