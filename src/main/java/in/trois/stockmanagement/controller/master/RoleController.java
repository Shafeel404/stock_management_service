package in.trois.stockmanagement.controller.master;

import in.trois.stock.auth.lib.service.ResponseBuilder;
import in.trois.stock.auth.lib.service.controller.AbstractController;
import in.trois.stock.auth.lib.service.request.Request;
import in.trois.stock.auth.lib.service.response.AbstractResponse;
import in.trois.stockmanagement.predicate.master.RolePredicates;
import in.trois.stockmanagement.request.master.RoleDto;
import in.trois.stockmanagement.searchbean.master.RoleSearchBean;
import in.trois.stockmanagement.service.master.RoleService;

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
@RequestMapping(value = "/role")
@Slf4j
public class RoleController extends AbstractController {

    private final RoleService service;

    @PostMapping("/save")
    public ResponseEntity<AbstractResponse> save(@Valid @RequestBody Request<RoleDto> request) {

        if (!(request.isValid() && request.getPayLoad().isValid(HttpMethod.POST))) {
            return new ResponseBuilder().withError("Invalid api").build();
        }
        return new ResponseBuilder().withData(service.save(request.getPayLoad()).toDTO()).build();
    }

    @PatchMapping("/save")
    public ResponseEntity<AbstractResponse> update(@Valid @RequestBody Request<RoleDto> request) {
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
            @Valid RoleSearchBean searchBean) {
        return new ResponseBuilder()
                .withData(
                        service.findByCriteria(
                                RolePredicates.createPredicate(searchBean),
                                searchBean.getDataSort(),
                                asDropdown,
                                searchBean.getPageNo(),
                                searchBean.getPageSize()))
                .build();
    }
}
