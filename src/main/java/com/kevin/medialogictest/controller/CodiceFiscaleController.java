package com.kevin.medialogictest.controller;

import com.kevin.medialogictest.ResponseWrapper;
import com.kevin.medialogictest.UserDto;
import com.kevin.medialogictest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/codiceFiscale")
public class CodiceFiscaleController {

    private final UserService userService;

    CodiceFiscaleController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/getDataDiNascitaAndEta")
    public ResponseEntity<ResponseWrapper<UserDto>> getDataDiNascitaAndEta(@RequestParam("codiceFiscale") String codiceFiscale) {

        //inanzittutto trimmo il codice fiscale cosi da non avere spazi vuoti
        codiceFiscale = codiceFiscale.trim();

        //controllo se il codice fiscale sia innanzitutto valido
        if (!codiceFiscale.matches("^[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}$")) {
            return ResponseEntity.ok(ResponseWrapper.error("Formato Codice Fiscale non valido"));
        }

        UserDto userDto = userService.getDataDiNascitaAndEta(codiceFiscale);
        if (userDto == null) {
            return ResponseEntity.ok(ResponseWrapper.error("Errore durante il recupero della data di nascita e età"));
        }

        return ResponseEntity.ok(ResponseWrapper.ok(userDto));
    }
}
