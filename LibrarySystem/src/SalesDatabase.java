import java.util.*;

/**
 * Maintains a collection of {@link Order} objects.
 *
 * @author A zyh
 * @version  1.0.0
 * @see Order
 */
public class SalesDatabase {

	/* Collection of <code>SaleList</code> objects.*/
	private Vector<Order>orders;

	/**
	 * Constructs an empty collection of {@link Order}
	 * objects.
	 */
	public SalesDatabase() {

		orders = new Vector<Order>();
	}

	/**
	 * Adds a {@link Order} object to this collection.
	 *
	 * @param order  the {@link Order} object.
	 */
	public void  addOrder(Order order) {

		orders.add(order);
	}

	/**
	 * Returns an iterator over the borrowers in this database.
	 *
	 * return  an {@link Iterator}
	 */
	public Iterator<Order>  getOrdersIterator() {

		return orders.iterator();
	}

	/**
	 * Returns the {@link Order} object with the specified
	 * <code>code</code>.
	 *
	 * @param code  the code of the order.
	 * @return  The {@link Order} object with the specified code.
	 *          Returns <code>null</code> if the object with the
	 *          code is not found.
	 */
	public Order  getOrder(String code) {

		for (Iterator<Order> i = getOrdersIterator(); i.hasNext();) {

			Order order = (Order) i.next();

			if (order.getCode().equals(code)) {

				return order;
			}
		}

		return null;
	}

	/**
	 * Returns the number of {@link Order} objects in this collection.
	 *
	 * @return  the number of {@link Order} objects in this collection.
	 */
	public int  getNumberOfOrders() {

		return orders.size();
	}
}
