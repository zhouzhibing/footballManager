#include "web_action_TestNative.h"
#include <string>
using  std::string;
JNIEXPORT jstring JNICALL Java_web_action_TestNative_sayHello(JNIEnv * env, jclass c)
{
	printf("Java_web_action_TestNative_sayHello");
	char p[100] = "Java_web_action_TestNative_sayHello C++";
	//return "Java_web_action_TestNative_sayHello C++".data();
	return CStr2Jstring(env , p);
}


//将char类型转换成jstring类型  
jstring CStr2Jstring( JNIEnv* env,const char* str )  
{  
    jsize len = strlen(str);  
    // 定义java String类 strClass  
    jclass strClass = (*env)->FindClass(env, "java/lang/String");  
    //设置String, 保存语言类型,用于byte数组转换至String时的参数  
    jstring encoding = (*env)->NewStringUTF(env, "GB2312");  
    // 获取java String类方法String(byte[],String)的构造器,用于将本地byte[]数组转换为一个新String  
    jmethodID ctorID = (*env)->GetMethodID(env, strClass, "<init>", "([BLjava/lang/String;)V");  
    // 建立byte数组  
    jbyteArray bytes = (*env)->NewByteArray(env, len);  
    // 将char* 转换为byte数组  
    (*env)->SetByteArrayRegion(env, bytes, 0, len, (jbyte*)str);  
    //将byte数组转换为java String,并输出  
    return (jstring)(*env)->NewObject(env, strClass, ctorID, bytes, encoding);  
}  