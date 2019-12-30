package test.resteasy.series.xml.json.service;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class TestCustomerService {

	public static void main(String[] args) throws Exception {

		// setting & invoking first service getCustomer/45001
		System.out.println("Invoking and executing getCustomer service for customer id 45001");
		String httpGetURL = "http://localhost:8080/RestEasy-XML-JSON-IO/resteasy/customerservice/getcustomer/45001";
		String responseStringGet = testCustomerServiceForGetRequest(httpGetURL);
		System.out.println("GET >> Response String : " + responseStringGet);

		// setting & invoking second service addCustomer with XML/JSON request
		System.out.println("\n\nInvoking and executing addCustomer service with request");
		String httpPostURL = "http://localhost:8080/RestEasy-XML-JSON-IO/resteasy/customerservice/addcustomer";
		String requestStringInJson = "{"
				+ 					" \"customerId\": 45011, " 
				+ 					" \"name\": \"Shin Chan\","
				+ 					"\"age\": 25 "
				+				"}";
		String requestStringInXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ 					"<CustomerType xmlns=\"http://benchresources.in/cdm/Customer\">"
				+ 						"<customerId>45021</customerId>"
				+ 						"<name>Nobita</name>"
				+ 						"<age>26</age>"
				+ 					"</CustomerType>";
		String responseStringPost = testCustomerServiceForPostRequest(httpPostURL, requestStringInJson);
		System.out.println("POST >> Response String : " + responseStringPost);
	}

	/**
	 * using ClientRequest and ClientResponse classes from org.jboss.resteasy.client
	 * @param httpURL
	 * @return responseString
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static String testCustomerServiceForGetRequest(String httpURL) throws Exception {

		// local variables
		ClientRequest clientRequest = null;
		ClientResponse<String> clientResponse = null;
		int responseCode;
		String responseString = null;

		try{
			clientRequest = new ClientRequest(httpURL);
			clientRequest.setHttpMethod(HttpMethod.GET);
			clientRequest.header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED);
			clientRequest.accept(MediaType.APPLICATION_JSON);
			clientResponse = clientRequest.get(String.class);

			responseCode = clientResponse.getResponseStatus().getStatusCode();
			System.out.println("Response code: " + responseCode);

			if(clientResponse.getResponseStatus().getStatusCode() != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + responseCode);
			}

			System.out.println("ResponseMessageFromServer: " + clientResponse.getResponseStatus().getReasonPhrase());
			responseString = clientResponse.getEntity();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally{
			// release resources, if any
			clientResponse.close();
			clientRequest.clear();
		}
		return responseString;
	}

	/**
	 * using ResteasyClient, ResteasyWebTarget and Response classes from org.jboss.resteasy.client
	 * @param httpURL
	 * @param requestString
	 * @return
	 */
	public static String testCustomerServiceForPostRequest(String httpURL, String requestString)  throws Exception {

		// local variables
		ResteasyClient resteasyClient = null;
		ResteasyWebTarget resteasyWebTarget = null;
		Response response = null;
		int responseCode;
		String responseString = null;

		try{
			resteasyClient = new ResteasyClientBuilder().build();
			resteasyWebTarget = resteasyClient.target(httpURL);
			//			resteasyWebTarget.property("Content-Type", MediaType.APPLICATION_FORM_URLENCODED);
			//			resteasyWebTarget.property("accept", MediaType.APPLICATION_JSON);
			response = resteasyWebTarget.request(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.entity(requestString, MediaType.APPLICATION_JSON));

			responseCode = response.getStatus();
			System.out.println("Response code: " + responseCode);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + responseCode);
			}

			System.out.println("ResponseMessageFromServer: " + response.getStatusInfo().getReasonPhrase());
			responseString = response.readEntity(String.class);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			// release resources, if any
			response.close();
		}
		return responseString;
	}
}