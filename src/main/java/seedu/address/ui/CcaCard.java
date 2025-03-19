package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.cca.Cca;

/**
 * An UI component that displays information of a {@code Cca}.
 */
public class CcaCard extends UiPart<Region> {

    private static final String FXML = "CcaListCard.fxml";

    public final Cca cca;

    @FXML
    private HBox ccaCardPane;
    @FXML
    private Label ccaName;
    @FXML
    private Label id;

    /**
     * Creates a {@code CcaCard} with the given {@code Cca} and index to display.
     */
    public CcaCard(Cca cca, int displayedIndex) {
        super(FXML);
        this.cca = cca;
        id.setText(displayedIndex + ". ");
        ccaName.setText(cca.getCcaName().fullCcaName);
    }
}
