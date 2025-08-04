package in.trois.stockmanagement.controller.master;

import in.trois.stock.auth.lib.service.ResponseBuilder;
import in.trois.stock.auth.lib.service.controller.AbstractController;
import in.trois.stock.auth.lib.service.request.Request;
import in.trois.stock.auth.lib.service.response.AbstractResponse;
import in.trois.stockmanagement.predicate.master.CategoryPredicates;
import in.trois.stockmanagement.request.master.CategoryDto;
import in.trois.stockmanagement.searchbean.master.CategorySearchBean;
import in.trois.stockmanagement.service.master.CategoryService;

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
@RequestMapping(value = "/category")
@Slf4j
public class CategoryController extends AbstractController {

    private final CategoryService service;

    @PostMapping("/save")
    public ResponseEntity<AbstractResponse> saveCategory(
            @Valid @RequestBody Request<CategoryDto> request) {

        if (!(request.isValid() && request.getPayLoad().isValid(HttpMethod.POST))) {
            return new ResponseBuilder().withError("Invalid api").build();
        }
        return new ResponseBuilder().withData(service.save(request.getPayLoad()).toDTO()).build();
    }

    @PatchMapping("/save")
    public ResponseEntity<AbstractResponse> update(
            @Valid @RequestBody Request<CategoryDto> request) {
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
        return new ResponseBuilder().withData(service.findByID(id).get().toDTO()).build();
    }

    @GetMapping("/list/all")
    public ResponseEntity<AbstractResponse> findByCriteria(
            @RequestParam(name = "dropDown", required = false, defaultValue = "false")
                    boolean asDropdown,
            @Valid CategorySearchBean searchBean) {

        return asDropdown
                ? new ResponseBuilder()
                        .withData(
                                service.findByCriteria(
                                        CategoryPredicates.createPredicate(searchBean),
                                        searchBean.getDataSort(),
                                        true,
                                        searchBean.getPageNo(),
                                        searchBean.getPageSize()))
                        .build()
                : new ResponseBuilder()
                        .withData(
                                service.findByCriteria(
                                        CategoryPredicates.createPredicate(searchBean),
                                        searchBean.getDataSort(),
                                        searchBean.getPageNo(),
                                        searchBean.getPageSize()))
                        .build();
    }
}
