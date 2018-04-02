package web.data.entity;

import java.io.Serializable;

import web.control.entity.JobType;
import web.control.entity.StateType;

public class SysUser  implements Serializable{
    private int id;

    private String userName;

    private String userPwd;

    private int job;
    
    private int state;

    private String tag;
    
    private String lastLoginTime;
    
    private String createTime;

    private String fullName;

    private String mail;

    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

    public int getId() {
        return id;
    }
    
    public String getIdStr()
    {
    	return id +"";
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public int getJob() {
        return job;
    }
    
    public String getJobStr()
    {
    	if(job == JobType.JOB_ADMIN)
    		return JobType.JOB_ADMIN_STR;
    	else if(job == JobType.JOB_NORMAL)
    		return JobType.JOB_NORMAL_STR;
    	else
    		return JobType.JOB_ERROR;
    }
    
    public String getStateStr()
    {
    	if(state == StateType.ENABLE)
    		return StateType.ENABLE_STR;
    	else
    		return StateType.DISABLE_STR;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }
}