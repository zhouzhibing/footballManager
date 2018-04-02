package web.action;

import java.util.ArrayList;

import game.tools.utils.DateTools;
import game.tools.utils.StringTools;
import web.action.url.ActionURL;
import web.control.ServerListControl;
import web.control.UserControl;
import web.control.entity.Const;
import web.control.entity.Key;
import web.data.football.entity.JournalPoint;
import web.data.football.mapper.JournalPointMapper;
import web.msg.Message;
import web.tools.properties.FootballProperties;

public class JournalPointAction extends Action 
{

	private UserControl userControl;
	
	@Override
	protected void init() 
	{
		userControl = getSessionValue(Key.LOGIN_USER_CONTROL);
	}

	public String journalpointList() 
	{
		if(!userControl.isConnection())		//没有连接对应的游戏服务器
		{
			userControl.alert(Message.MSG_SELECT_SERVER);
			return ActionURL.TO_SERVER_LIST_PAGE;
		}
		
		JournalPointMapper mapper = userControl.getJournalPointMapper();
		
		String startDate = getRequest().getParameter("startDate");
		String pageNoString = getRequest().getParameter("pageNo");
		
		int pageNo = 0;
		int maxPageNo = 0;
		int pageSize = FootballProperties.getPAGE_SIZE();
		
		if(StringTools.empty(startDate))
		{
			startDate = getSessionValue(Key.SEARCH_START_DATE);
			
			if(StringTools.empty(startDate))
			{
				int [] dateArray = DateTools.getCalenderArray();
				
				startDate = dateArray[0] + "";
				//月
				if(dateArray[1] < 10)				
					startDate += "0" + dateArray[1];
				else
					startDate += dateArray[1] + "";
				
				//日
				if(dateArray[2] < 10)				
					startDate += "0" + dateArray[2];
				else
					startDate += dateArray[2] + "";
			}
		}
		
		if(!StringTools.empty(pageNoString))
			pageNo = Integer.parseInt(pageNoString);
		
		if(pageNo < 0)
		{
			alert("别闹，这已经是第一页了。");
			return ActionURL.TO_JOURNAL_POINT_PATE;
		}
		
		if(pageNo <= 0)
		{
			setSessionValue("statmap", null);
			setSessionValue(Key.JOURNALPOINT_LIST_PAGE_NO , pageNo);
			setSessionValue(Key.JOURNALPOINT_LIST_MAX_PAGE_NO , pageNo);
		}
		
		ArrayList<JournalPoint> list ;
		
		if(!StringTools.empty(startDate))
		{
			setSessionValue(Key.SEARCH_START_DATE , startDate);

			setSessionValue(Key.JOURNALPOINT_LIST_PAGE_NO , pageNo);
			maxPageNo = getSessionValue(Key.JOURNALPOINT_LIST_MAX_PAGE_NO);
			if(pageNo > maxPageNo)
				setSessionValue(Key.JOURNALPOINT_LIST_MAX_PAGE_NO , pageNo);
			
			
			list = mapper.selectByDate(startDate , pageNo * pageSize , pageSize);
		}
		else
		{
			list = mapper.selectAll();
		}
		
		setSessionValue(Key.JOURNALPOINT_LIST , list);
		
		return ActionURL.TO_JOURNAL_POINT_PATE;
	}
	
}
