import java.util.*;

/**
 * Maintains a collection of {@link CatalogItems} assigned to a borrower.
 *
 * @author A Jiayi
 * @version  1.0.0
 * @see CatalogItem
 */
public class BorrowedItems {


	/* Catalog items assigned to a borrower.*/
	private Vector<CatalogItem> items;

	/**
	 * Sets the collection of {@link CatalogItems} to empty.
	 */
	public BorrowedItems() {

		items = new Vector<CatalogItem>();
	}

	/**
	 * Adds a {@link CatalogItem} object to this collection and
	 * sets the {@link CatalogItem} object as not available.
	 *
	 * @param item  the {@link CatalogItem} object.
	 */
	public void  addItem(CatalogItem catalogItem) {

		items.add(catalogItem);
		catalogItem.setAvailable(false);
	}

	/**
	 * Removes a {@link CatalogItem} object from this collection
	 * and sets the {@link CatalogItem} object as available.
	 *
	 * @param item  the {@link CatalogItem} object.
	 */
	public boolean  removeItem(CatalogItem catalogItem) {

		if (items.removeElement(catalogItem)){;
			catalogItem.setAvailable(true);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns an iterator over the borrowed items in this collection.
	 *
	 * return  an {@link Iterator}
	 */
	public Iterator<CatalogItem> getItemsIterator() {

		return items.iterator();
	}

	/**
	 * Returns the {@link CatalogItem} object with the specified
	 * <code>code</code>.
	 *
	 * @param code  the code of an item.
	 * @return  The {@link CatalogItem} object with the specified
	 *          code. Returns <code>null</code> if the object with
	 *          the code is not found.
	 */
	public CatalogItem getItem(String code) {

		for (Iterator<CatalogItem> i = getItemsIterator(); i.hasNext();) {

			CatalogItem catalogItem = (CatalogItem) i.next();

			if (catalogItem.getCode().equals(code)) {

				return catalogItem;
			}
		}

		return null;
	}

	/**
	 * Returns the number of borrowed items.
	 *
	 * @return  the number of borrowed items.
	 */
	public int  getNumberOfItems() {

		return items.size();
	}
}
