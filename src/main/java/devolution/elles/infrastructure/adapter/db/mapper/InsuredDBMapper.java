package devolution.elles.infrastructure.adapter.db.mapper;

import devolution.elles.domain.model.Insured;
import devolution.elles.infrastructure.adapter.db.entity.InsuredEntity;

public class InsuredDBMapper {

    public static Insured toDomain(InsuredEntity entity) {
        return new Insured(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getIdentityNumber(),
                entity.getCity(),
                entity.getAddress()
        );
    }


    public static InsuredEntity toEntity(Insured insured) {
        return new InsuredEntity(
                insured.id(),
                insured.firstName(),
                insured.lastName(),
                insured.identityNumber(),
                insured.city(),
                insured.address()
        );
    }
}
