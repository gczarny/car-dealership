package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.car_dealership.business.dao.PartDAO;
import pl.car_dealership.domain.Part;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PartCatalogService {

    private final PartDAO partDAO;


    @Transactional
    public Part findPart(String partSerialNumber) {
        Optional<Part> part = partDAO.findBySerialNumber(partSerialNumber);
        if (part.isEmpty()) {
            throw new RuntimeException("Could not find part by part serial number: [%s]".formatted(partSerialNumber));
        }
        return part.get();
    }

}
