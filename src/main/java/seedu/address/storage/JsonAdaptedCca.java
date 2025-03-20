package seedu.address.storage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaName;
import seedu.address.model.cca.SessionCount;
import seedu.address.model.role.Role;

/**
 * Jackson-friendly version of {@link Cca}.
 */
public class JsonAdaptedCca {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Cca's %s field is missing!";

    private final String ccaName;
    private final List<JsonAdaptedRole> roles;
    private final int totalSessions;

    /**
     * Constructs a {@code JsonAdaptedCca} with the given details.
     *
     * @param ccaName The name of the CCA.
     * @param roles The list of roles associated with the CCA.
     * @param totalSessions The total number of sessions for the CCA.
     */
    @JsonCreator
    public JsonAdaptedCca(@JsonProperty("ccaName") String ccaName,
                          @JsonProperty("roles") List<JsonAdaptedRole> roles,
                          @JsonProperty("totalSessions") int totalSessions) {
        this.ccaName = ccaName;
        this.roles = roles;
        this.totalSessions = totalSessions;
    }

    /**
     * Converts a given {@code Cca} into this class for Jackson use.
     *
     * @param source The CCA model object to be converted.
     */
    public JsonAdaptedCca(Cca source) {
        this.ccaName = source.getCcaName().fullCcaName;
        this.roles = source.getRoles().stream()
                .map(JsonAdaptedRole::new)
                .collect(Collectors.toList());
        this.totalSessions = source.getTotalSessions().getSessionCount();
    }

    /**
     * Converts this Jackson-friendly adapted cca object into the model's {@code Cca} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted cca.
     */
    public Cca toModelType() throws IllegalValueException {
        if (ccaName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, CcaName.class.getSimpleName()));
        }
        if (!CcaName.isValidCcaName(ccaName)) {
            throw new IllegalValueException(CcaName.MESSAGE_CONSTRAINTS);
        }
        final CcaName modelCcaName = new CcaName(ccaName);

        final Set<Role> modelRoles = new HashSet<>();
        if (roles != null) {
            for (JsonAdaptedRole role : roles) {
                modelRoles.add(role.toModelType());
            }
        }

        final SessionCount modelTotalSessions = new SessionCount(totalSessions);

        return new Cca(modelCcaName, modelRoles, modelTotalSessions);
    }

}
