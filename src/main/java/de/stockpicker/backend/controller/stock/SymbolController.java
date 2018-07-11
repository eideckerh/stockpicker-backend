package de.stockpicker.backend.controller.stock;

import de.stockpicker.backend.entity.Symbol;
import de.stockpicker.backend.repository.SymbolRepository;
import de.stockpicker.backend.repository.TradeRepository;
import de.stockpicker.backend.response.SymbolStatistic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.List;

@RestController
@RequestMapping("/stock/symbol")
@Api(value = "symbol", description = "Ermittlung der möglichen Indizies")
public class SymbolController {

    @Autowired
    SymbolRepository symbolRepository;

    @Autowired
    TradeRepository tradeRepository;

    @GetMapping
    @Produces(value = "application/json")
    @ApiOperation(value = "Suche nach Indizies", notes = "Ermöglicht die Suche nach Indizies über den Schlüssel oder Bezeichner des Index")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erfolgreiche Suche"),
            @ApiResponse(code = 401, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 403, message = "Authentifizierung nicht erfolgreich")
    })
    public List<Symbol> query(@RequestParam("name") String name) {
        return symbolRepository.findByKeyContainingOrNameContainingOrderByName(name, name);
    }

    @GetMapping(path = "trending")
    @Produces(value = "application/json")
    @ApiOperation(value = "Suche der meist gehandelten Indizies", notes = "Liefert die meist gehandelten Indizies zurück")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Erfolgreiche Abfrage"),
            @ApiResponse(code = 401, message = "Authentifizierung nicht erfolgreich"),
            @ApiResponse(code = 403, message = "Authentifizierung nicht erfolgreich")
    })
    public List<SymbolStatistic> getMostTradedSymbols() {
        return tradeRepository.calculateMostTradedSymbols();
    }
}
