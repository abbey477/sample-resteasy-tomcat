package com.resteasy.series.xml.json.service;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customerservice")
public interface ICustomerService {

	// Basic CRUD operations for Customer Service

	// http://localhost:8080/RestEasy-XML-JSON-IO/resteasy/customerservice/addcustomer  - Tomcat 7.0.x
	// http://localhost:9090/RestEasy-XML-JSON-IO/resteasy/customerservice/addcustomer  - JBoss AS7
	@POST
	@Path("addcustomer")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_FORM_URLENCODED})
	public String createOrSaveNewCustomerInfo(CustomerType customerType);

	// http://localhost:8080/RestEasy-XML-JSON-IO/resteasy/customerservice/getcustomer/10001  - Tomcat 7.0.x
	// http://localhost:9090/RestEasy-XML-JSON-IO/resteasy/customerservice/getcustomer/10001  - JBoss AS7
	@GET
	@Path("getcustomer/{id}")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public CustomerType getCustomerInfo(@PathParam("id") int customerId);

	// http://localhost:8080/RestEasy-XML-JSON-IO/resteasy/customerservice/updatecustomer  - Tomcat 7.0.x
	// http://localhost:9090/RestEasy-XML-JSON-IO/resteasy/customerservice/updatecustomer  - JBoss AS7
	@PUT
	@Path("updatecustomer")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_FORM_URLENCODED})
	public String updateCustomerInfo(CustomerType Customer);

	// http://localhost:8080/RestEasy-XML-JSON-IO/resteasy/customerservice/deletecustomer  - Tomcat 7.0.x
	// http://localhost:9090/RestEasy-XML-JSON-IO/resteasy/customerservice/deletecustomer  - JBoss AS7
	@DELETE
	@Path("deletecustomer")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_FORM_URLENCODED})
	public String deleteCustomerInfo(CustomerType Customer);

	// http://localhost:8080/RestEasy-XML-JSON-IO/resteasy/customerservice/getallcustomer  - Tomcat 7.0.x
	// http://localhost:9090/RestEasy-XML-JSON-IO/resteasy/customerservice/getallcustomer  - JBoss AS7
	@GET
	@Path("getallcustomer")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public CustomerListType getAllCustomerInfo();
}