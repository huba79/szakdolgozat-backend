package crengine.api;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen")
public class ApiException extends Exception {
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
