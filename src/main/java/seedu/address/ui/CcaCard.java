package seedu.address.ui;

import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.cca.Cca;

public class CcaCard extends UiPart<Region> {

    private static final String FXML = "CcaListCard.fxml";

    public final Cca cca;

    @FXML
    private HBox ccaCardPane;
    @FXML
    private Label id;
    @FXML
    private Label ccaName;
    @FXML
    private Label roles;
    @FXML
    private Label sessionCount;

    /**
     * Creates a {@code CcaCard} with the given {@code Cca} and index to display.
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
