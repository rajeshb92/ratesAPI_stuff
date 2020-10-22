package com.StepDefinitionClasses;

import org.junit.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LatestFXRWithBaseResponse {
	
			//creating response object to store response
			Response res2;
			String date,base,currentDate;
					
			@Given("^Rates API for Latest Foreign Exchange rates with base to check response$")
			public void buildAPI() throws Throwable {
				//setting up the baseURI
				RestAssured.baseURI="https://api.ratesapi.io";
			}

			@When("^The API with base is available to check response$")
			public void invokeAPI() throws Throwable {
			
				res2= RestAssured.get("/api/latest?base=USD");
			}

			@Then("^verify that the response has current date$")
			public void validate() throws Throwable {
			    date=res2.jsonPath().get("date");
				System.out.println("Date in the response is :"+date);
				//verifying the date is current
				currentDate=commonResources.Respos.getTodayDateString();
				Assert.assertEquals(currentDate,date);
			
			}
			
			@And("^response has base currency same as the base parameter mentioned in api$")
			public void validateHTTPVersion() throws Throwable {
			    
				base=res2.jsonPath().get("base");
				//printing the base currency present in api response
				System.out.println("Base currency present in response is :"+base);
				//verifying that it is USD
				Assert.assertEquals("USD", base);
			}

}
