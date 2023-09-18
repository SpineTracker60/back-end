package com.spinetracker.spinetracker.domain.member.command.infra.service;

import com.spinetracker.spinetracker.domain.member.command.infra.KakaoUnlinkResponse;
import com.spinetracker.spinetracker.global.common.annotation.InfraService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.util.Objects;


@InfraService
public class UnlinkKakao {

    @Value("${app.auth.kakao-admin-key}")
    private String KAKAO_ACCESS_TOKEN;
    private final WebClient webClient;
    public UnlinkKakao() {
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory("https://kapi.kakao.com/v1/user/unlink");
        webClient = WebClient.builder().uriBuilderFactory(uriBuilderFactory).baseUrl("https://kapi.kakao.com/v1/user/unlink").build();
    }


    public boolean unlink(String memberUid) {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("target_id_type", "user_id");
        formData.add("target_id", memberUid);

        Mono<KakaoUnlinkResponse> response = webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization", "KakaoAK " + KAKAO_ACCESS_TOKEN)
                .body(BodyInserters.fromFormData(formData))
                .exchangeToMono(response1 -> {
                    if (response1.statusCode().is2xxSuccessful()) {
                        return response1.bodyToMono(KakaoUnlinkResponse.class);
                    } else {
                        return response1.createException().flatMap(Mono::error);
                    }
                });

        if (Objects.requireNonNull(response.block()).getId() != null ) {
            return true;
        } else {
            return false;
        }
    }


}
