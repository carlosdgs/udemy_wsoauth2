package com.hugosilva.curso.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(
            RequestHandlerSelectors.basePackage(
                "br.com.hugosilva.curso.ws.controller"))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false)
        .directModelSubstitute(ResponseEntity.class, java.lang.Void.class)
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("API acesso de usuário")
        .description("API para treinamento de Spring Boot Oauth2")
        .contact(new Contact("Carlos", "www.sicredi.com.br", "carlos.delfeu@gmail.com"))
        .license("Licença da API")
        .licenseUrl("www.sicredi.com.br/licensa")
        .version("1.0.0")
        .termsOfServiceUrl("Termos de serviço")
        .build();
  }
}
