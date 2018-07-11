package de.stockpicker.backend.controller;

import de.stockpicker.backend.entity.User;
import de.stockpicker.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.security.Principal;

@RestController
@Api(value = "login", description = "Benutzerlogin")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/login")
    @Produces("application/json")
    @ApiOperation(value = "Endpunkt zur Prüfung von Benutzer / Passwort Kombination")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Der Benutzer wurde erfolgreich registriert"),
            @ApiResponse(code = 401, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 403, message = "Authentifizierung nicht erfolgreich")
    })
    public ResponseEntity<User> register(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return ResponseEntity.ok(user);
    }
}
