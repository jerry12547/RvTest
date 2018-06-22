package rvtest.jerry.com.recyclerviewtest.beans;



/**
 * @author Jerry on 2018/6/22.
 * @function
 */
public class MainBean {
	private int  ivId;
	private String name;

	public MainBean(int ivId, String name) {
		this.ivId = ivId;
		this.name = name;
	}

	public int getIvId() {
		return ivId;
	}

	public void setIvId(int ivId) {
		this.ivId = ivId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
