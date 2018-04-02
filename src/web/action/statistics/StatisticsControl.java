package web.action.statistics;
import game.tools.event.Event;
import game.tools.event.Events;
import game.tools.utils.StringTools;
import web.tools.properties.FootballProperties;

public class StatisticsControl 
{
	private static long eventIndex = 0;
	
	public static String main()
	{
		start();
		
		return "StatisticsControl.Event.Start!!!!!!";
	}
	
	
	
	private static void start()
	{
		Events.stop();
		
		Events.addEvent(new Event(FootballProperties.getEVENT_NAME() , new Runnable() {
			@Override
			public void run() 
			{
				statistics();
			}
		}));
		
		System.out.println("StatisticsControl.Event.Start!!!!!!");
	}
	
	private static void statistics() 
	{
		JournalPointStatistics.getInstance().statistics();
		eventIndex++;
	}
	
	public static boolean appintDateStatistics(String date)
	{
		if(StringTools.empty(date))
			return false;
		
		JournalPointStatistics.getInstance().statistics(date);
		
		return true;
	}


	public static long getEventIndex() {		return eventIndex;	}
}
