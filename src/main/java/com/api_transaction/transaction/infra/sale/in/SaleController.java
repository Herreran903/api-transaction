package com.api_transaction.transaction.infra.sale.in;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.api_transaction.transaction.infra.util.Urls.SALE_URL;

@RestController
@RequestMapping(SALE_URL)
@Validated
@RequiredArgsConstructor
public class SaleController {
    private static final String ADD_SALE_ADD = "/add";

    @PostMapping(ADD_SALE_ADD)
    @Transactional
    @PreAuthorize("hasRole(T(com.api_transaction.transaction.domain.role.util.RoleEnum).ROLE_CLIENT.toString())")
    ResponseEntity<Void> addSale(){
        return ResponseEntity.ok().build();
    }
}
