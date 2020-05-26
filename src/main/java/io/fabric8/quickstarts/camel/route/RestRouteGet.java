package io.fabric8.quickstarts.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestRouteGet extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		rest("/say").id("get-say").description("GET URL JAVA DSL").consumes("application/json")
				.produces("application/json").get("/hello").outType(String.class).type(String.class).to("direct:hello");

		from("direct:hello").transform().constant("<tXML><a>1</a><Message>Hello World2</Message></tXML>");

	}
}
