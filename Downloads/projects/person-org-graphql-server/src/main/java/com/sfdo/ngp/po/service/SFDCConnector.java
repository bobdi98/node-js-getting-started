package com.sfdo.ngp.po.service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service()
public class SFDCConnector {

	private static final String TOKEN_URL =  "https://login.salesforce.com/services/oauth2/token";

/*	public static void main(String[] a) throws Exception {
		SFDCConnector coo = new SFDCConnector();
		//coo.createContact("Potluri", "pot.yog@gmail.com", "0011I000005x8OJQAY");
		coo.createUser("Preeti", "satya.polavarapu@gmail.com", true, false, null);
	}
*/	
    public String createAccount(String name, Long accountNumber) throws Exception {

    			String tokens[] = login();
            //create the JSON object containing the new lead details.
            JSONObject account = new JSONObject();
            account.put("Name", name);
            account.put("AccountNumber", String.valueOf(accountNumber));
            
            return createRecord(tokens[0], tokens[1], "Account", account);
    }

    public String createUser(
				    			String lastName, 
				    			String email, 
				    			String username,
				    			boolean isAdmin, 
				    			String accountNumber) throws Exception {

		String[] tokens = login();
		System.out.println("Login result: " + tokens);
		
		//create the JSON object containing the new lead details.
        JSONObject lead = new JSONObject();
        lead.put("Username",username);
        lead.put("LastName",lastName);
        lead.put("FirstName","SATTY");
        lead.put("Email" ,email); 
        lead.put("Alias","pree123");
        
        lead.put("CommunityNickname",lastName + email );
        lead.put("isActive",true);
        lead.put("TimeZoneSidKey","America/Los_Angeles" );
        lead.put("LocaleSidKey", "en_US");
        lead.put("EmailEncodingKey","ISO-8859-1");
        lead.put("LanguageLocaleKey","en_US");
        lead.put("UserPermissionsMobileUser" , false );
        lead.put("UserPreferencesDisableAutoSubForFeeds", false);
        if(!isAdmin) {
            lead.put("ContactId", createContact(lastName, accountNumber) );
            lead.put("ProfileId","00e1I000000l8kyQAA"); // Customers profile (External Identity profile)
        } else {
            lead.put("ProfileId","00e1I0000013D33QAE"); // todo: change this logic to use names instead of Ids
        }
	    
	    String userId = createRecord(tokens[0],tokens[1], "User", lead);
	    
	    return userId;
	 }

    public String createContact(String lastName, String accountNumber) throws Exception {

    			String[] tokens = login();
    			System.out.println("Login result: " + tokens);
    			
            JSONObject contact = new JSONObject();
            contact.put("LastName", lastName);
            contact.put("AccountId", accountNumber);
            
            return createRecord(tokens[0],tokens[1], "Contact", contact);
    }

	public String[] login() throws Exception {
			
			//todo: externalize these variables to heroku config vars
			String[] result = new String[2];
			String username = "satya.polavarapu@nextgen.com";
	        String password = "nKFL31%4t67e0XibmAll13vffNMx0K9rRsaYq";
	        String consumerKey = "3MVG9g9rbsTkKnAXtWHieRN_beQoj8iNdrWZYAumM.6PaoL1gyFiodNv.oKo8g0XVSVda73b1KT96MZsVKOF9";
	        String consumerSecret = "3604657987028619749";

	            final CloseableHttpClient httpclient = HttpClients.createDefault();

	            final List<NameValuePair> loginParams = new ArrayList<NameValuePair>();
	            loginParams.add(new BasicNameValuePair("client_id", consumerKey));
	            loginParams.add(new BasicNameValuePair("client_secret", consumerSecret));
	            loginParams.add(new BasicNameValuePair("grant_type", "password"));
	            loginParams.add(new BasicNameValuePair("username", username));
	            loginParams.add(new BasicNameValuePair("password", password));

	            HttpPost post = new HttpPost(TOKEN_URL);
	            post.setEntity(new UrlEncodedFormEntity(loginParams));

	            HttpResponse loginResponse = httpclient.execute(post);

	            // parse
	            final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

	            final JsonNode loginResult = mapper.readValue(loginResponse.getEntity().getContent(), JsonNode.class);
	            result[0] = loginResult.get("access_token").asText();
	            result[1] = loginResult.get("instance_url").asText();
	            return result;

	}
    
    public static String createRecord(String accessToken, String baseUri, String object, JSONObject payload) {
        System.out.println("\n_______________ " + object + " INSERT _______________");
 
        String objId = null;
        String uri = baseUri + "/services/data/v39.0/sobjects/" + object +"/";
        System.out.println("Invoking : " + uri);
        try {
 
            System.out.println("JSON for " + object + " to be inserted:\n" + payload.toString(1));
 
            //Construct the objects needed for the request
            HttpClient httpClient = HttpClientBuilder.create().build();
 
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Authorization", "Bearer " + accessToken);
            //httpPost.addHeader(prettyPrintHeader);
            // The message we are going to post
            StringEntity body = new StringEntity(payload.toString(1));
            body.setContentType("application/json");
            httpPost.setEntity(body);
 
            //Make the request
            HttpResponse response = httpClient.execute(httpPost);
 
            //Process the results
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 201) {
                String response_string = EntityUtils.toString(response.getEntity());
                System.out.println(response_string);
                JSONObject json = new JSONObject(response_string);
                // Store the retrieved lead id to use when we update the lead.
                objId = json.getString("id");
                System.out.println("New " + object + " id from response: " + objId);
            } else {
                System.out.println("Insertion unsuccessful. Status code returned is " + statusCode);
            }
        } catch (Exception npe) {
            npe.printStackTrace();
            throw new RuntimeException(npe);
        }
        
        return objId;
    }
}