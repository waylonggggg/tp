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

Examples:
* `create_s n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

### Adding a CCA: `create_c`

Adds a CCA to the list of CCAs.

Format: `create_c n/CCA_NAME`

<box type="warning" seamless>

**Caution:**
CCA names are case-sensitive. For example, `Basketball` and `basketball` are considered different CCA names.
</box>

Examples:
* `create_c n/Basketball`

### Adding a role to a student in a CCA: `add_r`

Adds a role to a student in a CCA.

Format: `add_r INDEX c/CCA_NAME r/ROLE_NAME`
* Adds a role to the student at the specified `INDEX` in the CCA.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** (1, 2, 3, …​)
* The role must exist in the CCA.
* The student must be in the CCA.
* The student must not have an existing role in the CCA.

Examples:
* `add_r 2 c/Basketball r/Captain` Adds the role `Captain` to the 2nd student in the student list in the CCA `Basketball`.


### Listing all students : `list`

Shows a list of all students in the address book.

Format: `list`

### Editing a student : `edit_s`

Edits an existing student in the address book.

Format: `edit_s INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing CCAs, the existing CCAs of the person will be removed i.e adding of CCAs is not cumulative.

Examples:
*  `edit_s 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.

### Editing a cca : `edit_c`

Edits an existing cca in the address book.

Format: `edit_c INDEX [n/CCA_NAME] [r/ROLE_NAME]... [t/TOTAL_SESSIONS]`

<box type="warning" seamless>

**Caution:**
Role names are case-sensitive. For example, `Captain` and `captain` are considered different.
</box>

* Edits the cca at the specified `INDEX`. The index refers to the index number shown in the displayed cca list. The index **must be a positive integer** (1, 2, 3, …).
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* The new name must not match any existing any cca names in the cca list.
* The amount of total sessions must be a non-negative integer.
* Regardless of the input for the role field, a "Member" role will automatically be created.
* If an existing student have their role removed from the cca, the student's role will be set to "Member".
* If an existing student's total sessions is above the new total sessions, the student's total sessions will be set to the new total sessions. 

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
* The resulting total sessions attended by the student must not exceed the total sessions of the CCA.

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
* Deleting a CCA will also delete the CCA from each student that is in the CCA.

Examples:
* `delete_c 2` deletes the 2nd CCA in the CCA list.

### Deleting a role from a student in a CCA : `delete_r`

Deletes a role from a student in a CCA.

Format: `delete_r INDEX c/CCA_NAME`

* Deletes the role from the student at the specified `INDEX` in the CCA (i.e. turns the student into a "Member").
* The index refers to the index number shown in the displayed student list.
* The index must be a positive integer (1, 2, 3, …​)
* The student must be in the CCA.
* The student must have a role in the CCA.

Examples:
* `delete_r 2 c/Basketball` Deletes the role of the 2nd student in the student list in the CCA `Basketball`.

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
**Add Role**    | `add_r INDEX c/CCA_NAME r/ROLE_NAME` <br> e.g., `add_r 2 c/Basketball r/Captain`
**Clear**  | `clear`
**Delete Student** | `delete_s INDEX`<br> e.g., `delete_s 3`
**Delete CCA** | `delete_c INDEX`<br> e.g., `delete_c 2`
**Delete Role** | `delete_r INDEX c/CCA_NAME`<br> e.g., `delete_r 2 c/Basketball`
**Edit Student**   | `edit_s INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CCA_NAME] [r/ROLE_NAME]​`<br> e.g.,`edit_s 2 n/James Lee e/jameslee@example.com c/Basketball r/Captain`
**Edit CCA**   | `edit_c INDEX [n/CCA_NAME] [r/ROLE_NAME]... [t/TOTAL_SESSIONS]`<br> e.g., `edit_c 2 n/Basketball r/Captain r/Vice-Captain t/40`
**Record Attendance**   | `attend INDEX [n/CCA_NAME] [a/AMOUNT]`<br> e.g., `attend 2 n/Basketball a/1`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
