package in.trois.stockmanagement.repository;

import in.trois.stock.auth.lib.service.repository.AbstractRepository;
import in.trois.stockmanagement.entity.Inventory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends AbstractRepository<Inventory, UUID> {

    Optional<Inventory> findByProductCodeAndStatus(String productCode, int status);

    Optional<Inventory> findByProductCode(String productCode);

    List<Inventory> findByProductCodeInAndStatus(List<String> productCode, int status);

    @Query(
            value =
                    """
        SELECT
            SUM(i.remaining_quantity) AS totalStock,
            SUM(i.total_quantity - i.remaining_quantity) AS totalSales,
            SUM((i.total_quantity - i.remaining_quantity) * icm.selling_price) AS totalRevenue
        FROM stock_management.inventory i
        JOIN stock_management.inventory_commercials_mapping icm ON i.id = icm.inventory_id
        WHERE (:categoryId IS NULL OR i.category_id = :categoryId)
        """,
            nativeQuery = true)
    Object[] getDashboardCardResponse(@Param("categoryId") UUID categoryId);

    @Query(
            value =
                    """
        SELECT i.month, SUM(i.total_quantity - i.remaining_quantity) AS sold_stock
        FROM stock_management.inventory i
        JOIN stock_management.inventory_commercials_mapping icm ON i.id = icm.inventory_id
        WHERE (:categoryId IS NULL OR i.category_id = :categoryId)
        GROUP BY i.month
        ORDER BY date_part('month', to_date(i.month, 'Mon'))
        """,
            nativeQuery = true)
    List<Object[]> getMonthWiseSoldStock(@Param("categoryId") UUID categoryId);

    @Query(
            value =
                    """
        SELECT i.month, SUM(icm.selling_price) AS total_revenue
        FROM stock_management.inventory i
        JOIN stock_management.inventory_commercials_mapping icm ON i.id = icm.inventory_id
        WHERE (:categoryId IS NULL OR i.category_id = :categoryId)
        GROUP BY i.month
        ORDER BY date_part('month', to_date(i.month, 'Mon'))
        """,
            nativeQuery = true)
    List<Object[]> getMonthWiseSalesRevenue(@Param("categoryId") UUID categoryId);

    @Query(
            value =
                    """
        SELECT i.created_at::date AS sale_date, SUM(icm.selling_price) AS total_revenue
        FROM stock_management.inventory i
        JOIN stock_management.inventory_commercials_mapping icm ON i.id = icm.inventory_id
        WHERE i.created_at >= CURRENT_DATE - INTERVAL '7 days'
          AND (:categoryId IS NULL OR i.category_id = :categoryId)
        GROUP BY sale_date
        ORDER BY sale_date
        """,
            nativeQuery = true)
    List<Object[]> getLast7DaysSalesTrend(@Param("categoryId") UUID categoryId);
}
