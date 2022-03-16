/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.27).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.messages.LoginMessage;
import io.swagger.messages.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.messages.RegistrationMessage;
import io.swagger.messages.RegistrationResponse;
import org.springframework.web.bind.annotation.PathVariable;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")
@Validated
public interface RegistrationApi {
    
//REGISTRATION 
    @Operation(summary = "Registration", description = "Attempts to register into the system", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "guests", "users", "admins" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registration succesful"),

        @ApiResponse(responseCode = "400", description = "Invalid request"),

        @ApiResponse(responseCode = "401", description = "Not Authorized"),

        @ApiResponse(responseCode = "500", description = "Server error") })
    @RequestMapping(value = "registration/",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<RegistrationResponse> registration(@Parameter(in = ParameterIn.DEFAULT, description = "Registration data", schema=@Schema()) @Valid @RequestBody RegistrationMessage body);

}

