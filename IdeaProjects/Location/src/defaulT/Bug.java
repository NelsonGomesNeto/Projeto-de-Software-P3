package defaulT;

public class Bug {

	private String name;

	public Bug(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bug{" +
			"name='" + name + '\'' +
			'}';
	}
}
