/**
 *修改人：陈玲
 *修改：类名、方法名、注释以及部分变量
 */
public class ProductItem {

	/* Code of the item. */
	private String  code;

	/* Title of the item. */
	private String  description;

	/* Year the item was published. */
	private double  price;

	/* Indicates if the item is available */
	private boolean  available;

	/**
	 * Constructs a <code>ProductItem</code> object.
	 */
	public ProductItem(String initialCode, String initialdescription,
			double initialprice) {

		code = initialCode;
		description = initialdescription;
		price = initialprice;
		available = true;
	}

	/**
	 * Returns the code of this item.
	 */
	public String getcode() {

		return  code;
	}

	/**
	 * Returns the description of this item.
	 */
	public String getdescription() {

		return  description;
	}

	/**
	 * Returns the price of this item .
	 */
	public double getprice() {

		return  price;
	}
	
	/**
	 * Returns <code>true</code> if the item is available.
	 */
	public boolean isAvailable() {

		return available;
	}
	
	/**
	 * Sets the value of instance variable <code>available</code>.
	 */
	public void setAvailable(boolean newValue) {

		available = newValue;
	}

	/**
	 * Returns <code>true</code> if the code of this catalog item is
	 * equal to the code of the argument
	 */
	public boolean equals(Object object) {

		return object instanceof CatalogItem
		       && getCode().equals(((CatalogItem) object).getCode());
	}

	/**
	 * Returns the string representation of this catalog item.
	 */
	public String toString() {

		return  getcode() + "_" + getdescription() + "_" + getprice()
		        + "_" + isAvailable();
	}
}
