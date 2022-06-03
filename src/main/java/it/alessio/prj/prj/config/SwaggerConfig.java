package it.alessio.prj.prj.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.LinkedHashSet;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(new LinkedHashSet<>(Collections.singletonList("application/json")))
                .consumes(new LinkedHashSet<>(Collections.singletonList("application/json"))).select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()).build().genericModelSubstitutes(ResponseEntity.class)
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Privacy Manager")
                .description("Gestione Trattemento dai sensibili")
                .contact(
                        new Contact("Alessio Garbi", "https://www.linkedin.com/in/garbialessio/", "garbi.alessio@gmail.com"))
                .license("Licenza").licenseUrl("http://licenseUrl")
                .termsOfServiceUrl("http://termsOfServiceUrl").version("3.0").build();
    }
}
