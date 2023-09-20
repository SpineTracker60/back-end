package com.spinetracker.spinetracker.global.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class SwaggerConfiguration {
    private final String securitySchemeName = "bearerAuth";

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        Info info = new Info().title("SpineTracker REST API Document").version(appVersion)
                .description("Spring Boot를 이용한 SpineTracker 웹 애플리케이션 API 도큐먼트입니다.")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact().name("JIWON").url("https://github.com/jiwon11").email("jeewonre@gmail.com"))
                .contact(new Contact().name("namhyojeong").url("https://github.com/namhyojeong").email("namgywjd2@gmail.com"))
                .license(new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));


        Schema postureTime = new Schema<Map<String, Object>>()
                .addProperties("ASYMMETRY",new IntegerSchema().example(80))
                .addProperties("TEXTNECK",new IntegerSchema().example(161))
                .addProperties("STOOPED",new IntegerSchema().example(0))
                .addProperties("SLEEPINESS",new IntegerSchema().example(300))
                .description("일간 자세별 지속 시간 입니다. (단위 : 초)")
        ;

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                    )
                        .addSchemas("PostureTime", postureTime)
                )
                .info(info);
    }
}