package de.stockpicker.backend.repository;

import de.stockpicker.backend.entity.TimeSerie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSerieRepository extends CrudRepository<TimeSerie, Long> {

}

