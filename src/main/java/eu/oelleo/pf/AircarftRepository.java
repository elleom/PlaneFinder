package eu.oelleo.pf;

import eu.oelleo.pf.domain.Aircraft;
import org.springframework.data.repository.CrudRepository;

public interface AircarftRepository extends CrudRepository<Aircraft, Long> {
}
