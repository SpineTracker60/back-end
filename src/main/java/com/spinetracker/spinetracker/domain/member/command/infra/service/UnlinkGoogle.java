//package com.spinetracker.spinetracker.domain.member.command.infra.service;
//
//import com.spinetracker.spinetracker.domain.member.command.infra.GoogleUnlinkResponse;
//import com.spinetracker.spinetracker.global.common.annotation.InfraService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.util.DefaultUriBuilderFactory;
//import reactor.core.publisher.Mono;
//
//import java.util.Objects;
//
//@InfraService
//public class UnlinkGoogle {
//
//    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
//    private String GOOGLE_ACCESS_TOKEN;
//    private final WebClient webClient;
//    public UnlinkGoogle() {
//        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory("https://accounts.google.com/o/oauth2/revoke");
//        webClient = WebClient.builder().uriBuilderFactory(uriBuilderFactory).baseUrl("https://accounts.google.com/o/oauth2/revoke").build();
//    }
//    public boolean unlink(String memberUid) {
//        Mono<GoogleUnlinkResponse> response = webClient.post()
//                .uri(uriBuilder -> uriBuilder.queryParam("token", GOOGLE_ACCESS_TOKEN).build())
//                .exchangeToMono(response1 -> {
//                    if (response1.statusCode().is2xxSuccessful()) {
//                        return response1.bodyToMono(GoogleUnlinkResponse.class);
//
//                    } else {
//                        return response1.createException().flatMap(Mono::error);
//                    }
//                });
//
//        if (Objects.requireNonNull(response.block()).getId() != null ) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//}
