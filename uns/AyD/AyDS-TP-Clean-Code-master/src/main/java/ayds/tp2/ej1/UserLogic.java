package ayds.tp2.ej1;

import java.net.URI;
import java.net.URISyntaxException;

import com.google.gson.Gson;

public class UserLogic {

	private ServiceProvider serviceProvider;

	public UserLogic(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	// GET Request. Format: <base-url>/<service-name>?<params>
	// Example:
	// http://www.my-service.com/create-user.json?name=Richard%20Dean&last-name=Anderson
	// Result example: { "result" : "ok" }
	public boolean addUser(User user) {

		boolean ok = false;

		String baseUrl = "http://www.my-service.com";
		String serviceName = "create-user.json";

		String addUserUrl = baseUrl + "/" + serviceName + "?" + "name=" + user.getName() + "&last-name="
				+ user.getLastName();
		
		// replaces spaces with encoding %20 
		addUserUrl = addUserUrl.replace(" ", "%20");

		String json = null;

		try {
			json = serviceProvider.resolveCall(new URI(addUserUrl));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CreateUserResult addUserResult = null;

		if (json != null) {
			Gson gson = new Gson();
			addUserResult = gson.fromJson(json, CreateUserResult.class);
			ok = addUserResult.getResult().equals("ok");
		}

		return ok;
	}

}
