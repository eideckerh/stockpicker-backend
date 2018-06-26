package de.stockpicker.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Symbol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "symbol_name")
    private String name;

    @Column(name = "symbol_key")
    private String key;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false)
    private SymbolType type;

    public Symbol() {
    }

    public Symbol(String name, String key, SymbolType type) {
        this.name = name;
        this.key = key;
        this.type = type;
    }
}
