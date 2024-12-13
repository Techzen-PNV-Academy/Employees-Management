package com.student.pnv.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaculatorController {
    @GetMapping("/caculator")
    public ResponseEntity<String> caculator(
            @RequestParam(value = "fNum", defaultValue = "") String fNumString,
            @RequestParam(value = "sNum", defaultValue = "") String sNumString,
            @RequestParam(value = "operator", defaultValue = "") String operator
    ) {
        if(fNumString.isEmpty()) {
            return ResponseEntity.status(400).body("Fisrt number cannot be empty");
        }else if(sNumString.isEmpty()) {
            return ResponseEntity.status(400).body("Second number cannot be empty");
        }else if(operator.isEmpty()) {
            return ResponseEntity.status(400).body("Operator math cannot be empty");
        }

        double result = 0;
        double fNum = Double.parseDouble(fNumString);
        double sNum = Double.parseDouble(sNumString);

        switch (operator) {
            case "+": result = fNum + sNum;
            break;
            case "-": result = fNum - sNum;
            break;
            case "*": result = fNum * sNum;
            break;
            case "/":
                if (sNum == 0){
                    return ResponseEntity.status(400).body("Should not degree to zero");
                }result = fNum / sNum;
            break;
        }
        return ResponseEntity.ok(String.valueOf(result));
    };
}
