---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# AB-3 Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete_s 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete_s 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete_s 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteStudentCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteStudentCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteStudentCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a student).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `CreateStudentCommandParser`, `DeleteStudentCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects and `Cca` objects (which are contained in a `UniquePersonList` object and a `UniqueCcaList` respectively).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores the current `Cca` seperately in as an unmodifiable `ObservableList<Cca>` just like `Person` objects, however, there is no 'selected' objects.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete_s 5` command to delete the 5th student in the address book. The `delete_s` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete_s 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete_s`, just save the student being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user**: Hall attendance managers

**Target user profile**:

* Resolve the distress of the Hall attendance manager caused by the current complex and unorganized CCA attendance system.
* Provide a simple and easy-to-use software that effectively tracks all CCA attendances for hall students.
* Accommodate the manager’s “lazy” nature by streamlining workflows and reducing complexity.
* Prioritize typing over mouse usage to align with the manager’s preferences.

**Value proposition**:  It provides a centralized tracking system for the CCA attendance for hall students in different CCAs, which is required for the point calculation. Students are grouped by CCAs, and each student has an attendance/point tracker. CLI commands allow point allocation to be done quickly.

### User stories

- Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`.
- HAM below refers to Hall Attendance Managers.

| Index | Priority | As a …                             | I can …                                                      | So that I can…                                                                           |
|-----: | :------: | :---------------------------------- | :----------------------------------------------------------- |:-----------------------------------------------------------------------------------------|
| 1     | `* * *`  | HAM                                 | create new students in the list                             | store their information.                                                                 |
| 2     | `* * *`  | HAM                                 | delete existing students from the list                       | remove ex-students and other redundant data.                                             |
| 3     | `* *`    | HAM                                 | view student profiles by searching by name                   | provide personalized service.                                                            |
| 4     | `* *`    | HAM                                 | view student profiles sorted by their last update            | access the most recent data easily.                                                      |
| 5     | `* * *`  | HAM                                 | create a CCA role                                            | label students according to specific responsibilities in that CCA.                       |
| 6     | `* * *`  | HAM                                 | delete an existing CCA role                                  | revert any changes or mistakes made when labeling a student.                             |
| 7     | `* * *`  | HAM                                 | add roles to a student in a CCA                              | categorize students by their responsibilities (e.g. captain, exco, non-official member). |
| 8     | `* * *`  | HAM                                 | delete a role from a student in a CCA                        | remove any outdated labels (e.g. if the student resigns from a role).                    |
| 9     | `* *`    | HAM                                 | search for students by their roles                           | easily find students who share the same responsibilities.                                |
| 10    | `* *`    | HAM                                 | edit student profiles                                        | keep their information up to date and recover from mistakes (e.g. a change of address).  |
| 11    | `*`      | HAM                                 | add additional remarks to students                           | record special circumstances (e.g. poor behavior).                                       |
| 12    | `*`      | new HAM                             | use the “help” command                                       | learn how to use the application.                                                        |
| 13    | `*`      | experienced HAM                     | set custom keybinds or shortcuts                             | use commands more quickly.                                                               |
| 14    | `*`      | HAM                                 | undo commands                                                | easily recover from mistakes or accidents.                                               |
| 15    | `*`      | HAM                                 | archive student profiles                                     | remove inactive students while still retaining their data for future reference.          |
| 16    | `* * *`  | potential HAM exploring the app      | see the application populated with sample student information | understand how it looks in active use.                                                   |
| 17    | `* * *`  | new HAM ready to start using the app | purge all current data                                       | remove any sample student information used for testing.                                  |
| 18    | `*`      | HAM taking over from another HAM     | import student profiles                                      | transfer existing student information into my system.                                    |
| 19    | `*`      | HAM taking over from another HAM     | export student profiles                                      | pass the current student information on to another HAM.                                  |
| 20    | `* * *`  | HAM                                 | record a student’s attendance in a CCA by 1                  | accurately track attendance.                                                             |
| 21    | `* *`    | HAM                                 | set a maximum attendance for CCA                             | have a better idea of the student’s attendance rate.                                     |
| 22    | `* *`    | HAM                                 | record attendance in bulk                                    | dont have to update attendance student by student.                                       |
| 23    | `*`      | HAM                                 | view the access history of student profiles                  | keep track of my past interactions.                                                      |
| 24    | `* * *`  | HAM                                 | create a new CCA                                             | assign it to students.                                                                   |
| 25    | `* * *`  | HAM                                 | delete a CCA                                                | remove any that are no longer active or needed.                                          |
| 26    | `* * *`  | HAM                                 | add a CCA to a student                                       | keep their records accurate.                                                             |
| 27    | `* * *`  | HAM                                 | delete a CCA from a student                                  | update their status if they leave or switch CCAs.                                        |
| 28    | `* * *`  | HAM                                 | view the list of CCAs separately from the student list       | see all CCAs at once.                                                                    |
| 29    | `* *`    | HAM                                 | see the number of students in each CCA                       | better manage the distribution of students across CCAs.                                  |
| 30    | `* *`    | HAM                                 | see the list of roles in a CCA                               | know which roles can be assigned to students.                                            |
| 31    | `* *`    | HAM                                 | view students by their CCAs                                 | more easily track students in each CCA.                                                  |
| 32    | `* *`    | HAM                                 | view the calculated hall points each student has             | better understand their overall contributions.                                           |
| 33    | `*`      | HAM                                 | manually adjust hall points for a student                    | correct any inaccuracies when necessary.                                                 |
| 34    | `*`      | HAM                                 | highlight students with low attendance in red                | know which students to follow up with.                                                   |
| 35    | `*`      | HAM                                 | generate reports that give an overview of CCA participation and points | provide data to the hall administration for decision-making.                             |
| 36    | `*`      | HAM                                 | rank students by their hall points in these reports          | have a clear summary of top performers.                                                  |
| 37    | `*`      | HAM                                 | edit the multiplier used in point calculations (e.g. changing from 1.5× to 2× for certain roles) | adjust the scoring system as needed.                                                     |


### Use cases

(For all use cases below, the **System** is `CCAttendance`, the person is `Hall Attendance Manager`, and the **Actor** is the `user`, unless specified otherwise).

**UC1: List students**

**MSS**

1. User requests to list students.
2. System shows a list of all students.

    Use case ends.

**Extensions**
* 1a. The student list is empty.

  Use case ends.

**UC2: Add a student**

**MSS**

1.  User <u>lists the students (UC1)</u>.
2. User requests to add a student with the corresponding details.
3. System adds the student to the list.
4. System shows the student has been added.

    Use case ends.

**Extensions**

* 2a. The student already exists in the list.

    * 2a1. System shows an error message.

      Use case resumes at step 2.

**UC3: Delete a student**

**MSS**

1. User <u>lists the students (UC1)</u>.
2. User requests to delete a specific student.
3. System deletes the student from the list.
4. System shows the student has been deleted.

    Use case ends.

**Extensions**

* 2a. The student does not exist in the list.

    * 2a1. System shows an error message.

      Use case resumes at step 2.

**UC4: Edit a student**

**MSS**

1. User <u>lists the students (UC1)</u>.
2. User requests to edit a student's information from the list.
3. System edits the student from the list.
4. System shows the student has been edited.

    Use case ends.

**Extensions**

* 2a. The student does not exist in the list.

    * 2a1. System shows an error message.

      Use case resumes at step 2.

* 2b. The parameters to edit does not exist.

  * 2b1. System shows an error message. 
  
    Use case resumes at step 2.

* 2c. The parameters to edit is invalid.

    * 2c1. System shows an error message.

      Use case resumes at step 2.

* 2d. The student already has the same information.

  * 2d1. System shows an error message. 
  
    Use case resumes at step 2.

**UC5: Find a student**

**MSS**

1. User requests to find students with specific keywords in a name.
2. System searches for students matching the keywords.
3. System shows a list of searched students.

    Use case ends.

**Extensions**

* 1a. No students match the keywords.
  Use case ends.

* 1a. The student list is empty.
    Use case ends.

* 1b. The keyword is an invalid format or missing keywords.

    * 1b1. System shows an error message.
  
        Use case resumes at step 1.

**UC6: Add a CCA**

**MSS**

1.  User requests to add a CCA with the corresponding details.
2.  System adds the CCA to the list.
3.  System shows the CCA has been added.

    Use case ends.

**Extensions**

* 1a. The CCA already exists in the list.

    * 1a1. System shows an error message.

      Use case resumes at step 1.

**UC7: Delete a CCA**

**MSS**

1. System shows list of CCAs. 
2. User requests to delete a specific CCA.
3. System deletes the CCA from the list.
4. System shows the CCA has been deleted.

    Use case ends.

**Extensions**

* 1a. The CCA list is empty.

  Use case ends.

* 2a. The CCA does not exist in the list.

    * 2a1. System shows an error message.

      Use case resumes at step 2.

**UC8: Edit a cca**

**MSS**

1. System shows list of CCAs.
2. User requests to edit a CCA's information from the list.
3. System edits the CCA from the list.
4. System shows the CCA has been edited.

   Use case ends.

**Extensions**

* 2a. The student does not exist in the list.

    * 2a1. System shows an error message.

      Use case resumes at step 2.

* 2b. The parameters to edit does not exist.

    * 2b1. System shows an error message.

      Use case resumes at step 2.

* 2c. The parameters to edit is invalid.

    * 2c1. System shows an error message.

      Use case resumes at step 2.

**UC9: Add a role to a student in a CCA**

**MSS**

1.  User <u>lists the students (UC1)</u>.
2. System shows list of CCAs.
3. User requests to add a role to a specific student in a specific CCA.
4. System adds the role to the student in the CCA.
5. System shows the role has been added.

    Use case ends.

**Extensions**

* 2a. The CCA list is empty.

  Use case ends.

* 3a. The student does not exist in the list.

    * 3a1. System shows an error message.

      Use case resumes at step 3.

* 3b. The CCA does not exist in the list.

    * 3b1. System shows an error message.

      Use case resumes at step 3.

* 3c. The role already exists for the student in the CCA.

    * 3c1. System shows role already exists.

      Use case ends.

* 3d. The student is not in the CCA.

    * 3d1. System shows an error message.

      Use case resumes at step 3.

* 3e. The role does not exist.

    * 3e1. System shows an error message.

      Use case resumes at step 3.

**UC10: Delete a role from a student in a CCA**

**MSS**

1.  User <u>lists the students (UC1)</u>.
2.  User requests to delete a role from a specific student in a specific CCA.
3. System deletes the role from the student in the CCA.
4. System shows the role has been deleted.

    Use case ends.

**Extensions**

* 2a. The student does not exist in the list.

    * 2a1. System shows an error message.

      Use case resumes at step 2.

* 2b. The student does not have the role in the CCA.

    * 2b1. System shows an error message.

      Use case resumes at step 2.

* 2c. The role does not exist.

   * 2c1. System shows an error message.

      Use case resumes at step 2.

**UC11: Add CCA to a student**

**MSS**

1.  User <u>requests to list the students (UC1)</u>. 
2. System shows list of CCAs. 
3. User requests to add a CCA to a specific student. 
4. System adds the CCA to the student. 
5. System shows the CCA has been added.

    Use case ends.

**Extensions**

* 2a. The CCA list is empty.

   Use case ends.

* 3a. The student does not exist in the list.

    * 3a1. System shows an error message.

      Use case resumes at step 3.

* 3b. The CCA does not exist in the list.

    * 3b1. System shows an error message.

      Use case resumes at step 3.

* 3c. The student is already in the CCA.

    * 3c1. System shows student already in CCA.

      Use case ends.

**UC12: Delete a CCA from a student**

**MSS**

1.  User <u>lists the students (UC1)</u>. 
2. User requests to delete a CCA from a specific student. 
3. System deletes the CCA from the student. 
4. System shows the CCA has been deleted.

    Use case ends.

**Extensions**

* 2a. The student does not exist in the list.

   * 2a1. System shows an error message.

      Use case resumes at step 2.

* 2b. The CCA does not exist in the list.

   * 2b1. System shows an error message.

      Use case resumes at step 2.

* 2c. The student is not in the CCA.

   * 2c1. System shows an error message.

      Use case resumes at step 2.

**UC13: Clear all the student**

**MSS**

1. User requests to clear all the students.
2. System clears all the students.
3. System shows all the students have been cleared.

    Use case ends.

**UC14: Record a student's attendance**

**MSS**
1.  User <u>lists the students (UC1)</u>.
2. User requests to record a certain attendance amount for a specific student and a given CCA. 
3. System increments the student’s attendance in that CCA by the requested amount. 
4. System shows that the attendance has been updated successfully.

Use case ends.

**Extensions**
* 2a. The student does not exist in the list.
    * 2a1. System shows an error message.  
      Use case resumes at step 2.

* 2b. The specified CCA does not exist in the system.
    * 2b1. System shows an error message.  
      Use case resumes at step 2.

* 2c. The student is not in the specified CCA.
    * 2c1. System shows an error message.  
      Use case resumes at step 2.

* 2d. The attendance amount is missing or invalid.
    * 2d1. System shows an error message.  
      Use case resumes at step 2.

### Non-Functional Requirements

1. **Platform Compatibility**: The application should work on any _mainstream OS_ (Windows, Linux, macOS) as long as Java `17` or above is installed. *(Constraint-Platform-Independent, Constraint-Java-Version)*

2. **Performance**: The application should be able to hold up to **1000 persons** without noticeable sluggishness in performance for typical usage. *(Scalability requirement)*

3. **Typing Efficiency**: A user with above-average typing speed for regular English text (i.e., not code, not system admin commands) should be able to accomplish most tasks **faster using commands** than using the mouse. *(Constraint-Typing-Preferred, Recommendation-CLI-First)*

4. **Incremental Development**: The software should be developed in **small, incremental updates** throughout the project lifecycle instead of being built in one go. *(Constraint-Incremental)*

5. **Storage Format**: Data should be stored in a **human-editable text file** (e.g., JSON, CSV) rather than a database or proprietary format. *(Constraint-Human-Editable-File, Constraint-No-DBMS)*

6. **Portability**: The application should **not require an installer** and should be packaged as a **single JAR file**. *(Constraint-Portable, Constraint-Single-File)*

7. **No Remote Server Dependency**: The application should be **fully functional without requiring an internet connection** or an external server. *(Constraint-No-Remote-Server, Recommendation-Minimal-Network)*

8. **Screen Resolution Support**: The GUI should work well on **standard screen resolutions** (1920×1080 and higher) and be usable at **lower resolutions** (1280×720). *(Constraint-Screen-Resolution)*

9. **Security & Privacy**: User data should be stored **locally** and should **not be accessible to other users** during regular operations. *(Constraint-Single-User)*

10. **Extensibility & Maintainability**: The application should follow **Object-Oriented Programming (OOP) principles**, making it **easy to extend and modify**. *(Constraint-OO)*

*{More to be added as needed}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **CLI**: Command Line Interface, a text-based interface that allows users to interact with the system by typing commands
* **GUI**: Graphical User Interface, a visual interface that allows users to interact with the system using graphical elements such as CLI, buttons, etc.
* **Index**: A number that represents the position of student or CCA. It is shown in the UI on the left side of the student or CCA details
* **HAM**: Hall attendance manager, i.e. the one in charge of taking attendance of the students
* **Role**: The position the student has in the CCA, e.g. Captain, President

*{More to be added}*

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;  
testers are expected to do more \*exploratory\* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Listing a student

1. List all students
    1. Test case: `list`
       Expected: The list should contain all students. The status message should reflect the successful listing of all students.

### Clearing all students

1. List all students
    1. Prerequisite: List all students using the `list` command. Multiple students in the list.
    2. Test case: `list`
       Expected: The list should contain all students. The status message should reflect the successful listing of all students.

### Finding a student

1. Find a student by querying name
    1. Prerequisites: For this test, you will use the sample data provided when the app is launched the first time. Make sure the data file is not corrupted.
    2. Test case: `find Alex`
       Expected: The list should contain one student `Alex Yeoh` only.

### Creating a student

1. Creating a student
    1. Prerequisites: List all students using the `list` command. Multiple students in the list.
    2. Test case: `create_s n/John Doe p/98765432 e/e0000000@u.nus.edu a/Raffles Hall 22/B/2`
       Expected: A new student is added to the list. The student details are shown in the list.
    3. Test case: `create_s n/John Doe p/98765432`
       Expected: Error message is shown as insufficient parameters are provided. The student is not added to the list.
    4. Test case: `create_s n/John Doe p/98765432 e/e0000000@u.nus.edu a/Raffles Hall 22/B/2`
       Expected: Error message is shown as the student already exists in the list. The student is not added to the list.
    5. Other incorrect create student commands to try: `create_s n/helloworld* p/helloworld* e/helloworld* a/helloworld*` (incorrect inputs, missing inputs, or incorrect prefixes used)
       Expected: Error message is shown as parameters with invalid formats were provided. The student is not added to the list.

### Deleting a student

1. Deleting a student while all students are being shown
    1. Prerequisites: List all students using the `list` command. Multiple students in the list.
    2. Test case: `delete_s 1`
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
    3. Test case: `delete_s 0`
       Expected: No student is deleted. Error details shown in the status message.
    4. Other incorrect delete commands to try: `delete_s`, `delete_s x`, `...` (where x is larger than the list size)
       Expected: Similar to previous.

### Creating a CCA

1. Creating a CCA with a CCA name.
    1. Prerequisites: List all CCAs using the `list` command. Multiple CCAs in the list.
    2. Test case: `create_c n/Basketball`
       Expected: A new CCA is added to the list. The CCA details are shown in the list.
    3. Test case: `create_c`
       Expected: No CCA is added. Error details shown in the status message as the CCA name is not provided.
    4. Test case: `create_c n/Basketball`
       Expected: No CCA is added. Error details shown in the status message as the CCA already exists.
    5. Other test cases to try: `create_c n/helloworld*` (incorrect inputs, missing inputs, or incorrect prefixes used)
       Expected: No CCA is added. Error message is shown as parameters with invalid formats were provided.

### Deleting a CCA

1. Deleting a CCA while all CCAs are being shown
    1. Prerequisites: List all CCAs using the `list` command. One CCA in the list.
    2. Test case: `delete_c 1`
       Expected: The first CCA is deleted from the list. Details of the deleted CCA shown in the status message.
    3. Test case: `delete_c 1`
       Expected: There are no more CCAs in the list. Error details are shown in the status message.
    4. Test case: `delete_c 0`
       Expected: No CCA is deleted. Error details shown in the status message.
    5. Other incorrect delete commands to try: `delete_c`, `delete_c x`, `...` (where x is larger than the list size)
       Expected: Similar to previous.

### Editing a student

1. Editing an existing student's name, phone, email and address.
    1. Prerequisites: List all students using the `list` command. Multiple students in the list and name is not `Clark Kent`.
    2. Test case: `edit_s 1 n/Clark Kent p/99999999 e/e0000000@u.nus.edu a/Raffles Hall 22/B/2`
       Expected: The first student's name is changed to `Clark Kent`. The updated student details are shown in the list.
    3. Test case: `edit_s 1 n/Clark Kent`
       Expected: No student is edited. Error details are shown in the status message as the edit did not change the student.
    4. Test case: `edit_s 0 n/Josh Yoseph`
       Expected: No student is edited. Error details shown in the status message as the index is out of the student list.
    5. Other test cases to try: `edit_s 1 n/helloworld* p/helloworld* e/helloworld* a/helloworld*` (incorrect inputs, missing inputs, or incorrect prefixes used)
       Expected: No student is edited. Error message is shown as parameters with invalid formats were provided.
2. Editing CCA of a student will be handled in separate test cases below.

### Editing the CCA information of a student

1. Adding CCAs to a student
    1. Prerequisites: List all students using the `list` command. Multiple students in the list. Multiple CCAs in the list and it has `Basketball` and `Acting`. It does not contain `Chess`.
    2. Test case: `edit_s 1 c/Basketball c/Acting`
       Expected: The first student is added to the `Basketball` CCA. The updated student details are shown in the list.
    3. Test case: `edit_s 1 c/Basketball`
       Expected: The first student is already in the `Basketball` CCA. Error details shown in the status message.
    4. Test case: `edit_s 1 c/Chess`
       Expected: The app does not have the `Chess` CCA. Error details shown in the status message.
    5. Test case: `edit_s 0 c/Basketball`
       Expected: No student is edited. Error details shown in the status message as the index is out of the student list.
    6. Other test cases to try: `edit_s 1 c/helloworld*` (incorrect inputs, missing inputs, or incorrect prefixes used)
       Expected: No student is edited. Error message is shown as parameters with invalid formats were provided.

2. Deleting CCAs from a student
    1. Prerequisite: List all students using the `list` command. Multiple students in the list. Multiple CCAs in the list and the first person has at least one CCA.
    2. Test case: `edit_s 1 c/`
       Expected: The first student is removed from the `Basketball` CCA. The updated student details are shown in the list.
    3. Test case: `edit_s 1 c/`
       Expected: The first student is already not in any CCA. Error details shown in the status message.
    4. Test case: `edit_s 0 c/`
       Expected: No student is edited. Error details shown in the status message as the index is out of the student list. Status bar remains the same.

### Editing a CCA

1. Editing an existing CCA's name and total sessions.
    1. Prerequisites: List all CCAs using the `list` command. Multiple CCAs in the list and name is not `Basketball`.
    2. Test case: `edit_c 1 n/Basketball t/15 r/President r/Vice-President r/Treasurer`
       Expected: The first CCA's name is changed to `Basketball`. The respective details are changed as specified. The updated CCA details are shown in the list.
    3. Test case: `edit_c 1 n/Basketball`
       Expected: No CCA is edited as the CCA already has the name `Basketball`. Other details are reserved. Error details shown in the status message as the edit did not change the CCA.
    4. Test case: `edit_c 0 n/Chess`
       Expected: No CCA is edited. Error details shown in the status message as the index is out of the CCA list.
    5. Other test cases to try: `edit_c 1 n/helloworld*` (incorrect inputs, missing inputs, or incorrect prefixes used, wrong index used)
       Expected: No CCA is edited. Error message is shown as parameters with invalid formats were provided.

2. Editing a role of a CCA is handled in separate test cases below.

### Editing a role of a CCA

1. Adding a role to a CCA
    1. Prerequisites: List all CCAs using the `list` command. Multiple CCAs in the list.
    2. Test case: `edit_c 1 r/President r/Vice-President r/Treasurer`
       Expected: The first CCA is added with the `President`, `Vice-President`, and `Treasurer` roles. The updated CCA details are shown in the list.
    3. Test case: `edit_c 1 r/President`
       Expected: The first CCA is added with the `President` role. Other roles get deleted. The updated CCA details are shown in the list.
    4. Test case: `edit_c 1 r/President`
       Expected: The CCA already has the same set of roles. Error details are shown in the status message.
    5. Test case: `edit_c 0 r/Treasurer`
       Expected: No CCA is edited. Error details shown in the status message as the index is out of the CCA list.
    6. Other test cases to try: `edit_c 1 r/helloworld*` (incorrect inputs, missing inputs, or incorrect prefixes used)
       Expected: No CCA is edited. Error message is shown as parameters with invalid formats were provided.

2. Deleting a role from a CCA
    1. Prerequisite: List all CCAs using the `list` command. Multiple CCAs in the list. Multiple roles in the list and the first CCA has at least one role.
    2. Test case: `edit_c 1 r/`
       Expected: The first CCA is removed from the `President` role. The updated CCA details are shown in the list.
    3. Test case: `edit_c 1 r/`
       Expected: The first CCA already does not have any role. Error details shown in the status message.
    4. Test case: `edit_c 0 r/`
       Expected: No CCA is edited. Error details shown in the status message as the index is out of the CCA list. Status bar remains the same.

### **Record Attendance**

1. Recording attendance for a student already in a CCA
    1. Prerequisite: Multiple students in the list (e.g., from previous sample data). The second student (index `2`) is already in the `Basketball` CCA. The `Chess` CCA does not exist and `Dancing` CCA is not contained in the first student.
    2. Test case: `attend 2 n/Basketball a/1`
   Expected: The second student’s attendance for the `Basketball` CCA is incremented by 1.  A success message is displayed, indicating that attendance has been updated. 
   3. Test case: `attend 2 n/Chess a/1`
   Expected: No attendance is recorded. An error message is displayed indicating that the `Chess` does not exist).
   4. Test case: `attend 2 n/Dancing a/1`
    Expected: No attendance is recorded. An error message is displayed indicating that the student is not in the `Dancing` CCA.
   5. Test case: `attend 0 n/Basketball a/1`
    Expected: No attendance is recorded. An error message is shown, since `0` is out of the valid student index range. 
   6. Test case: `attend 2 n/Basketball`
   Expected: No attendance is recorded. An error message is displayed, indicating missing or invalid parameters (e.g., `a/AMOUNT`).
   7. Other incorrect attendance commands to try: `attend`, `attend x`, `attend 2`, `attend 2 n/Basketball a/abc` (where x is larger than the list size).
    Expected: Similar to previous.

### Adding a role to a student

1. {write test cases when implemented}

### Deleting a role from a student

1. {write test cases when implemented}

### Saving data

1. Dealing with missing/corrupted data files
    1. Prerequisite: For this test, you will use the sample data provided when the app is launched the first time. Make sure the data file is not corrupted.
    2. Test case: Delete the data file.
       Under the app folder, go to the data folder, and delete the `addressbook.json` file.
       Expected: The app should start with the sample data when launched. Upon any action with storage (e.g. adding a student), a new data file should be created.
    3. Test case: Corrupt the data file.
       Open the `addressbook.json` file in a text editor and delete the first five lines.
       Expected: The app should start with an empty data when launched. Upon any action with storage (e.g. adding a student), a new data file should be created.
    4. Test case: Corrupt the data file with an extra parameter.
       Open the `addressbook.json` file in a text editor and add `    "name" : "Alice Paul",` in the third line.
       Expected: The app should start with the sample data when launched. However, the first person's name is `Alice Paul` instead of `Alex Yeoh`.
    5. Test case: Corrupt the data file with an invalid parameter.
       Open the `addressbook.json` file in a text editor and edit `    "name" : "Alex Yeoh",` to `    "name" : "Alex !!!",` in the third line.
       Expected: The app should start with an empty data when launched. Upon any action with storage (e.g. adding a student), a new data file should be created.
