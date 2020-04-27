import java.util.*;

/**
 * Maintains a collection of {@link ProductItem} assigned to a borrower.
 *
 * @author A Jiayi
 * @version  1.0.0
 * @see ProductItem
 */
public class SalesItem {


	/* Product items assigned to a Order.*/
	private ProductItem productItem;

	/*quantity of the SalesItem*/
	private int quantity;

	/**
	 * Sets the collection of {@link ProductItem} to empty.
	 */
	public SalesItem(ProductItem initialProduct,int initialQuantity) {

		productItem = initialProduct;
		quantity = initialQuantity;
	}

	/*get quantity*/
	public int getQuantity(){
		return quantity;
	}

	/*set quantity*/
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public ProductItem getProductItem(){
		return productItem;
	}
	public String toString() {
		return Double.toString(getQuantity());
	}
}
