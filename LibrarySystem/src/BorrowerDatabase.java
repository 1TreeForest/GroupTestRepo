import java.util.*;

/**
 * Maintains a collection of {@link Borrower} objects.
 *
 * @author A Jiayi
 * @version  1.0.0
 * @see Borrower
 */
public class BorrowerDatabase {

	/* Collection of <code>Borrower</code> objects.*/
	private Vector<Borrower> borrowers;

	/**
	 * Constructs an empty collection of {@link Borrower}
	 * objects.
	 */
	public BorrowerDatabase() {

		borrowers = new Vector<Borrower>();
	}

	/**
	 * Adds a {@link Borrower} object to this collection.
	 *
	 * @param borrower  the {@link Borrower} object.
	 */
	public void  addBorrower(Borrower borrower) {

		borrowers.add(borrower);
	}

	/**
	 * Returns an iterator over the borrowers in this database.
	 *
	 * return  an {@link Iterator}
	 */
	public Iterator<Borrower>  getBorrowersIterator() {

		return borrowers.iterator();
	}

	/**
	 * Returns the {@link Borrower} object with the specified
	 * <code>id</code>.
	 *
	 * @param code  the id of the borrower.
	 * @return  The {@link Borrower} object with the specified id.
	 *          Returns <code>null</code> if the object with the
	 *          id is not found.
	 */
	public Borrower  getBorrower(String id) {

		for (Iterator<Borrower> i = getBorrowersIterator(); i.hasNext();) {

			Borrower borrower = (Borrower) i.next();

			if (borrower.getId().equals(id)) {

				return borrower;
			}
		}

		return null;
	}

	/**
	 * Returns the number of {@link Borrower} objects in this collection.
	 *
	 * @return  the number of {@link Borrower} objects in this collection.
	 */
	public int  getNumberOfBorrowers() {

		return borrowers.size();
	}
}
