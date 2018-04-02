package web.data.entity;

import java.io.Serializable;

public class SysDbList implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

    private String dbUrl;

    private String dbUserName;
    private String dbUserPwd;
    private String dbClassName;
    private String dbDesc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getDbUserName() {
		return dbUserName;
	}
	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}
	public String getDbUserPwd() {
		return dbUserPwd;
	}
	public void setDbUserPwd(String dbUserPwd) {
		this.dbUserPwd = dbUserPwd;
	}
	public String getDbDesc() {
		return dbDesc;
	}
	public void setDbDesc(String dbDesc) {
		this.dbDesc = dbDesc;
	}
	public String getDbClassName() {
		return dbClassName;
	}
	public void setDbClassName(String dbClassName) {
		this.dbClassName = dbClassName;
	}

	
}