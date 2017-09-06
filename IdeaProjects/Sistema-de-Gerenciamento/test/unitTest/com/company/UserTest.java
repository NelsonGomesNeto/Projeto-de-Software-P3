package unitTest.com.company;

import com.company.database.DataBase;
import com.company.user.SimpleUser;
import com.company.user.User;
import com.company.user.student.GraduationStudent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class UserTest {

	@Test
	public void shouldAddUserToDataBase() {

		User user = new SimpleUser("Nelson", "069.474.654-19", "ngn@ic.ufal.br");
		User user2 = new SimpleUser("Nelson", "222.222.222-22", "ngn2@ic.ufal.br");
		ArrayList<User> userArrayList = new ArrayList<>();
		userArrayList.add(user);
		userArrayList.add(user2);

		DataBase dataBase = new DataBase();
		dataBase.addUser(user);
		dataBase.addUser(user2);

		Assertions.assertEquals(dataBase.getUserByName("Nelson"), userArrayList);
	}

	@Test
	public void shouldBeOnDataBase() {

		User user = new SimpleUser("Nelson", "069.474.654-19", "ngn@ic.ufal.br");

		DataBase dataBase = new DataBase();
		dataBase.addUser(user);

		user = new GraduationStudent(user);

		ArrayList<User> userArrayList = new ArrayList<>();
		userArrayList.add(user);

		dataBase.setUser(user);

		Assertions.assertEquals(dataBase.getUserByName("Nelson"), userArrayList);
	}
}
