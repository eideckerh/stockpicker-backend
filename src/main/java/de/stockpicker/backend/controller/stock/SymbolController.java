package de.stockpicker.backend.controller.stock;

import de.stockpicker.backend.entity.Symbol;
import de.stockpicker.backend.repository.SymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock/symbol")
public class SymbolController {

    @Autowired
    SymbolRepository symbolRepository;

    @GetMapping
    public List<Symbol> query(@RequestParam("name") String name) {
        return symbolRepository.findByKeyContainingOrNameContainingOrderByName(name, name);
    }
}
