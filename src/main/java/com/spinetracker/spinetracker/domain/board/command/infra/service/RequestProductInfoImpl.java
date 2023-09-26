package com.spinetracker.spinetracker.domain.board.command.infra.service;

import com.spinetracker.spinetracker.domain.board.command.domain.service.RequestProductInfo;
import com.spinetracker.spinetracker.domain.board.command.infra.response.CoupangOpenGraphResponse;
import com.spinetracker.spinetracker.global.common.annotation.InfraService;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

@InfraService
public class RequestProductInfoImpl implements RequestProductInfo {

    private final WebClient webClient;
    public RequestProductInfoImpl() {
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory("http://192.168.0.19:3000");
        webClient = WebClient.builder().uriBuilderFactory(uriBuilderFactory).baseUrl("http://192.168.0.19:3000").build();
    }


    public CoupangOpenGraphResponse getCoupangInfo(String productUrl) {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("product_url", productUrl);

        Mono<CoupangOpenGraphResponse> response = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/product/coupang").build()
                ).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData(formData))
                .exchangeToMono(response1 -> {
                    if (response1.statusCode().is2xxSuccessful()) {
                        return response1.bodyToMono(CoupangOpenGraphResponse.class);
                    } else {
                        return response1.createException().flatMap(Mono::error);
                    }
                });

            return response.block();

        }
    }

