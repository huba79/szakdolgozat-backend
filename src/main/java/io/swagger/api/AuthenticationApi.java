
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
import org.springframework.web.bind.annotation.PathVariable;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")
@Validated
public interface AuthenticationApi {

    @Operation(summary = "Login", description = "Attempts to login to the system", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "guests", "users", "admins" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login succesful"),

        @ApiResponse(responseCode = "400", description = "Invalid request"),

        @ApiResponse(responseCode = "401", description = "Not Authorized"),

        @ApiResponse(responseCode = "500", description = "Server error") }
    )
    @RequestMapping(value = "login/",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<LoginResponse> login(@Parameter(in = ParameterIn.DEFAULT, 
            description = "Login data", 
                schema=@Schema()) @Valid @RequestBody LoginMessage body);

    @Operation(summary = "Logout", description = "Attempts to login to the system", security = {
    @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "users", "admins" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Logout succesful"),

        @ApiResponse(responseCode = "204", description = "Data not found"),

        @ApiResponse(responseCode = "400", description = "Invalid request"),

        @ApiResponse(responseCode = "401", description = "Not Authorized"),

        @ApiResponse(responseCode = "500", description = "Server error") }
    )
    @RequestMapping(value = "logout/{id}",
        method = RequestMethod.GET)
    ResponseEntity<Void> logout(@Parameter(in = ParameterIn.PATH, 
                description = "", required=true, 
                    schema=@Schema()) @PathVariable("id") Long id);

}

