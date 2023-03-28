package binaryTreePD;

public class Student {
	private int key;
	private String name;
	
	public Student(int inKey, String inName) {
		setKey(inKey);
		setName(inName);
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return key + ":" + name;
	}
}