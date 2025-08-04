package in.trois.stockmanagement.controller.user;

import in.trois.stock.auth.lib.service.ResponseBuilder;
import in.trois.stock.auth.lib.service.controller.AbstractController;
import in.trois.stock.auth.lib.service.request.Request;
import in.trois.stock.auth.lib.service.response.AbstractResponse;
import in.trois.stock.auth.lib.service.response.Response;
import in.trois.stockmanagement.predicate.UserPredicates;
import in.trois.stockmanagement.request.AuthRequestDto;
import in.trois.stockmanagement.searchbean.UserSearchBean;
import in.trois.stockmanagement.service.UserService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping(value = "/no-auth/user")
public class UserController extends AbstractController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response<?>> register(@RequestBody Request<AuthRequestDto> request) {

        userService.register(request.getPayLoad());
        return new ResponseEntity<>(
                Response.<String>builder()
                        .payload("User saved successfully")
                        .message("Success")
                        .build(),
                HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<Response<?>> update(@RequestBody Request<AuthRequestDto> request) {

        userService.update(request.getPayLoad());
        return new ResponseEntity<>(
                Response.<String>builder()
                        .payload("User updated successfully")
                        .message("Success")
                        .build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AbstractResponse> delete(@Valid @PathVariable UUID id) {
        return new ResponseBuilder().withData(userService.delete(id)).build();
    }

    @GetMapping("/list/all")
    public ResponseEntity<AbstractResponse> findByCriteria(
            @RequestParam(name = "dropDown", required = false, defaultValue = "false")
                    boolean asDropdown,
            @Valid UserSearchBean searchBean) {
        return new ResponseBuilder()
                .withData(
                        userService.findByCriteria(
                                UserPredicates.createPredicate(searchBean),
                                searchBean.getDataSort(),
                                asDropdown,
                                searchBean.getPageNo(),
                                searchBean.getPageSize()))
                .build();
    }
}
