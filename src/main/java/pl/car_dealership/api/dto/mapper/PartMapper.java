package pl.car_dealership.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.car_dealership.api.dto.PartDTO;
import pl.car_dealership.domain.Part;


@Mapper(componentModel = "spring")
public interface PartMapper {

    PartDTO map(Part part);
}
