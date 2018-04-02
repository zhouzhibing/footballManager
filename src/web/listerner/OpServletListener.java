package web.listerner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import web.action.statistics.StatisticsControl;
import web.data.MysqlControl;




public class OpServletListener implements ServletContextListener
{
	public void contextDestroyed(ServletContextEvent arg0)
	{
	}


	public void contextInitialized(ServletContextEvent arg0)
	{
		try
		{
			StatisticsControl.main();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}

}
