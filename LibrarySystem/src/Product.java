/**
修改人：陈玲
修改处：注释、类名、部分变量名和方法名
*/
import java.util.*;


public class Product  {

	private Vector<ProductItem> items;

	
	public Product() {

		items = new Vector<ProductItem>();
	}

	/**
	 * Add a new product.
	 */
	public void addItem(ProductItem productItem) {

		items.add(productItem);
	}

	/**
	 * Returns an iterator over the items in this catalog.
	 */
	public Iterator<ProductItem> getItemsIterator() {

		return items.iterator();
	}

	/**
	 * Returns the object with the specified code. 
	 * Returns null if the object with the code is not found.      
	 */
	public ProductItem  getItem(String code)  {

		for (Iterator<ProductItem> i = getItemsIterator(); i.hasNext();) {

			ProductItem productItem = (ProductItem) i.next();

			if (productItem.getcode().equals(code)) {

				return ProductItem;
			}
		}

		return null;
	}

	/**
	 * Returns the number of items in the catalog.
	 */
	public int  getNumberOfItems()  {

		return items.size();
	}
}
