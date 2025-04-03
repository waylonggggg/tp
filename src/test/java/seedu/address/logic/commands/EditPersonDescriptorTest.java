package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
// Import necessary constants
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
// Remove CCA/Role related imports if they were previously used here
// import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_PRESIDENT;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_CCA_DANCE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditStudentCommand.EditPersonDescriptor;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different cca information -> Should no longer be tested as field removed
        // If EditPersonDescriptorBuilder still supports withCcaInformation, ensure it's ignored
        // If DESC_AMY contained CCAs originally, ensure the comparison ignores it
        // The equals method in EditPersonDescriptor itself should have been updated
        // to exclude CCA comparison. Assuming it has, the previous tests cover enough.
    }

    @Test
    public void toStringMethod() {
        // Test with an empty descriptor
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        String expected = EditPersonDescriptor.class.getCanonicalName() + "{name="
                + editPersonDescriptor.getName().orElse(null) + ", phone="
                + editPersonDescriptor.getPhone().orElse(null) + ", email="
                + editPersonDescriptor.getEmail().orElse(null) + ", address="
                + editPersonDescriptor.getAddress().orElse(null) + "}"; // CCA part is removed
        assertEquals(expected, editPersonDescriptor.toString());

        // Test with a descriptor having some values (using DESC_AMY as example)
        expected = EditPersonDescriptor.class.getCanonicalName() + "{name="
                + DESC_AMY.getName().orElse(null) + ", phone="
                + DESC_AMY.getPhone().orElse(null) + ", email="
                + DESC_AMY.getEmail().orElse(null) + ", address="
                + DESC_AMY.getAddress().orElse(null) + "}"; // CCA part is removed
        assertEquals(expected, DESC_AMY.toString());
    }

    // Add tests for isAnyFieldEdited if desired
    @Test
    public void isAnyFieldEdited() {
        // No fields set -> false
        assertFalse(new EditPersonDescriptor().isAnyFieldEdited());

        // Only one field set -> true
        assertTrue(new EditPersonDescriptorBuilder().withName("A").build().isAnyFieldEdited());
        // Use a valid phone number (e.g., 3 digits or more)
        assertTrue(new EditPersonDescriptorBuilder().withPhone("123").build().isAnyFieldEdited()); // Fixed
        assertTrue(new EditPersonDescriptorBuilder().withEmail("a@bc").build().isAnyFieldEdited());
        assertTrue(new EditPersonDescriptorBuilder().withAddress("Addr").build().isAnyFieldEdited());

        // Multiple fields set -> true
        // Use a valid phone number here too
        assertTrue(new EditPersonDescriptorBuilder()
                .withName("A").withPhone("123").build().isAnyFieldEdited()); // Fixed
    }
}
