package in.trois.stockmanagement.controller.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.trois.stockmanagement.service.FoodSearchService;

@RestController
@RequestMapping("/external")
public class ExternalApiController {

    @Autowired
    private FoodSearchService foodSearchService;

    @GetMapping("/food-search")
    public ResponseEntity<String> searchFood(@RequestParam("query") String query) {
        String result = foodSearchService.searchFood(query);
        return ResponseEntity.ok(result);
    }
} 