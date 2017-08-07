package defaulT;

/**
 * Created by alunoic on 24/07/17.
 */
public class Main {

	public Main() {
	}

	public static void main(String[] args) {

		Box <Bug> box = new Box<>();
		box.setE(new Bug("Teste"));

		System.out.println(box);

	}
}

