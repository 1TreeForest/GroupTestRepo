import java.util.Iterator;
import java.util.Vector;

/**
 * This class models a library user. It contains the following
 * information:
 * <ol>
 * <li>The id of the order, a <code>String</code></li>
 * <li>The name of the order, a <code>String</code></li>
 * <li>The items checked out by the order,
 *     a <code>SalesItems</code> object</li>
 * </ol>
 *
 * @author A zyh
 * @version  1.0.0
 * @see SalesItem
 * @see ProductItem
 */
public class Order {

	/* Identification code of the order.*/
	private int orderCode;

	/*Total of the order*/
	private double total;

	/* Items checked out by the order.*/
	private Vector<SalesItem> salesItems;

	/**
	 * Constructs a <code>Order</code> object.
	 * <p>
	 * The collection of the borrowed items is initially empty.
	 * </p>
	 *
	 * @param initialorderCode  the code of the order
	 */
	public Order(int initialorderCode) {

		orderCode = initialorderCode;
		salesItems = new Vector<SalesItem>();
	}

	public int getOrderCode(){
		return orderCode;
	}

	/**
	 * Returns the total of this Order
	 * @return  the total of this Order.
	 */
	public double  getTotal () {

		return  total;
	}

	  /*delete salesItem*/
	public void deleteSalesItem(String code,SalesDatabase salesDatabase){
		int count=0;
		for (Iterator<SalesItem> i = getSalesItemIterator(); i.hasNext();){

			SalesItem salesItem =(SalesItem) i.next();

			if((salesItem.getProductItem().getCode()).equals(code)){
				count = salesItem.getQuantity();
				int value = salesDatabase.getQuantityToProductsLog().get(code);
				value-=salesItem.getQuantity();
				salesDatabase.getQuantityToProductsLog().put(code,value);
				count--;
				if(count==0){
					value = salesDatabase.getOrdersNumToProductsLog().get(code);
					value-=1;
					salesDatabase.getOrdersNumToProductsLog().put(code,value);
				}
				salesItems.remove(salesItem);
			}
		}

	}


	/*add salesItem*/
	public void addSalesItem(String code,ProductDatabase productDatabase,SalesDatabase salesDatabase){
		int count=0;
		for(Iterator<ProductItem> i = productDatabase.getProductsIterator(); i.hasNext();){

			ProductItem productItem = (ProductItem) i.next();

			if((productItem.getCode()).equals(code)){
				SalesItem salesItem = new SalesItem(productItem,1);
				int value = salesDatabase.getQuantityToProductsLog().get(code);
				value+=salesItem.getQuantity();
				salesDatabase.getQuantityToProductsLog().put(code,value);
				if (count==0){
					value = salesDatabase.getOrdersNumToProductsLog().get(code);
					value+=1;
					salesDatabase.getQuantityToProductsLog().put(code,value);
				}
				count++;
				salesItems.add(salesItem);
			}
		}
	}
	/**
	 * Returns the sales items iterator.
	 *
	 * @return  a {@link SalesItem} object.
	 */
	public Iterator<SalesItem> getSalesItemIterator() {

		return  salesItems.iterator();
	}

	/**
	 * Returns the string representation of this Order.
	 *
	 * @return  the string representation of this Order.
	 */
	public String toString() {

		return Double.toString(getTotal());
	}
}
