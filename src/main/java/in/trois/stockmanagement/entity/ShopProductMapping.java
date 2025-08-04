package in.trois.stockmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "shop_product_mapping", schema = "stock_management")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopProductMapping {
    @Id
    private UUID id;

    @Column(name = "shop_id")
    private UUID shopId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "category")
    private String category;
   
} 