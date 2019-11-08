public class Student {
	
	private int sid;
	private int student_id;
	private String firstName;
	private String secondName;

	public Student() {
		this.sid = sid;
		this.student_id = student_id;
		this.firstName = firstName;
		this.secondName = secondName;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", student_id=" + student_id + ", firstName=" + firstName + ", secondName="
				+ secondName + "]";
	}
	
	

	
}