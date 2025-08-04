package in.trois.stockmanagement.controller.inventory;


import in.trois.stockmanagement.controller.AbstractController;
import in.trois.stockmanagement.predicate.mapping.InventoryPredicates;
import in.trois.stockmanagement.request.InventoryDto;
import in.trois.stockmanagement.request.Request;
import in.trois.stockmanagement.response.AbstractResponse;
import in.trois.stockmanagement.searchbean.mapping.InventorySearchBean;
import in.trois.stockmanagement.service.InventoryService;

import in.trois.stockmanagement.utils.ResponseBuilder;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/inventory")
@Slf4j
public class InventoryController extends AbstractController {

    private final InventoryService service;

    @PostMapping("/save")
    public ResponseEntity<AbstractResponse> saveInventory(
            @Valid @RequestBody Request<InventoryDto> request) {
        if (!(request.isValid() && request.getPayLoad().isValid(HttpMethod.POST))) {
            return new ResponseBuilder().withError(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseBuilder().withData(service.saveInventory(request.getPayLoad())).build();
    }

    @PatchMapping("/save")
    public ResponseEntity<AbstractResponse> update(
            @Valid @RequestBody Request<InventoryDto> request) {
        if (!(request.isValid() && request.getPayLoad().isValid(HttpMethod.PATCH))) {
            return new ResponseBuilder().withError(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseBuilder()
                .withData(
                        service.update(request.getPayLoad().getId(), request.getPayLoad()).toDTO())
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AbstractResponse> delete(@Valid @PathVariable UUID id) {
        return new ResponseBuilder().withData(service.softDelete(id)).build();
    }

    @GetMapping("/list/by-id")
    public ResponseEntity<AbstractResponse> findById(@RequestParam("id") UUID id) {
        return new ResponseBuilder().withData(service.findById(id)).build();
    }

    @GetMapping("/list/all")
    public ResponseEntity<AbstractResponse> findByCriteria(
            @RequestParam(name = "dropDown", required = false, defaultValue = "false")
                    boolean asDropdown,
            @Valid InventorySearchBean searchBean) {

        return new ResponseBuilder()
                .withData(
                        service.findByCriteria(
                                InventoryPredicates.createPredicate(searchBean),
                                searchBean.getDataSort(),
                                asDropdown,
                                searchBean.getPageNo(),
                                searchBean.getPageSize()))
                .build();
    }

    @GetMapping("/get/by-product-code")
    public ResponseEntity<AbstractResponse> findByProductCode(
            @RequestParam(name = "productCode") String productCode) {
        return new ResponseBuilder()
                .withData(service.getInventoryByProductCode(productCode))
                .build();
    }

    @PostMapping("/deduct-product-count/by-invoice-generation")
    public ResponseEntity<AbstractResponse> deductProductCountByInvoiceGeneration(
            @RequestBody InventoryDto request) {
        return new ResponseBuilder()
                .withData(
                        service.deductProductCountByInvoiceGeneration(
                                request.getId(), request.getQuantity()))
                .build();
    }
}
