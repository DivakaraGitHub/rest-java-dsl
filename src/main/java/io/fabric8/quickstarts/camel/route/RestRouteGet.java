package io.fabric8.quickstarts.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RestRouteGet extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		rest("/{{api.ver}}/say").id("get-say").description("GET URL JAVA DSL").consumes("application/json")
				.produces("application/json").get("/hello").outType(String.class)
				.responseMessage().code(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase()).responseModel(String.class).endResponseMessage()
				 .route().routeId("rest-order-latest-route-get")
	                .to("direct:hello")
	                .end()
	                .endRest();

		from("direct:hello")
		.log("Get call")
		.transform().constant("{\"test\":[]}");

	}
}
