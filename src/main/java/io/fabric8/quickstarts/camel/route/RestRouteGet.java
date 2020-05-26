package io.fabric8.quickstarts.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RestRouteGet extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		rest("/{{api.ver}}/say").id("get-say").description("GET URL JAVA DSL").consumes("application/json")
		.produces("application/json").get("/hello").outType(String.class)
		//.type(String.class)
		.to("direct:hello");

		from("direct:hello")
		.log("I am here")
		.setBody(simple("{\"ALL\" : []}"))
		.setHeader(Exchange.HTTP_RESPONSE_TEXT, simple("{\"ALL\" : []}"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"));
		//.transform().constant("<tXML><a>1</a><Message>Hello World2</Message></tXML>");

	}
}
