package in.trois.stockmanagement.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDashboardResponse {
    private Long totalStock;
    private Long totalSales;
    private Double totalRevenue;
}
