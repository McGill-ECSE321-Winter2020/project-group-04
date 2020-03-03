package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetSystemIntegrationTests {
    private final String APP_URL = "http://petadoptionsystem-backend-04-n.herokuapp.com";
    private JSONObject response;
    private final Integer systemID = 4;
    private final String restUserNameAdmin = "TestUserAdmin";
    private final String restUserNameRegular = "TestUserRegular";
    private final String restEmailAdmin = "ecse321testadmin@protonmail.com";
    private final String restEmailRegularUser= "ecse321testregularuser@protonmail.com";
    private final String restPasswordAdmin = "userpasswordAdmin";
    private final String restPasswordRegularUser = "userpasswordRegularUser";
    private final String petName = "Gucci";

    /*
     * TESTING
     */

    @Test
    public void testCreatePetAdoptionSystem() {
        try {
            response = send("POST", APP_URL, "/createSystem", null);
            System.out.println("Received: " + response.toString());
            assertEquals(systemID, response.getString("systemID"));
        } catch (JSONException e) {
            fail();
        }
    }

    @Test
    public void testGetPetAdoptionSystem() {
//        try {
//            response = send("GET", APP_URL, "/getSystem", null);
//            System.out.println("Received: " + response.toString());
//            assertEquals(systemID, response.getString("name"));
//        } catch (JSONException e) {
//            fail();
//        }

    }
    @Test
    public void testCreateAdminAccount() {

    }
    @Test
    public void testGetAccountByUsername() {

    }
    @Test
    public void testCreatePetProfile() {

    }
    @Test
    public void testGetAllPetProfilesOfUser() {

    }

    @Test
    public void testCreateRegularUserAccount() {

    }
    /**
     * @Author eknuviad
     */
    @Test
    public void testGetRegularUserbyUser() {
        try {
            response = send("GET", APP_URL, "/regularuser/" + restUserNameRegular, null);
            System.out.println("Received: " + response.toString());
            assertEquals(restUserNameRegular, response.getString("username"));
        } catch (JSONException e) {
            fail();
        }
    }
    @Test
    public void testGetAllPetProfiles() {
        List<PetProfile> profiles = new ArrayList<>();
        try {
            response = send("GET", APP_URL, "/petprofiles", null);
            System.out.println("Received: " + response.toString());
            assertEquals(petName, response.getString("petName"));
        } catch (JSONException e) {
            fail();
        }
    }
    @Test
    public void testCreateApplication() {
        try {
            response = send("POST", APP_URL, "/apply",
                    "username=" + restUserNameAdmin + "&passwordHash=" + restPasswordAdmin + "&email=" + restEmailAdmin);
            System.out.println("Received: " + response.toString());
            assertEquals(restUserNameAdmin, response.getString("username"));
            assertEquals(restPasswordAdmin, response.getString("passwordHash"));
            assertEquals(restEmailAdmin, response.getString("email"));
        } catch (JSONException e) {
            fail();
        }
        //

    }
    @Test
    public void testGetApplication() {
     try {
			String applicationId = send("POST", APP_URL, "/applications/",
					"username=" + restUserNameRegular + "&email=" + restEmailRegularUser)
							.getString("applicationId");
			JSONArray applications = sendArray("GET", APP_URL, "/applications/" + applicationId, "");
			for (int i = 0; i < applications.length(); i++) {
				JSONObject o = applications.getJSONObject(i);
				assertEquals(restUserNameRegular, o.getString("applicant"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}
    }
    @Test
    public void testUpdateApplication() {
        try {
			String applicationId = send("POST", APP_URL, "/apply",
					"username=" + restUserNameRegular + "&email=" + restEmailRegularUser)
							.getString("applicationId");
			JSONArray applications = sendArray("GET", APP_URL, "/updateApplication/" + applicationId, "");
			for (int i = 0; i < applications.length(); i++) {
				JSONObject o = applications.getJSONObject(i);
				assertEquals(true, o.getString("confirm"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}
    }


    // METHODS FOR REST

    public JSONObject send(String type, String appURL, String path, String parameters) {
        try {
            URL URL = new URL(appURL + path + ((parameters == null) ? "" : ("?" + parameters)));
            System.out.println("Sending: " + URL.toString());
            HttpURLConnection c = (HttpURLConnection) URL.openConnection();
            c.setRequestMethod(type);
            c.setRequestProperty("Accept", "application/json");
            System.out.println(c.getContentType());
            if (c.getResponseCode() != 200) {
                throw new RuntimeException(URL.toString() + " Returned error: " + c.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((c.getInputStream())));
            String response = br.readLine();
            if (response.equals("true") || response.equals("false")) {
                JSONObject json = new JSONObject();
                json.put("boolean", response);
                c.disconnect();
                return json;
            } else {
                JSONObject json = new JSONObject(response);
                c.disconnect();
                return json;
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray sendArray(String type, String appURL, String path, String parameters) {
        try {
            URL url = new URL(appURL + path + ((parameters == null) ? "" : ("?" + parameters)));
            System.out.println("Sending: " + url.toString());
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod(type);
            c.setRequestProperty("Accept", "application/json");
            if (c.getResponseCode() != 200) {
                throw new RuntimeException(url.toString() + " Returned error:: " + c.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((c.getInputStream())));
            String response = br.readLine();
            if (response != null) {
                JSONArray r = new JSONArray(response);
                c.disconnect();
                return r;
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}