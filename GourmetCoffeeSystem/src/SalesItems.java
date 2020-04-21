import java.util.*;

/**
 * Maintains a collection of {@link ProductItem} assigned to a borrower.
 *
 * @author A Jiayi
 * @version  1.0.0
 * @see ProductItem
 */
public class SalesItems {


	/* Product items assigned to a Order.*/
	private Vector<ProductItem> item;

	/**
	 * Sets the collection of {@link ProductItem} to empty.
	 */
	public SalesItems() {

		item = new Vector<ProductItem>();
	}

	/**
	 * Adds a {@link ProductItem} object to this collection and
	 * sets the {@link ProductItem} object as not available.
	 *
	 * @param item  the {@link ProductItem} object.
	 */
	public void  addItem(ProductItem productItem) {

		item.add(productItem);
		productItem.setAvailable(false);
	}

	/**
	 * Removes a {@link ProductItem} object from this collection
	 * and sets the {@link ProductItem} object as available.
	 *
	 * @param item  the {@link ProductItem} object.
	 */
	public boolean  removeItem(ProductItem productItem) {

		if (item.removeElement(productItem)){;
			productItem.setAvailable(true);
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
	public Iterator<ProductItem> getItemsIterator() {

		return item.iterator();
	}

	/**
	 * Returns the {@link ProductItem} object with the specified
	 * <code>code</code>.
	 *
	 * @param code  the code of an item.
	 * @return  The {@link ProductItem} object with the specified
	 *          code. Returns <code>null</code> if the object with
	 *          the code is not found.
	 */
	public ProductItem getItem(String code) {

		for (Iterator<ProductItem> i = getItemsIterator(); i.hasNext();) {

			ProductItem productItem = (ProductItem) i.next();

			if (productItem.getCode().equals(code)) {

				return productItem;
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

		return item.size();
	}
}
