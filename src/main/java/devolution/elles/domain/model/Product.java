package devolution.elles.domain.model;

import java.util.List;

public record Product(
        String id,
        String name,
        List<String> warranties,
        List<String> eligibleVehicles
) {
}
