package rvtest.jerry.com.recyclerviewtest.beans;

/**
 * @author Jerry on 2018/6/27.
 * @function 城市数据
 */
public class CityBean {
	//TODO 因为真实城市数据有点复杂，需要考虑排序等因素，先拿假数据填充

	private String groupName;
	private String memberName;
	private boolean isShowSubItem;

	public CityBean(String groupName, String memberName, boolean isShowSubItem) {
		this.groupName = groupName;
		this.memberName = memberName;
		this.isShowSubItem = isShowSubItem;
	}

	public boolean isShowSubItem() {
		return isShowSubItem;
	}

	public void setShowSubItem(boolean showSubItem) {
		isShowSubItem = showSubItem;
	}


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
}
