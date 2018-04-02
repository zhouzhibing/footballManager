package web.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import game.tools.net.netty4.Netty4Decode;
import game.tools.net.netty4.Netty4Encode;
import game.tools.net.netty4.Netty4Handler;
import game.tools.net.netty4.server.Netty4Server;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class FileTools
{
	private FileInputStream fileIn;
    private MappedByteBuffer mappedBuf;
//    private long fileLength;
    private int arraySize;
    private byte[] array;
    
	public void mapperFileRead(String fileName, int arraySize) throws IOException
	{
		this.fileIn = new FileInputStream(fileName);
        FileChannel fileChannel = fileIn.getChannel();
        this.fileLength = fileChannel.size();
        this.mappedBuf = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);
        
        
//        File file = new File(fileName);
//        Read
//        FileInputStream in = new FileInputStream(file);   
//        ByteArrayOutputStream out = new ByteArrayOutputStream((int)file.length());   
	}
	
	private static final HashMap<String , Runnable> map = new HashMap();
	
	private void t1()
	{
		System.out.println("t1");
	}
	
	private void t2()
	{
		System.out.println("t2");
	}
	
	private void t3()
	{
		System.out.println("t3");
	}
	
	public static void main(String [] args)
	{
//		bufferedReader("charge_20171203_0631.log");
//		bufferedWriter("charge_20171203_0632.log");
//		scannerFile("charge_20171203_0631.log");
//		readBigFile("charge_20171203_0632.log");\
		
		
//		new Thread(Run)
		FileTools ft = new FileTools();
		
		map.put("t1",ft::t1);
		map.put("t2",ft::t2);
		map.put("t3",ft::t3);
		
		
		map.get("t2").run();
//		getMemInfo();
		
		
		Netty4Server ns = new Netty4Server(new Netty4Decode() 
		{
			@Override
			protected void decode(ChannelHandlerContext arg0, ByteBuf arg1, List<Object> arg2) throws Exception {
				// TODO Auto-generated method stub
				super.decode(arg0, arg1, arg2);
			}
			
			@Override
			public Netty4Decode clone() {
				// TODO Auto-generated method stub
				return super.clone();
			}
		}, 
		new Netty4Encode() 
		{
			@Override
			protected void encode(ChannelHandlerContext arg0, Object arg1, ByteBuf arg2) throws Exception {
				// TODO Auto-generated method stub
				super.encode(arg0, arg1, arg2);
			}
		}, 
		new Netty4Handler() 
		{
			@Override
			public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception 
			{
				// TODO Auto-generated method stub
				super.channelRead(ctx, msg);
			}
		});
	}
	
	private static void bufferedWriter(String path) 
	{
		try 
		{
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path) , true), "utf-8"));
			
			for(int i = 0 ; i < 6000000; i ++)
			{
				bw.write("{\"aid\":\"44\",\"chl\":\"jingFenChl\",\"chr\":\"jingFenChr\",\"did\":\"null\",\"ga\":0,\"gc\":\"com.xygame.allstarsoccer.funding\",\"gn\":\"null\",\"ip\":\"null\",\"key\":\"628s36yk8sg8ak0\",\"mny\":499,\"oid\":\"\",\"rid\":\"39\",\"svr\":\"01-30\",\"ts\":\"1512282662\"}\r");
			}
		    bw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			
		}
		
	}


	public static void bufferedReader(String path)
	{
		try 
		{
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)),"utf-8"));
			int count = 0;
		    while (br.ready()) 
		    {
		        String line = br.readLine();
		        System.out.println( " length = " + line.getBytes().length);
		        count ++;
//		        System.out.println(line);
		    }
		    System.out.println("bufferedReader count = " + count );
		    br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			
		}

	}
	
	
	public static void scannerFile(String path)
	{
//		System.out.println(new File(path).getAbsolutePath());
		FileInputStream inputStream = null;
		Scanner sc = null;
		
		try 
		{
		    inputStream = new FileInputStream(path);
		    sc = new Scanner(inputStream, "UTF-8");
		    
		    int count = 0;
		    String line = null;
		    while (sc.hasNextLine()) 
		    {
		        line = sc.nextLine();
		        count ++;
		        
		        if(count % 1000 == 0)
		        	System.out.println("count = " + count);
		    }
		    
		    System.out.println("scannerFile count = " + count );
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) 
		    {
		        throw sc.ioException();
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (inputStream != null) 
			    {
			        inputStream.close();
			    }
			    if (sc != null) {
			        sc.close();
			    }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		   
		}
	}
	static long count = 0;
	static long fileLength = 0;
	static String secContent = "";
	static boolean start = true;
	public static void readBigFile( String fileName) 
	{
		
		Thread t = new Thread(()->{
			long index = 0;
			while(start)
			{
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				index ++;
				
				System.out.println("Count = " + count + " / " + fileLength + " index = " + index + " secContent = " + secContent.hashCode());
				
//				secContent = null;
			}
		});
		
		t.start();
		
		try 
		{
//		  String fileName = "/Users/mc2/Desktop/youku.txt";
			RandomAccessFile randomFile = null;
			randomFile = new RandomAccessFile(fileName, "r");
			
	        fileLength = randomFile.length();
	        System.out.println("文件大小:" + fileLength);
	        
//	        int start = 0;
	        randomFile.seek(0);
	        
	        int byteread = 0;
	        
	        byte [] rn = "{\"aid\":\"44\",\"chl\":\"jingFenChl\",\"chr\":\"jingFenChr\",\"did\":\"null\",\"ga\":0,\"gc\":\"com.xygame.allstarsoccer.funding\",\"gn\":\"null\",\"ip\":\"null\",\"key\":\"628s36yk8sg8ak0\",\"mny\":499,\"oid\":\"\",\"rid\":\"39\",\"svr\":\"01-30\",\"ts\":\"1512282662\"}".getBytes();
//	        System.out.println( " rn  = " + Arrays.toString(rn));
	        byte[] bytes = new byte[212];
//	        byte[] bytes = new byte[1024];
	        
//	        int count = 0;
//	        System.out.println("String = s" + new String(new byte [] {10 }) + "ss");
	        // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
	        // 将一次读取的字节数赋给byteread
	        String line = null;
	        while((line = randomFile.readLine()) != null) {
//	        	System.out.println("line = " + line);
	        	secContent = line + "" + Math.random();
	        	if(line == null || line == "")
	        		break;
	        	count ++;
	        }
//	        while ((byteread = randomFile.read(bytes)) != -1) 
//	        {
////	        	randomFile.seek(start);
//	        	
//	        	//if(secContent == "")
//	        	secContent = new String(bytes);
//	        	System.out.println("secContent = " + secContent);
////	        	System.out.println(Arrays.toString(bytes));
////	            System.out.write(bytes);
//	        	
//	        	count ++;
//	        	
////	        	if( count % 1000000 == 0)
////	        		System.out.println(fileLength - count);
//	        }
	        
	        System.out.println("Read OK ！");
//	        System.out.println(bytes.length);
//	        System.out.println(new String(bytes,"UTF-8"));
	        if (randomFile != null) {
	         randomFile.close();
	        }
	        
	        start = false;
			        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	 
	 }
	
	
	public static void getDiskInfo()
    {
        File[] disks = File.listRoots();
        for(File file : disks)
        {
            System.out.print(file.getPath() + "    ");
            System.out.print("空闲未使用 = " + file.getFreeSpace() / 1024 / 1024 + "M" + "    ");// 空闲空间
            System.out.print("已经使用 = " + file.getUsableSpace() / 1024 / 1024 + "M" + "    ");// 可用空间
            System.out.print("总容量 = " + file.getTotalSpace() / 1024 / 1024 + "M" + "    ");// 总空间
            System.out.println();
        }
    }
    
    public static void getMemInfo()
    {
//        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//        System.out.println("Total RAM：" + mem.getTotalPhysicalMemorySize() / 1024 / 1024 + "MB");
//        System.out.println("Available　RAM：" + mem.getFreePhysicalMemorySize() / 1024 / 1024 + "MB");
    	
    	Runtime runtime;
    	try
    	{
			runtime=Runtime.getRuntime();
			System.out.println("处理器的数目"+runtime.availableProcessors());
			System.out.println("空闲内存量："+runtime.freeMemory()/ 1024L/1024L + "M av");
			System.out.println("使用的最大内存量："+runtime.maxMemory()/ 1024L/1024L + "M av");
			System.out.println("内存总量："+runtime.totalMemory()/ 1024L/1024L + "M av");
			System.out.println("使用总量："+(runtime.totalMemory() - runtime.freeMemory())/ 1024L/1024L + "M av");
		}
    	catch(Exception e)
    	{
			e.printStackTrace();
		}
    }
}
