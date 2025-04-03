---
layout: default.md
title: "User Guide"
pageNav: 3
---

# CCAttendance User Guide

CCAttendance is a **desktop app for recording attendance of students in CCAs** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, CCAttendance can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/AY2425S2-CS2103T-T09-4/tp/releases/tag/v1.3).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all students.

   * `create_s n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the list.

   * `delete_s 3` : Deletes the 3rd student shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `create_s n/NAME`, `NAME` is a parameter which can be used as `create_s n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [c/CCA_NAME]` can be used as `n/John Doe c/Basketball` or as `n/John Doe`.

* Items with ... after them can be used multiple times including zero times.
e.g. `[r/ROLE]`... can be used as `  `(i.e. 0 times), `r/President`, `r/Vice-President` `r/Treasurer` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a student: `create_s`

Adds a student to the list of students.

Format: `create_s n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS​`

<box type="tip" seamless>

</box>

Examples:
* `create_s n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

### Adding a CCA: `create_c`

Adds a CCA to the list of CCAs.

Format: `create_c n/CCA_NAME`

Examples:
* `create_c n/Basketball`

### Adding a CCA to a student : `add_c`

Assigns an existing CCA from the CCA list to a specific student. The student will be assigned a default role (e.g., "Member") for the added CCA.

Format: `add_c INDEX c/CCA_NAME`

* Assigns the CCA specified by `CCA_NAME` to the student at the specified `INDEX`.
* The index `INDEX` refers to the index number shown in the displayed student list. The index **must be a positive integer** (1, 2, 3, …).
* The CCA name prefix `c/` and the `CCA_NAME` are **mandatory**.
* The `CCA_NAME` provided must exactly match the name of a CCA already present in the main CCA list (use `list` or view the CCA panel to see available CCAs).
* The student must **not** already be assigned to the specified CCA.

Examples:
* `add_c 2 c/Tennis` Assigns the existing "Tennis" CCA to the student at index 2 in the current student list. The student gets the default role for Tennis.

### Listing all persons : `list`

Shows a list of all students in the address book.

Format: `list`

### Editing a student's basic details : `edit_s`

Edits the name, phone, email, or address of an existing student in the address book.

Format: `edit_s INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** (1, 2, 3, …).
* At least one of the optional fields (`n/`, `p/`, `e/`, `a/`) must be provided.
* Existing values for the specified fields will be overwritten by the new input values.

Examples:
* `edit_s 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the student at index 1 to be `91234567` and `johndoe@example.com` respectively. Their name, address, CCAs, and roles remain unchanged.
* `edit_s 3 n/Peter Tan a/Blk 123, Clementi Ave 4, #05-06` Edits the name and address of the student at index 3. Their phone, email, CCAs, and roles remain unchanged.

### Editing a cca : `edit_c`

Edits an existing cca in the address book.

Format: `edit_c INDEX [n/CCA_NAME] [r/ROLE_NAME]... [t/TOTAL_SESSIONS]`

* Edits the cca at the specified `INDEX`. The index refers to the index number shown in the displayed cca list. The index **must be a positive integer** 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* The new name must not match any existing any cca names in the cca list.
* The amount of total sessions must be a non-negative integer.
* Regardless of the input for the role field, a "Member" role will automatically be created.

Examples:
*  `edit_c 1 n/Volleyball r/Captain r/Vice-Captain t/40` Edits the cca with the first index in the cca list. Renames it to "Volleyball", updates the available roles to Captain, Vice-Captain and Member, and updates the total sessions to 40.

### Recording attendance : `attend`
Records the attendance of a student in a CCA.

Format: `attend INDEX [n/CCA_NAME] [a/AMOUNT]`

* Records the attendance of the student at the specified `INDEX` in the specified CCA.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​
* The CCA name must be provided.
* The amount of attendance must be provided.
* The amount of attendance must be a positive integer.

Examples:
* `attend 2 n/Basketball a/1` Records the attendance of the 2nd person in the student list in the CCA `Basketball` one time (i.e. increase attendance by 1).
* `attend 3 n/Basketball a/2` Records the attendance of the 3rd person in the student list in the CCA `Basketball` two times (i.e. increase attendance by 2).

### Locating students by name: `find`

Finds students whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a student : `delete_s`

Deletes the specified student from the list of students.

Format: `delete_s INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete_s 2` deletes the 2nd person in the student list.
* `find Betsy` followed by `delete_s 1` deletes the 1st person in the results of the `find` command.

### Deleting a CCA : `delete_c`

Deletes the specified CCA from the list of CCAs.

Format: `delete_c INDEX`

* Deletes the CCA at the specified `INDEX`.
* The index refers to the index number shown in the displayed CCA list.
* The index **must be a positive integer** 1, 2, 3, …​
* Unlike student list, the CCA list will always show all CCAs.

Examples:
* `delete_c 2` deletes the 2nd CCA in the CCA list.

### Removing a CCA from a student : `remove_c`

Removes a specific CCA assignment (including its associated role and attendance) from a student.

Format: `remove_c INDEX c/CCA_NAME`

* Removes the CCA specified by `CCA_NAME` from the student at the specified `INDEX`.
* The index `INDEX` refers to the index number shown in the displayed student list. The index **must be a positive integer** (1, 2, 3, …).
* The CCA name prefix `c/` and the `CCA_NAME` are **mandatory**.
* The student must currently be assigned to the specified `CCA_NAME` for the removal to be successful. 

Examples:
* `remove_c 1 c/Basketball` Removes the "Basketball" CCA assignment from the student at index 1 in the current student list.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

CCAttendance data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CCAttendance data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Create Student**    | `create_s n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS​` <br> e.g., `create_s n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665`
**Create CCA**    | `create_c n/CCA_NAME` <br> e.g., `create_c Basketball`
**Clear**  | `clear`
**Delete Student** | `delete_s INDEX`<br> e.g., `delete_s 3`
**Delete CCA** | `delete_c INDEX`<br> e.g., `delete_c 2`
**Edit Student**   | `edit_s INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CCA_NAME] [r/ROLE_NAME]​`<br> e.g.,`edit_s 2 n/James Lee e/jameslee@example.com c/Basketball r/Captain`
**Record Attendance**   | `attend INDEX [n/CCA_NAME] [a/AMOUNT]`<br> e.g., `attend 2 n/Basketball a/1`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
