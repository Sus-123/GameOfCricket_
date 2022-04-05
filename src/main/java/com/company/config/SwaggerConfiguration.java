package com.company.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerCongiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build();

    }

//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "GameOfCricket_",
//                "Api for Game of Cricket",
//                "1.0.0",
//                "NXAJBX",
//                new Contact(
//                        "KSDK",
//                        "http:sushant.com",
//                        "skdnk.com"),
//                "sjdnjsd",
//                "sknjs.com",
//                Collections.emptyList());
//    }

    //http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/




}
