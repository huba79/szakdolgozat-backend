package messages;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Guests, clients, admins and managers(owners)
 */
//@Schema(description = "Guests, clients, admins and managers(owners)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-28T16:04:50.838Z[GMT]")

public class RegistrationMessage   {

    @JsonProperty("displayName")
    String displayName;

    @JsonProperty("nickName")
    String nickName;

    @JsonProperty("emailAddress")
    String emailAddress;

    @JsonProperty("password")
    String password;

    @JsonProperty("groupId")
    String groupId = "USER";
    
    
    @JsonProperty("companyId")
    Long companyId;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }    
    
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class Registration {\n");
      sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
      sb.append("    nickName: ").append(toIndentedString(nickName)).append("\n");
      sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");    
      sb.append("    password: ").append(toIndentedString(password)).append("\n");
      sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
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
