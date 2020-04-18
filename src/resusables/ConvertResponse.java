package resusables;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ConvertResponse {

	public static XmlPath rawToXMLPath(Response response) {

		String str = response.asString();
		XmlPath path = new XmlPath(str);
		return path;
	}

	public static JsonPath rawToJSONPath(Response response) {

		String str = response.asString();
		JsonPath path = new JsonPath(str);
		return path;
	}

}
