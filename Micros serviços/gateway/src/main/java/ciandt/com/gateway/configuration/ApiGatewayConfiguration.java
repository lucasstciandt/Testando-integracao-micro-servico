package ciandt.com.gateway.configuration;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {


    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder locatorBuilder){

        return locatorBuilder
                .routes()
                .route(
                        predicateSpec -> predicateSpec.path("/get")
                                .filters(gatewayFilter -> gatewayFilter
                                        .addRequestHeader("Hello","World")
                                        .addRequestParameter("Olá","Mundo")
                        ).uri("http://httpbin.org:80")
                )
                .route(
                        predicateSpec -> predicateSpec.path("/currency-exchange/**").uri("lb://currency-exchange")
                )
                .route(
                        predicateSpec -> predicateSpec.path("/currency-conversion/**")
                                .uri("lb://currency-exchange")
                )
                .build();
    }
}
