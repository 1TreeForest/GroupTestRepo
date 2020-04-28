
import java.util.*;


public class ProductDatabase  {

	/* Collection of <code>ProductItem</code> objects.*/
	private Vector<ProductItem> productItems;

	/**
	 * Constructs an empty product.
	 */
	public ProductDatabase() {

		productItems = new Vector<ProductItem>();
	}

	/**
	 * Adds a {@link ProductItem} object to this product.
	 */
	public void addProduct(ProductItem productItem) {

		productItems.add(productItem);
	}

	/**
	 * Returns an iterator over the items in this product.
	 *
	 * return  an {@link Iterator}
	 */
	public Iterator<ProductItem> getProductsIterator() {

		return productItems.iterator();
	}

	/**
	 * Returns the {@link ProductItem} object with the specified
	 * <code>code</code>.
	 *Returns <code>null</code> if the object with
	 *          the code is not found.
	 */
	public ProductItem  getProduct(String code)  {

		for (Iterator<ProductItem> i = getProductsIterator(); i.hasNext();) {

			ProductItem productItem = (ProductItem) i.next();

			if (productItem.getCode().equals(code)) {
				return productItem;
			}
		}

		return null;
	}

	/**
	 * Returns the number of items in the product.
	 */
	public int  getNumberOfItems()  {

		return productItems.size();
	}
}
