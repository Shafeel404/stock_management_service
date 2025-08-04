package in.trois.stockmanagement.repository;

import in.trois.stockmanagement.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ShopRepository extends JpaRepository<Shop, UUID> {
} 