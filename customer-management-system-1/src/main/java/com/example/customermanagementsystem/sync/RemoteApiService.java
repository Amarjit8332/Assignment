package com.example.customermanagementsystem.sync;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.customermanagementsystem.product.Customer;

@Service
public class RemoteApiService {
    @Value("${remote.api.url}")
    private String remoteApiUrl;

    @Value("${remote.api.username}")
    private String remoteApiUsername;

    @Value("${remote.api.password}")
    private String remoteApiPassword;

    public List<Customer> fetchCustomers() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(remoteApiUsername, remoteApiPassword);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Customer>> response = restTemplate.exchange(remoteApiUrl, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Customer>>() {});
        return response.getBody();
    }
}
