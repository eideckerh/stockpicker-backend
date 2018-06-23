package de.stockpicker.backend.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Symbol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "symbol_name")
    private String name;

    @Column(name = "symbol_key")
    private String key;

    @ManyToOne(fetch = FetchType.LAZY)
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
