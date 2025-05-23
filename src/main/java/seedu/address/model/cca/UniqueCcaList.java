package seedu.address.model.cca;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.cca.exceptions.CcaNotFoundException;
import seedu.address.model.cca.exceptions.DuplicateCcaException;

/**
 * A list of CCAs that enforces uniqueness between its elements and does not allow nulls.
 * A CCA is considered unique by comparing using {@code Cca#isSameCca(Cca)}. As such, adding and updating of
 * CCAs uses Cca#isSameCca(Cca) for equality, to ensure that the CCA being added or updated is
 * unique in terms of identity in the UniqueCcaList. However, the removal of a CCA uses Cca#equals(Object),
 * to ensure that the CCA with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Cca#isSameCca(Cca)
 */
public class UniqueCcaList implements Iterable<Cca> {

    private final ObservableList<Cca> internalList = FXCollections.observableArrayList();
    private final ObservableList<Cca> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent CCA as the given argument.
     */
    public boolean contains(Cca toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCca);
    }

    /**
     * Returns true if the list contains an equivalent CCA with the given {@code ccaName} as the given argument.
     */
    public boolean contains(CcaName ccaName) {
        requireNonNull(ccaName);
        return internalList.stream().anyMatch(cca -> cca.getCcaName().equals(ccaName));
    }

    /**
     * Adds a CCA to the list.
     * The CCA must not already exist in the list.
     */
    public void add(Cca toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCcaException();
        }
        internalList.add(toAdd);
    }

    /**
     * Gets the CCA with the given {@code ccaName} from the list.
     * The CCA must exist in the list.
     */
    public Cca getCca(CcaName ccaName) {
        requireNonNull(ccaName);
        for (Cca cca : internalList) {
            if (cca.getCcaName().equals(ccaName)) {
                return cca;
            }
        }
        throw new CcaNotFoundException();
    }

    /**
     * Replaces the CCA {@code target} in the list with {@code editedCca}.
     * {@code target} must exist in the list.
     * The CCA identity of {@code editedCca} must not be the same as another existing CCA in the list.
     */
    public void setCca(Cca target, Cca editedCca) {
        requireAllNonNull(target, editedCca);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CcaNotFoundException();
        }

        if (!target.isSameCca(editedCca) && contains(editedCca)) {
            throw new DuplicateCcaException();
        }

        internalList.set(index, editedCca);
    }

    /**
     * Replaces the contents of this list with {@code ccas}.
     * {@code ccas} must not contain duplicate CCAs.
     */
    public void setCcas(UniqueCcaList ccas) {
        requireNonNull(ccas);
        internalList.setAll(ccas.internalList);
    }

    /**
     * Replaces the contents of this list with {@code ccas}.
     * {@code ccas} must not contain duplicate CCAs.
     */
    public void setCcas(List<Cca> ccas) {
        requireAllNonNull(ccas);
        if (!ccasAreUnique(ccas)) {
            throw new DuplicateCcaException();
        }

        internalList.setAll(ccas);
    }

    /**
     * Removes the equivalent CCA from the list.
     * The CCA must exist in the list.
     */
    public void remove(Cca toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CcaNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Cca> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Cca> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UniqueCcaList)) {
            return false;
        }

        UniqueCcaList otherList = (UniqueCcaList) other;
        return internalList.equals(otherList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code ccas} contains only unique CCAs.
     */
    private boolean ccasAreUnique(List<Cca> ccas) {
        for (int i = 0; i < ccas.size() - 1; i++) {
            for (int j = i + 1; j < ccas.size(); j++) {
                if (ccas.get(i).isSameCca(ccas.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
