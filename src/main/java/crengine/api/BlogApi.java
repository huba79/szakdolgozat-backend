
package crengine.api;


import crengine.messages.BlogResponse;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen")
@Validated
public interface BlogApi {

   //GetBlogBy CompanyID
    @Operation(summary = "Owner's Blog Entries", description = "List of the owner's blog entries, by lakes", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "guests", "users", "admins" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieval succesful"),
        @ApiResponse(responseCode = "204", description = "No content"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "401", description = "Not Authorized"),
        @ApiResponse(responseCode = "500", description = "Server error") })
    @RequestMapping(value = "blogs/{companyId}",
        method = RequestMethod.GET)
    ResponseEntity<ArrayList<BlogResponse>> getBlogEntryByCompanyId(
            @Parameter(in = ParameterIn.PATH, 
                    description = "the id of the company delivering the app", 
                        required=true, schema=@Schema()) 
                            @PathVariable("companyId") Long companyId); 
}

