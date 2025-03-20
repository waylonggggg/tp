package seedu.address.ui;

import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.cca.Cca;

/**
 * An UI component that displays information of a {@code Cca}.
 *
 * <p>
 * This class uses an FXML file {@code CcaListCard.fxml} to define its layout.
 * The corresponding FXML elements (e.g., {@link #ccaCardPane}, {@link #ccaName})
 * are injected at runtime.
 * </p>
 */
public class CcaCard extends UiPart<Region> {

    /** The file path to the FXML layout for this UI component. */
    private static final String FXML = "CcaListCard.fxml";

    /**
     * The CCA to be displayed. Declared public to allow other components
     * (like tests) to access the underlying data if needed.
     */
    public final Cca cca;

    /** The main container for this CCA card. */
    @FXML
    private HBox ccaCardPane;

    /** Label showing the index of the CCA (e.g., "1. "). */
    @FXML
    private Label id;

    /** Label showing the name of the CCA. */
    @FXML
    private Label ccaName;

    /** Label showing the roles (comma-separated) for the CCA. */
    @FXML
    private Label roles;

    /** Label showing the total number of sessions for the CCA. */
    @FXML
    private Label sessionCount;

    /**
     * Creates a {@code CcaCard} with the given {@code Cca} and index to display.
     *
     * @param cca The {@code Cca} object whose details are to be displayed.
     * @param displayedIndex The index of this CCA in the list (for labeling).
     */
    public CcaCard(Cca cca, int displayedIndex) {
        super(FXML);
        this.cca = cca;

        // Apply styling for the outer box
        ccaCardPane.getStyleClass().add("cca-box");

        // 1) Numbering: e.g. "1. "
        id.setText(displayedIndex + ". ");

        // 2) CCA name
        ccaName.setText(cca.getCcaName().fullCcaName);

        // 3) Show roles (single label, comma-separated)
        if (cca.getRoles().isEmpty()) {
            roles.setText("Roles: None");
        } else {
            String rolesString = cca.getRoles().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            roles.setText("Roles: " + rolesString);
        }

        // 4) Show total sessions
        sessionCount.setText("Total number of sessions: "
                + cca.getTotalSessions().getSessionCount());
    }
}
