package web.data.entity;

import java.io.Serializable;


public class SysServerList implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

    private String name="";

    private String ip;

    private String tag;

    private int port;

    private int status;
    
    private int dbId;
    
    private String dbDesc;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDbId() {
		return dbId;
	}

	public void setDbId(int dbId) {
		this.dbId = dbId;
	}

	public String getDbDesc() {
		return dbDesc;
	}

	public void setDbDesc(String dbDesc) {
		this.dbDesc = dbDesc;
	}

  
}