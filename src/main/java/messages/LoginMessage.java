package messages;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Guests, clients, admins and managers(owners)
 */
@Schema(description = "Guests, clients, admins and managers(owners)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-28T16:04:50.838Z[GMT]")

public class LoginMessage   {

  @JsonProperty("loginname")
  private String loginname = null;

  @JsonProperty("password")
  private String password = null;
  
  @JsonProperty("role")
  private String role = null;  

    public String getLoginname() {
        return loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Login {\n");
    sb.append("    loginname: ").append(toIndentedString(loginname)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
