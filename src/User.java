
public class User {
	
	private int uid;
	private String userName;
	
	public User () {
		this.uid = uid;
		this.userName = userName;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", userName=" + userName + "]";
	}
	
	
	

}
