
package io.swagger.api;

import io.swagger.messages.LakeResponse;
import io.swagger.messages.LakesResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")
@Validated
public interface LakeService {

    
    //GetLakes
    @Operation(summary = "lakes", description = "Lake operations", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "guests", "users", "admins" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieval succesful"),

        @ApiResponse(responseCode = "400", description = "Invalid request"),

        @ApiResponse(responseCode = "401", description = "Not Authorized"),

        @ApiResponse(responseCode = "500", description = "Server error") })
    @RequestMapping(value = "lakes/{companyId}/",
        method = RequestMethod.GET)
    ResponseEntity<LakesResponse> getLakes(
            @Parameter(in = ParameterIn.PATH, 
                    description = "the id of the company delivering the app", 
                        required=true, schema=@Schema()) 
                            @PathVariable("companyId") Long companyId);
    
    //GetLakeBy ID
    @Operation(summary = "lakes", description = "Lake operations", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "guests", "users", "admins" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieval succesful"),

        @ApiResponse(responseCode = "400", description = "Invalid request"),

        @ApiResponse(responseCode = "401", description = "Not Authorized"),

        @ApiResponse(responseCode = "500", description = "Server error") })
    @RequestMapping(value = "lakes/{companyId}/{lakeId}",
        method = RequestMethod.GET)
    ResponseEntity<LakeResponse> getLakeById(
            @Parameter(in = ParameterIn.PATH, 
                    description = "the id of the company delivering the app", 
                        required=true, schema=@Schema()) 
                            @PathVariable("companyId") Long companyId,
            @Parameter(in = ParameterIn.PATH, 
                    description = "the id of the company delivering the app", 
                        required=true, schema=@Schema()) 
                            @PathVariable("lakeId") Long lakeId); 
}

