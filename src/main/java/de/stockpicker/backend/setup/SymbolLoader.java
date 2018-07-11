package de.stockpicker.backend.setup;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import de.stockpicker.backend.entity.Symbol;
import de.stockpicker.backend.entity.SymbolType;
import de.stockpicker.backend.repository.SymbolRepository;
import de.stockpicker.backend.repository.SymbolTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Komponente zum Setup der Datenbank
 */
@Component
public class SymbolLoader {

    @Autowired
    SymbolTypeRepository symbolTypeRepository;

    @Autowired
    SymbolRepository symbolRepository;

    @PostConstruct
    private void setup() throws IOException {
        //Konfiguration des CSV-Lesers
        CsvMapper mapper = new CsvMapper();
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withSkipFirstDataRow(true);
        mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);

        //lese die verfügbaren Typen der Indizies ein
        File file = new ClassPathResource("setup/symbol_types.csv").getFile();
        MappingIterator<String[]> readValues =
                mapper.reader(String[].class).with(bootstrapSchema).readValues(file);
        List<String[]> rowList = readValues.readAll();
        for (String[] csvRow : rowList) {
            Optional<SymbolType> typeOptional = symbolTypeRepository.findDistinctByKeyEquals(csvRow[1]);
            if (!typeOptional.isPresent()) {
                SymbolType symbolType = new SymbolType(csvRow[0], csvRow[1]);
                symbolTypeRepository.save(symbolType);
            }
        }

        //lese die verfügbaren Indizies von digitalen Währungen ein
        Optional<SymbolType> symbolTypeOptional = symbolTypeRepository.findDistinctByKeyEquals("DIGITAL_CURRENCY");
        if (symbolTypeOptional.isPresent()) {
            SymbolType digitalCurrencyType = symbolTypeOptional.get();

            file = new ClassPathResource("setup/digital_currency_list.csv").getFile();
            readValues = mapper.reader(String[].class).with(bootstrapSchema).readValues(file);
            List<String[]> symbolList = readValues.readAll();
            for (String[] csvRow : symbolList) {
                Optional<Symbol> optionalSymbol = symbolRepository.findDistinctByKeyEquals(csvRow[0]);
                if (!optionalSymbol.isPresent()) {
                    symbolRepository.save(new Symbol(csvRow[1], csvRow[0], digitalCurrencyType));
                }
            }
        }


        //lese die verfügbaren Indizies von Aktien ein
        symbolTypeOptional = symbolTypeRepository.findDistinctByKeyEquals("STOCK");
        if (symbolTypeOptional.isPresent()) {

            SymbolType stockType = symbolTypeOptional.get();

            file = new ClassPathResource("setup/stock_list.csv").getFile();
            readValues = mapper.reader(String[].class).with(bootstrapSchema).readValues(file);
            List<String[]> symbolList = readValues.readAll();
            System.out.println(symbolList);
            for (String[] csvRow : symbolList) {
                Optional<Symbol> optionalSymbol = symbolRepository.findDistinctByKeyEquals(csvRow[0]);
                if (!optionalSymbol.isPresent()) {
                    symbolRepository.save(new Symbol(csvRow[1], csvRow[0], stockType));
                }
            }
        }
    }
}
