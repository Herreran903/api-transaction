package com.api_transaction.transaction.infra.supply.in;

import com.api_transaction.transaction.app.supply.dto.RestockDateResponse;
import com.api_transaction.transaction.app.supply.dto.RestockDateRequest;
import com.api_transaction.transaction.app.supply.dto.SupplyRequest;
import com.api_transaction.transaction.app.supply.handler.ISupplyHandler;
import com.api_transaction.transaction.infra.exception.ExceptionDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.api_transaction.transaction.domain.util.GlobalConstants.HEADER_STRING;
import static com.api_transaction.transaction.infra.security.jwt.JwtUtility.extractJwt;
import static com.api_transaction.transaction.infra.supply.util.SupplySwaggerMessages.INCREASE_SUPPLY_REQUEST_EXAMPLE;
import static com.api_transaction.transaction.infra.util.SwaggerMessages.*;
import static com.api_transaction.transaction.infra.util.Urls.SUPPLY_URL;

@RestController
@RequestMapping(SUPPLY_URL)
@Validated
@RequiredArgsConstructor
public class SupplyController {
    private static final String ADD_SUPPLY_ADD = "/add";
    private static final String GET_RESTOCK_DATE = "/restock-date";

    private final ISupplyHandler supplyHandler;

    @Operation(
            summary = "Increase the number of supplies of a product",
            description = "This endpoint allows increase the number of supplies of a produc",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body to increase the number of supplies of a product ",
                    content = @Content(
                            schema = @Schema(implementation = SupplyRequest.class),
                            examples = {
                                    @ExampleObject(value = INCREASE_SUPPLY_REQUEST_EXAMPLE)
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_200, description = RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = CODE_400, description = RESPONSE_400_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ExceptionDetails.class))
            ),
            @ApiResponse(responseCode = CODE_500, description = RESPONSE_500_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ExceptionDetails.class))
            )
    })
    @PostMapping(ADD_SUPPLY_ADD)
    @PreAuthorize("hasRole(T(com.api_transaction.transaction.domain.role.util.RoleEnum).ROLE_WAREHOUSE_ASSISTANT.toString())")
    ResponseEntity<Void> addSupply(
            @Valid @RequestBody SupplyRequest supplyRequest,
            @RequestHeader(HEADER_STRING) String authorization) {
        String token = extractJwt(authorization);
        supplyHandler.createSupply(supplyRequest, token);

        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Get the restock date of a product supply",
            description = "This endpoint allows retrieving the estimated restock date for a product's supply",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body to get the restock date for a product supply",
                    content = @Content(
                            schema = @Schema(implementation = SupplyRequest.class),
                            examples = {
                                    @ExampleObject(value = INCREASE_SUPPLY_REQUEST_EXAMPLE)
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = CODE_200, description = RESPONSE_200_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = RestockDateResponse.class))
            ),
            @ApiResponse(responseCode = CODE_400, description = RESPONSE_400_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ExceptionDetails.class))
            ),
            @ApiResponse(responseCode = CODE_500, description = RESPONSE_500_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ExceptionDetails.class))
            )
    })
    @PostMapping(GET_RESTOCK_DATE)
    @PreAuthorize("hasRole(T(com.api_transaction.transaction.domain.role.util.RoleEnum).ROLE_CLIENT.toString())")
    ResponseEntity<RestockDateResponse> getRestockDate(@Valid @RequestBody RestockDateRequest restockDateRequest) {
        RestockDateResponse restockDateResponse = supplyHandler.getRestockDate(restockDateRequest.getProduct());

        return ResponseEntity.ok(restockDateResponse);
    }
}
