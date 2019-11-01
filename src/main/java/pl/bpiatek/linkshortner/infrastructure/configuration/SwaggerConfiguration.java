package pl.bpiatek.linkshortner.infrastructure.configuration;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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
        .build();
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
