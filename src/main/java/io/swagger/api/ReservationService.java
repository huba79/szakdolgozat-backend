
package io.swagger.api;

import io.swagger.messages.BaseDataResponse;

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
import java.util.Date;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")
@Validated
public interface ReservationService {
    /**
     *
     * @param id
     * @param companyId
     * @return ResponseEntity<ReservationResponse>
     */
    //TODO define getReservation By Id
    @Operation(summary = "Get one reservation", description = "Get Reservation by id", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "guests", "users", "admins" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieval succesful"),

        @ApiResponse(responseCode = "204", description = "No content found"),
        
        @ApiResponse(responseCode = "400", description = "Invalid request"),

        @ApiResponse(responseCode = "401", description = "Not Authorized"),

        @ApiResponse(responseCode = "500", description = "Server error") })
    @RequestMapping(
            value = "reservation/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
        )
    ResponseEntity<BaseDataResponse> getReservationById(
            @Parameter(in = ParameterIn.PATH, 
                    description = "the id of the reservation", 
                        required=true, schema=@Schema()) 
                            @PathVariable("id") Long id);  

    //TODO define and FINISH getReservations by query params
    @Operation(summary = "Get a list of reservations", 
            description = "Get Reservation by query", 
            security = {
                @SecurityRequirement(name = "ApiKeyAuth")    
            }, tags={ "guests", "users", "admins" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieval succesful"),

        @ApiResponse(responseCode = "204", description = "No content found"),
        
        @ApiResponse(responseCode = "400", description = "Invalid request"),

        @ApiResponse(responseCode = "401", description = "Not Authorized"),

        @ApiResponse(responseCode = "500", description = "Server error") })
    @RequestMapping(
            value = "reservations/{id}{lakeId}{stageId}{userId}{dateFrom}{dateTo}{status}",
            method = RequestMethod.GET,
            produces = "application/json"  )
    ResponseEntity<BaseDataResponse> getReservationsByQuery(
            @Parameter(in = ParameterIn.PATH, 
                    description = "the id of the reservation", 
                        required=true, schema=@Schema()) 
                            @PathVariable("id") Long id,
            @Parameter(in = ParameterIn.QUERY, 
                    description = "the id of the reservation", 
                        required=false, schema=@Schema()) 
                            @RequestParam ("lakeId") Long lakeId,
            @Parameter(in = ParameterIn.QUERY, 
                    description = "the id of the reservation", 
                        required=false, schema=@Schema()) 
                            @RequestParam ("stageId") Long stageId,
            @Parameter(in = ParameterIn.QUERY, 
                    description = "the id of the reservation", 
                        required=false, schema=@Schema()) 
                            @RequestParam ("userId") Long userId,
            @Parameter(in = ParameterIn.QUERY, 
                    description = "the id of the reservation", 
                        required=false, schema=@Schema()) 
                            @RequestParam ("dateFrom") Date dateFrom,
            @Parameter(in = ParameterIn.QUERY, 
                    description = "the id of the reservation", 
                        required=false, schema=@Schema()) 
                            @RequestParam ("dateTo") Date dateTo,
            @Parameter(in = ParameterIn.QUERY, 
                    description = "the id of the reservation", 
                        required=false, schema=@Schema()) 
                            @RequestParam ("status") String status ); 

}

