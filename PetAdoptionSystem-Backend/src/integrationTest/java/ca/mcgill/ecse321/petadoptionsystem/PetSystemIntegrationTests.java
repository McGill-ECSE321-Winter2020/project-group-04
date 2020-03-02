package ca.mcgill.ecse321.petadoptionsystem;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetSystemIntegrationTests {
    //private final String APP_URL = "http://localhost:8080";
    private final String APP_URL = "http://petadoptionsystem-backend-04-n.herokuapp.com";
    private JSONObject response;
    private final Integer systemID = 4;
    private final String restUserNameAdmin = "TestUserAdmin";
    private final String restUserNameRegular = "TestUserRegular";
    private final String restEmailAdmin = "ecse321testadmin@protonmail.com";
    private final String restEmailRegularUser= "ecse321testregularuser@protonmail.com";
    private final String restPasswordAdmin = "userpasswordAdmin";
    private final String restPasswordRegularUser = "userpasswordRegularUser";

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

    }
    @Test
    public void testCreateAdminAccount() {
        String params = "username=" + restUserNameAdmin + "&passwordHash=" + restPasswordAdmin + "&email=" + restEmailAdmin;

        try {
            response = send("POST", APP_URL, "/account/createadmin", params);
            System.out.println("Received: " + response.toString());
            assertEquals(restUserNameAdmin, response.getString("username"));
        } catch (JSONException e) {
            fail();
        }
    }
    @Test
    public void testGetAccountByUsername() {
        String params = "username=" + restUserNameAdmin;

        try {
            response = send("GET", APP_URL, "/account/username", params);
            System.out.println("Received: " + response.toString());
            assertEquals(restUserNameAdmin, response.getString("username"));
        } catch (JSONException e) {
            fail();
        }
    }
    @Test
    public void testCreatePetProfile() {
        // TODO: Garrett
    }
    @Test
    public void testGetAllPetProfilesOfUser() {
        // TODO: Garrett
    }

    @Test
    public void testCreateRegularUserAccount() {
        // TODO: Garrett
    }
    @Test
    public void testGetRegularUserbyUser() {

    }
    @Test
    public void testGetAllPetProfiles() {

    }
    @Test
    public void testCreateApplication() {

    }
    @Test
    public void testGetApplication() {

    }
    @Test
    public void testUpdateApplication() {

    }

    @After
    public void zClearDb() {
        send("POST", APP_URL, "/flushdb", null);
        assertTrue(true);
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