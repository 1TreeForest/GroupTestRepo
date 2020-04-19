/**
 *陈玲
 *修改部分：注释、变量名、方法名、类名
 */
import java.util.*;


public class Product  {

	/* Collection of <code>ProductItem</code> objects.*/
	private Vector<ProductItem> items;

	/**
	 * Constructs an empty product.
	 */
	public Product() {

		items = new Vector<ProductItem>();
	}

	/**
	 * Adds a {@link ProductItem} object to this product.
	 */
	public void addItem(ProductItem productItem) {

		items.add(productItem);
	}

	/**
	 * Returns an iterator over the items in this product.
	 *
	 * return  an {@link Iterator}
	 */
	public Iterator<ProductItem> getItemsIterator() {

		return items.iterator();
	}

	/**
	 * Returns the {@link ProductItem} object with the specified
	 * <code>code</code>.
	 *Returns <code>null</code> if the object with
	 *          the code is not found.
	 */
	public ProductItem  getItem(String code)  {

		for (Iterator<ProductItem> i = getItemsIterator(); i.hasNext();) {

			ProductItem productItem = (productItem) i.next();

			if (productItem.getcode().equals(code)) {

				return productItem;
			}
		}

		return null;
	}

	/**
	 * Returns the number of items in the product.
	 */
	public int  getNumberOfItems()  {

		return items.size();
	}
}
