
package io.fabric8.quickstarts.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RestRoutePost extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		rest("/{{api.ver}}/say").id("test-route1").description("Test Route POST API").consumes("application/json")
				.produces("application/json").post("/bye").outType(String.class).type(String.class)
				.responseMessage().code(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase()).responseModel(String.class).endResponseMessage()
				 .route().routeId("rest-order-latest-route-post")
	                .to("direct:bye")
	                .end()
	                .endRest();

		from("direct:bye").log("Post call")
		.transform().constant("{\"test\":[]}");

	}

}
