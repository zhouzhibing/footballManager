package web.action;

public class TestNative 
{
	public static native String sayHello();
	
	public static void main(String[] args) 
	{
		System.load("");
		System.out.println(sayHello());
	}
}
