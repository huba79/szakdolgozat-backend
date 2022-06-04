
package crengine.api;

import crengine.messages.ReservationMessage;
import crengine.messages.ReservationResponse;

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
import java.util.Date;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-30T08:17:32.900Z[GMT]")
@Validated
public interface ReservationApi {
    /**
     *
     * @param id
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
    ResponseEntity<ReservationResponse> getReservationById(
            @PathVariable("id") Long id
    );  

    //GetReservations by query params
    @Operation(summary = "Get a list of reservations", 
            description = "Get Reservation by query", 
            security = {
                @SecurityRequirement(name = "ApiKeyAuth")    
            }, tags={"users", "admins" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieval succesful"),
        @ApiResponse(responseCode = "204", description = "No content found"),        
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "401", description = "Not Authorized"),
        @ApiResponse(responseCode = "500", description = "Server error") })
    @RequestMapping(
            value = "reservation", 
            method = RequestMethod.GET,
            produces = "application/json"  )
    ResponseEntity<ArrayList<ReservationResponse>> getReservationsByQuery(
        @RequestParam (name="lakeId", required = false) Long lakeId,
        @RequestParam (name="stageId", required = false) Long stageId,
        @RequestParam (name="userId", required = false) Long userId,
        @RequestParam (name="dateFrom", required = false) Date dateFrom,
        @RequestParam (name="dateTo", required = false) Date dateTo,
        @RequestParam (name="status", required = false) String status 
    ); 
    
 // Post Reservation
    @Operation(summary = "Registers a reservation", description = "Save a new reservation", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "users", "admins" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieval succesful"),
        @ApiResponse(responseCode = "204", description = "No content found"),        
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "401", description = "Not Authorized"),
        @ApiResponse(responseCode = "500", description = "Server error") })
    @RequestMapping(
            value = "reservation/",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces ="application/json"
        )
    ResponseEntity<ReservationResponse> newReservation(
        @Parameter(in = ParameterIn.DEFAULT, 
                    description = "New Reservation data", required= true,
                        schema=@Schema()) 
        @Valid @RequestBody ReservationMessage body);     

 //TODO define put Reservation
    @Operation(summary = "Updates a reservation", description = "Updates a new reservation by Id", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "users", "admins" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieval succesful"),
        @ApiResponse(responseCode = "204", description = "No content found"),        
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "401", description = "Not Authorized"),
        @ApiResponse(responseCode = "500", description = "Server error") })
    @RequestMapping(
            value = "reservation/{id}",
            method = RequestMethod.PUT
            , consumes = "application/json"
            , produces = "application/json"
        )
    
//    @RequestBody(
//            required = true,
//            content = @Content(
//                            schema = @Schema(implementation = ReservationMessage.class),
//                            mediaType = MediaType.APPLICATION_JSON_VALUE ) 
//    )
    ResponseEntity<ReservationResponse> updateReservation(
    @Parameter(in = ParameterIn.PATH, 
                description = "Updated Reservation data", 
                required = true,
                schema=@Schema()) @PathVariable("id") Long id, 
    @Valid @RequestBody ReservationMessage body); 

}

