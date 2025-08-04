package in.trois.stockmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FoodSearchService {

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUrl = "https://world.openfoodfacts.org/cgi/search.pl";

    public String searchFood(String query) {
        String url = baseUrl + "?search_terms=" + query + "&search_simple=1&action=process&json=1";
        return restTemplate.getForObject(url, String.class);
    }
} 