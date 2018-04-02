package web.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONObject;

import game.tools.utils.DateTools;

public class TestMain 
{
    static int num= 1_000_000;  
    static List<Map<Integer, Boolean>> list=new ArrayList<>(num);  
	public static void main(String[] args) throws Exception
	{
//		t1();
//		t2();
//		t3();
		
		printMemory();
		testHashMap();
//		testConcurrentHashMap();
		printMemory();
		
	}
	
	private static void printMemory()
	{
//		System.out.println("maxMemory : " + Runtime.getRuntime().maxMemory());
		System.out.println("totalMemory : " + Runtime.getRuntime().totalMemory());
		System.out.println("freeMemory : " + (Runtime.getRuntime().freeMemory() / 1000/ 1000) + "MB");
		System.out.println("useMemory : " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000 / 1000) + "MB");
	}
	
	public static void testHashMap() throws Exception{  
//	    List<Map<Integer, Boolean>> list=new ArrayList<>(num);  
	    for (int i = 0; i < num; i++) {  
	        Map<Integer, Boolean> passedMap = new HashMap<>(100);  
	        list.add(passedMap);  
	    }  
	      
	}  
	  
	public static void testConcurrentHashMap() throws Exception{  
//	    List<Map<Integer, Boolean>> list=new ArrayList<>(num);  
	    for (int i = 0; i < num; i++) {  
	        Map<Integer, Boolean> passedMap = new ConcurrentHashMap<>(100);  
	        list.add(passedMap);  
	    }  
	      
	} 
	
	
	private static void t3() 
	{
		
	}

	private static void t2()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		String startDate = DateTools.getCurrentDateString(calendar.getTimeInMillis()).split(" ")[0].replaceAll("-", "");
		int year = calendar.getWeekYear();
		System.out.println(startDate);
		
	}
	
	private static void t1()
	{

		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("数学","数学老师");
		hashMap.put("化学","化学老师");
		hashMap.put("物理","物理老师");
		hashMap.put("生物","生物老师");
		hashMap.put("政治","政治老师");
        
		
		TreeMap<String, Integer> treeMap = new TreeMap<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return 0;
			}
		});
		treeMap.put("数学",500);
		treeMap.put("化学",48);
		treeMap.put("物理",10);
		treeMap.put("生物",10);
		treeMap.put("政治",155);
		
		LinkedHashMap<String, String> linkMap = new LinkedHashMap<>();
		linkMap.put("数学","数学老师");
		linkMap.put("化学","化学老师");
		linkMap.put("物理","物理老师");
		linkMap.put("生物","生物老师");
		linkMap.put("政治","政治老师");
	
//		for (String string : treeMap.values()) 
//			System.out.println("tree " + string);
//		
//		for (String string : linkMap.values()) 
//			System.out.println("link " + string);
		
		System.out.println("hashMap = " + JSONObject.toJSONString(hashMap));
		System.out.println("linkMap = " + JSONObject.toJSONString(linkMap));
		System.out.println("treeMap = " + JSONObject.toJSONString(treeMap));
		
		
//		ArrayList<Object> list = new ArrayList(treeMap.entrySet());
		
//		System.out.println("linkMap = " + JSONObject.toJSONString(linkMap , true));
	}
	
}
