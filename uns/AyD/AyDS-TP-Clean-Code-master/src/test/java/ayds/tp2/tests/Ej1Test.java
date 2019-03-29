package ayds.tp2.tests;

import java.net.URI;

import org.junit.Test;

import ayds.tp2.ej1.ServiceProvider;
import ayds.tp2.ej1.User;
import ayds.tp2.ej1.UserLogic;

import static org.junit.Assert.*;

public class Ej1Test {

	@Test
	public void test() {
		// Arrange.
		ServiceProvider serviceProvider =  new ServiceProvider() {
			@Override
			public String resolveCall(URI uri) {
				if(uri.toString().equals("http://www.my-service.com/create-user.json?name=Richard%20Dean&last-name=Anderson")) {
					return "{ \"result\" : \"ok\" }";
				} else {
					return "{ \"result\" : \"not ok\" }";
				}
			}
		};
		
		UserLogic userLogic = new UserLogic(serviceProvider);
		
		User userOk =  new User("Richard Dean", "Anderson");
		User userNotOk =  new User("The", "Dude");
		
		// Act.
		boolean isUserOkOk = userLogic.addUser(userOk);
		boolean isUserNotOkOk = userLogic.addUser(userNotOk);
		
		// Assert.
		assertEquals(isUserOkOk, true);
		assertEquals(isUserNotOkOk, false);
	}
}
