package in.trois.stockmanagement.service;

import in.trois.stockmanagement.annotation.ReadTransactional;
import in.trois.stockmanagement.repository.InventoryRepository;
import in.trois.stockmanagement.response.InventoryDashboardResponse;
import in.trois.stockmanagement.response.InventoryStockSaleDashboardResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final InventoryRepository inventoryRepository;

    @ReadTransactional
    public InventoryDashboardResponse getDashboardCardResponse(UUID categoryId) {
        Object[] result = inventoryRepository.getDashboardCardResponse(categoryId);
        Object[] row = (Object[]) result[0];
        Long totalStock = row[0] != null ? ((Number) row[0]).longValue() : 0L;
        Long totalSales = row[1] != null ? ((Number) row[1]).longValue() : 0L;
        Double totalRevenue = row[2] != null ? ((Number) row[2]).doubleValue() : 0.0;
        return new InventoryDashboardResponse(totalStock, totalSales, totalRevenue);
    }

    @ReadTransactional
    public List<InventoryStockSaleDashboardResponse> getDashboardStockResponse(UUID categoryId) {
        return inventoryRepository.getMonthWiseSoldStock(categoryId).stream()
                .map(
                        obj ->
                                new InventoryStockSaleDashboardResponse(
                                        (String) obj[0],
                                        obj[1] != null ? ((Number) obj[1]).longValue() : 0L,
                                        null))
                .toList();
    }

    @ReadTransactional
    public List<InventoryStockSaleDashboardResponse> getDashboardSalesResponse(UUID categoryId) {
        return inventoryRepository.getMonthWiseSalesRevenue(categoryId).stream()
                .map(
                        obj ->
                                new InventoryStockSaleDashboardResponse(
                                        (String) obj[0],
                                        null,
                                        obj[1] != null ? ((Number) obj[1]).longValue() : 0.0))
                .toList();
    }

    @ReadTransactional
    public List<InventoryStockSaleDashboardResponse> getLast7DaysSalesTrend(UUID categoryId) {
        return inventoryRepository.getLast7DaysSalesTrend(categoryId).stream()
                .map(
                        obj -> {
                            Date date = (Date) obj[0];
                            Double totalRevenue =
                                    obj[1] != null ? ((Number) obj[1]).doubleValue() : 0.0;

                            String dateStr = (date != null) ? date.toString() : "";

                            return new InventoryStockSaleDashboardResponse(
                                    dateStr, null, totalRevenue);
                        })
                .toList();
    }
}
