package cz.ivosahlik.zomatoapi.config;//http://localhost:8080/v2/api-docs
//http://localhost:8080/swagger-ui.html

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfo("My REST API", "Api Documentation", "1.0", "urn:tos", new Contact("Ivo Vošahlík","https://github.com/ivosahlik/spring-daily-menu-zomato-api","ivosahlik@seznam.cz"), "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
    }



}