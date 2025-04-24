package devolution.elles.domain.model;

public record Insured(
        String id,
        String firstName,
        String lastName,
        String identityNumber,
        String city,
        String address
) {
}
