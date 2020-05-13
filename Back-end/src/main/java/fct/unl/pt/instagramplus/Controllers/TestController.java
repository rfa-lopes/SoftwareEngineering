package fct.unl.pt.instagramplus.Controllers;

import fct.unl.pt.instagramplus.Utils.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = TestController.BASE_URL)
public class TestController {

    static final String BASE_URL = "tests";

    @GetMapping(value = "test1", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> test1(){
        Logger.info("REQUEST: TESTS");
        return ResponseEntity.ok("TESTE CONTROLLERS");
    }

}
