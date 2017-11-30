package Grupo6.LanguageService.controllers;

import Grupo6.LanguageService.models.Language;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.Consumes;

@RestController
public class LanguageController {
    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Language> compileCode(@RequestBody Language code){
        code.execute();

        if(code.getError().size() == 0){
            code.setStatusCode(1);
        }
        else{
            code.setStatusCode(0);
        }
        return new ResponseEntity<Language>(code, HttpStatus.OK);
    }

    @RequestMapping(value = "/testCase", method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Language> doTestCase(@RequestBody Language code){
        code.execute();

        return new ResponseEntity<Language>(code, HttpStatus.OK);
    }
}