package in.trois.stockmanagement.controller.dashboard;

import in.trois.stockmanagement.response.AbstractResponse;
import in.trois.stockmanagement.service.DashboardService;

import in.trois.stockmanagement.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "dashboard/inventory")
@Slf4j
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/total")
    public ResponseEntity<AbstractResponse> getInventorySummary(
            @RequestParam(value = "categoryId", required = false) UUID categoryId) {
        return new ResponseBuilder()
                .withData(dashboardService.getDashboardCardResponse(categoryId))
                .build();
    }

    @GetMapping("/month-wise-stock")
    public ResponseEntity<AbstractResponse> getMonthWiseStockSummary(
            @RequestParam(value = "categoryId", required = false) UUID categoryId) {
        return new ResponseBuilder()
                .withData(dashboardService.getDashboardStockResponse(categoryId))
                .build();
    }

    @GetMapping("/month-wise-sales")
    public ResponseEntity<AbstractResponse> getMonthWiseSalesSummary(
            @RequestParam(value = "categoryId", required = false) UUID categoryId) {
        return new ResponseBuilder()
                .withData(dashboardService.getDashboardSalesResponse(categoryId))
                .build();
    }

    @GetMapping("/week-wise-sales")
    public ResponseEntity<AbstractResponse> getLast7DaysSalesTrend(
            @RequestParam(value = "categoryId", required = false) UUID categoryId) {
        return new ResponseBuilder()
                .withData(dashboardService.getLast7DaysSalesTrend(categoryId))
                .build();
    }
}
