package in.trois.stockmanagement.repository;

import in.trois.stockmanagement.entity.ShopProductMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ShopProductMappingRepository extends JpaRepository<ShopProductMapping, UUID> {
    List<ShopProductMapping> findByProductCode(String productCode);
    List<ShopProductMapping> findByProductName(String productName);
} 