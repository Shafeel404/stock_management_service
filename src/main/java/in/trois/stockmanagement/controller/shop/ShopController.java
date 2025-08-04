package in.trois.stockmanagement.controller.shop;

import in.trois.stockmanagement.request.ShopDto;
import in.trois.stockmanagement.service.ShopService;
import in.trois.stockmanagement.service.ShopProductMappingService;
import in.trois.stockmanagement.entity.ShopProductMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shops")
@Tag(name = "Shop", description = "Shop management APIs")
@SecurityRequirement(name = "bearerAuth")
public class ShopController {

    private final ShopService shopService;
    private final ShopProductMappingService shopProductMappingService;

    @PostMapping
    @Operation(summary = "Create shop", description = "Create a new shop")
    public ShopDto createShop(@RequestBody ShopDto dto) {
        return shopService.createShop(dto);
    }

    @GetMapping
    @Operation(summary = "Get all shops", description = "Retrieve all shops")
    public List<ShopDto> getAllShops() {
        return shopService.getAllShops();
    }

    @GetMapping("/by-product")
    @Operation(summary = "Get shops by product", description = "Get all shops that have a specific product")
    public List<ShopProductMapping> getShopsByProductCode(@RequestParam("productCode") String productCode) {
        return shopProductMappingService.getByProductCode(productCode);
    }
} 