package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCcas.BASKETBALL;
import static seedu.address.testutil.TypicalCcas.MEMBER;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.cca.Attendance;
import seedu.address.model.cca.Cca;
import seedu.address.model.cca.CcaInformation;
import seedu.address.model.cca.CcaName;
import seedu.address.model.cca.SessionCount;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;

public class RecordAttendanceCommandTest {

    @Test
    public void execute_validPersonAttendance_success() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();

        Cca validCca = BASKETBALL;
        Set<CcaInformation> ccaInformation = new HashSet<>();
        ccaInformation.add(new CcaInformation(validCca, MEMBER, validCca.createNewAttendance()));
        Person validPerson = new PersonBuilder().withName("Alice").withCcaInformations(
                ccaInformation).build();
        modelStub.addCca(validCca);
        modelStub.addPerson(validPerson);

        Index studentIndex = Index.fromOneBased(1);
        CcaName ccaName = new CcaName("Basketball");
        int amount = 1;

        RecordAttendanceCommand command = new RecordAttendanceCommand(studentIndex, ccaName, amount);
        CommandResult commandResult = command.execute(modelStub);

        assertEquals(String.format(RecordAttendanceCommand.MESSAGE_SUCCESS, validPerson.getName() + " in " + ccaName),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidStudentIndex_throwsCommandException() {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();

        Cca validCca = new Cca(new CcaName("Basketball"));
        Set<CcaInformation> ccaInformation = new HashSet<>();
        ccaInformation.add(new CcaInformation(validCca, MEMBER, validCca.createNewAttendance()));
        Person validPerson = new PersonBuilder().withName("Alice").withCcaInformations(
                ccaInformation).build();
        modelStub.addCca(validCca);
        modelStub.addPerson(validPerson);

        Index outOfBoundIndex = Index.fromOneBased(2);
        CcaName ccaName = new CcaName("Basketball");
        int amount = 1;

        RecordAttendanceCommand command = new RecordAttendanceCommand(outOfBoundIndex, ccaName, amount);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, (
                ) -> command.execute(modelStub));
    }

    @Test
    public void execute_ccaNotFound_throwsCommandException() {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().withName("Alice").build();
        modelStub.addPerson(validPerson);
        Cca validCca = new Cca(new CcaName("Basketball"));
        modelStub.addCca(validCca);

        Index studentIndex = Index.fromOneBased(1);
        CcaName ccaName = new CcaName("Swimming");
        int amount = 1;

        RecordAttendanceCommand command = new RecordAttendanceCommand(studentIndex, ccaName, amount);
        assertThrows(CommandException.class, RecordAttendanceCommand.MESSAGE_CCA_NOT_FOUND, (
                ) -> command.execute(modelStub));
    }

    @Test
    public void execute_invalidAttendanceAmount_throwsCommandException() {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();

        Cca validCca = new Cca(new CcaName("Basketball"));
        Set<CcaInformation> ccaInformation = new HashSet<>();
        ccaInformation.add(new CcaInformation(validCca, MEMBER, validCca.createNewAttendance()));
        Person validPerson = new PersonBuilder().withName("Alice").withCcaInformations(
                ccaInformation).build();
        modelStub.addCca(validCca);
        modelStub.addPerson(validPerson);

        Index studentIndex = Index.fromOneBased(1);
        CcaName ccaName = new CcaName("Basketball");
        int invalidAmount = -1;

        RecordAttendanceCommand command = new RecordAttendanceCommand(studentIndex, ccaName, invalidAmount);
        assertThrows(CommandException.class, SessionCount.MESSAGE_CONSTRAINTS, (
                ) -> command.execute(modelStub));
    }

    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        final ArrayList<Cca> ccasAdded = new ArrayList<>();

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public void addCca(Cca cca) {
            requireNonNull(cca);
            ccasAdded.add(cca);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public boolean hasCca(Cca cca) {
            requireNonNull(cca);
            return ccasAdded.stream().anyMatch(cca::isSameCca);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void recordAttendance(Cca cca, Person person, int amount) {
            requireNonNull(cca);
            requireNonNull(person);
            requireAllNonNull(person, cca, amount);
            Set<CcaInformation> ccaInformations = person.getCcaInformation();
            CcaInformation ccaInformation = ccaInformations.stream()
                    .filter(c -> c.getCca().equals(cca))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Person does not have this CCA"));
            Attendance attendance = ccaInformation.getAttendance();
            Attendance newAttendance = new Attendance(new SessionCount(
                    attendance.getSessionsAttended().getSessionCount() + amount), attendance.getTotalSessions());
            CcaInformation newCcaInformation = new CcaInformation(cca, ccaInformation.getRole(), newAttendance);
            Set<CcaInformation> newCcaInformations = new HashSet<>(ccaInformations);
            newCcaInformations.remove(ccaInformation);
            newCcaInformations.add(newCcaInformation);
            Person newPerson = new Person(person.getName(), person.getPhone(), person.getEmail(),
                    person.getAddress(), newCcaInformations);
            personsAdded.remove(person);
            personsAdded.add(newPerson);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableList(personsAdded);
        }
    }
}
