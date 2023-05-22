package io.github.marcperez06.link_checker_web_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.marcperez06.java_utilities.api.request.Request;
import io.github.marcperez06.java_utilities.api.request.Response;
import io.github.marcperez06.java_utilities.api.request.ResponseTypeHolder;
import io.github.marcperez06.java_utilities.api.request.enums.HttpMethodEnum;
import io.github.marcperez06.java_utilities.api.rest.UnirestClient;
import io.github.marcperez06.java_utilities.api.rest.interfaces.IRestClient;
import io.github.marcperez06.link_checker.report.configuration.builder.LinkCheckerConfigurationBuilder;
import io.github.marcperez06.link_checker_web_service.domain.api_params.LinkCheckerParams;
import io.github.marcperez06.link_checker_web_service.domain.errors.LinkCheckerError;

//@SpringBootTest
class ValidationEndpointTest {

	@Test
	void validationEndpointTest() {
		String webServiceUrl = "http://localhost:8080/validation";
		LinkCheckerParams params = this.createParamsWithError();
		IRestClient api = new UnirestClient();
		
		Request request = new Request(HttpMethodEnum.POST, webServiceUrl);
		request.setResponseType(new ResponseTypeHolder<LinkCheckerError>() {});
		request.addHeader("Accept", "application/json");
		request.addHeader("Content-Type", "application/json");
		request.setBody(params);
		
		SpringApplication.run(LinkCheckerWebServiceApplication.class, "");
		
		Response<LinkCheckerError> response = api.send(request);
		
		assert response.isBadRequest();
		//assert response.getBody().get().size() > 0;
		
	}
	
	private LinkCheckerParams createParamsWithError() {
		String url = "";
		LinkCheckerConfigurationBuilder builder = new LinkCheckerConfigurationBuilder();
		builder.minDepth(-1);
		builder.minInteractions(-1);
		builder.minRequests(-1);
		builder.numThreads(-2);
		
		LinkCheckerParams params = new LinkCheckerParams();
		params.setUrl(url);
		params.setConfiguration(builder.build());
		
		return params;
	}

}
