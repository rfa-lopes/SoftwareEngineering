package fct.unl.pt.instagramplus.Controllers;

import fct.unl.pt.instagramplus.Services.Result;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.*;

public class Response {

    public static <T> ResponseEntity<T> resultOrErrorCode(Result<T> result) {
        if (result.isOK())
            return ResponseEntity.ok(result.value());
        else
            return statusCode(result);
    }

    static private ResponseEntity statusCode(Result<?> result) {
        switch (result.error()) {
            case BAD_REQUEST:
                return ResponseEntity.badRequest().build();
            case NOT_FOUND:
                return ResponseEntity.notFound().build();
            case OK:
                return result.value() == null ? ResponseEntity.noContent().build() : ResponseEntity.ok().build();
            case NOT_IMPLEMENTED:
                return ResponseEntity.status(NOT_IMPLEMENTED).build();
            case CONFLICT:
                return ResponseEntity.status(CONFLICT).build();
            default:
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
