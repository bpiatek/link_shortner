package pl.bpiatek.linkshortner.infrastructure.configuration;

import static java.util.Collections.singletonList;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Bartosz Piatek on 31/10/2019
 */
@EnableSwagger2
@Configuration
class SwaggerConfiguration {

  @Bean
  Docket swaggerApi() {
    return new Docket(SWAGGER_2)
        .groupName("pl.bpiatek")
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .securityContexts(singletonList(actuatorSecurityContext()))
        .securitySchemes(singletonList(basicAuthScheme()));
  }

  private SecurityContext actuatorSecurityContext() {
    return SecurityContext.builder()
        .securityReferences(singletonList(basicAuthReference()))
        .forPaths(PathSelectors.any())
        .build();
  }

  private SecurityScheme basicAuthScheme() {
    return new BasicAuth("basicAuth");
  }

  private SecurityReference basicAuthReference() {
    return new SecurityReference("basicAuth", new AuthorizationScope[0]);
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("LinkShortener")
        .description("Web application created for managing and shortening links")
        .contact(new Contact("Bartosz Piątek",
                             "http://bpiatek.pl",
                             "piatekbart@gmail.com"))
        .version("0.0.1")
        .build();
  }
}
