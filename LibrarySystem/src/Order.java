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
 * @see SalesItems
 * @see ProductItem
 */
public class Order {

	/* Identification quantity of the order.*/
	private int quantity;

	/* Code of the order.*/
	private String  code;

	/*Price of thr order*/
	private double price;

	/*Total of the order*/
	private double total;

	/* Items checked out by the order.*/
	private SalesItems  salesItems;

	/**
	 * Constructs a <code>Order</code> object.
	 * <p>
	 * The collection of the borrowed items is initially empty.
	 * </p>
	 *
	 * @param initialQuantity  the quantity of the borrower.
	 * @param initialCode  the code of the borrower.
	 * @param initialPrice  the price of the borrower.
	 * @param initialTotal  the total of the borrower.
	 */
	public Order(int initialQuantity, String initialCode, double initialPrice, double initialTotal) {

		quantity = initialQuantity;
		code = initialCode;
		price = initialPrice;
		total = initialTotal;
		salesItems = new SalesItems();
	}

	/**
	 * Returns the identification quantity of this Order.
	 *
	 * @return  the identification number of this Order.
	 */
	public int  getQuantity()  {

		return  quantity;
	}

	/**
	 * Returns the code of this Order
	 * @return  the code of this Order.
	 */
	public String  getCode () {

		return  code;
	}

	/**
	 * Returns the price of this Order
	 * @return  the price of this Order.
	 */
	public double  getPrice () {

		return  price;
	}

	/**
	 * Returns the total of this Order
	 * @return  the total of this Order.
	 */
	public double  getTotal () {

		return  total;
	}

	/**
	 * Returns the sales items collection.
	 *
	 * @return  a {@link SalesItems} object.
	 */
	public SalesItems getSalesItems() {

		return  salesItems;
	}

	/**
	 * Returns <code>true</code> if the code of this Order is
	 * equal to the Order of the argument.
	 *
	 * @param object  object with which this Order is compared.
	 * @return  <code>true</code> if the code of this Order is
	 *          equal to the code of the argument; <code>false</code>
	 *          otherwise.
	 */
	public boolean equals(Object object) {

		return object instanceof Order
		       && getCode().equals(((Order) object).getCode());
	}

	/**
	 * Returns the string representation of this Order.
	 *
	 * @return  the string representation of this Order.
	 */
	public String toString() {

		return  getCode();
	}
}
