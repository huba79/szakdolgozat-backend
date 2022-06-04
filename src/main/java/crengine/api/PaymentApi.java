
package crengine.api;

import crengine.messages.PaymentResponse;

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
public interface PaymentApi {
    
//    //GetPayment --refactiored--not sure will need it any more
//    @Operation(summary = "payment", description = "payment", security = {
//        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "users", "admins" })
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "Retrieval succesful"),
//
//        @ApiResponse(responseCode = "400", description = "Invalid request"),
//
//        @ApiResponse(responseCode = "401", description = "Not Authorized"),
//
//        @ApiResponse(responseCode = "500", description = "Server error") })
//    @RequestMapping(value = "payments/{reservationId}/{id}",
//        method = RequestMethod.GET)
//    ResponseEntity<PaymentResponse> getPaymentById(
//            @Parameter(in = ParameterIn.PATH, 
//                    description = "the id of the company delivering the app", 
//                        required=true, schema=@Schema()) 
//                            @PathVariable("id") Long id);
    
    //Get Payment by ReservationID
    @Operation(summary = "Payments", description = "Payments for reservations", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={"users", "admins" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieval succesful"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "401", description = "Not Authorized"),
        @ApiResponse(responseCode = "500", description = "Server error") 
    })
    @RequestMapping(value = "payments/{reservationId}",
        method = RequestMethod.GET)
    ResponseEntity<PaymentResponse> getPaymentByReservationId(
            @Parameter(in = ParameterIn.PATH, 
                    description = "the id of the company delivering the app", 
                        required=true, schema=@Schema()) 
                            @PathVariable("reservationId") Long reservationId); 

}

