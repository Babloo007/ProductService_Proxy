package com.sai.ProductService_Proxy.clients.fakeStore.client;

import com.sai.ProductService_Proxy.clients.fakeStore.dto.FakeStoreProductDto;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreClient {

    RestTemplateBuilder restTemplateBuilder;

    FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private <T> ResponseEntity<T> PatchForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public List<FakeStoreProductDto> getAllProducts(){

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        return Arrays.asList(fakeStoreProductDtos.getBody());
    }

    public FakeStoreProductDto getSingleProduct(Long id){

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}", FakeStoreProductDto.class, id);

        return fakeStoreProductDto.getBody();
    }

    public FakeStoreProductDto addNewProduct(FakeStoreProductDto fakeStoreProductDto){

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);

        return responseEntity.getBody();
    }

    public FakeStoreProductDto updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto){

        ResponseEntity<FakeStoreProductDto> responseEntity = PatchForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/products/{id}", fakeStoreProductDto, FakeStoreProductDto.class, id);

        return responseEntity.getBody();
    }

    public String deleteProduct(Long id){

        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{productId}", id);

        return "Product Deleted Successfully......";
    }
}