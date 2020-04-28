public class ProductItem {

	/* Code of the item. */
	private String  code;

	/* Description of the item. */
	private String  description;

	/* Price of the item. */
	private double  price;

	/* Indicates if the item is available */
	private boolean  available;

	/**
	 * Constructs a <code>ProductItem</code> object.
	 */
	public ProductItem(String initialCode, String initialDescription,
			double initialPrice) {

		code = initialCode;
		description = initialDescription;
		price = initialPrice;
		available = true;
	}

	/**
	 * Returns the code of this item.
	 */
	public String getCode() {

		return  code;
	}

	/**
	 * Returns the description of this item.
	 */
	public String getDescription() {

		return  description;
	}

	/**
	 * Returns the price of this item .
	 */
	public double getPrice() {

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
	 * Returns <code>true</code> if the code of this product item is
	 * equal to the code of the argument
	 */
	public boolean equals(Object object) {
		return object instanceof ProductItem
		       && getCode().equals(((ProductItem) object).getCode());
	}

	/**
	 * Returns the string representation of this product item.
	 */
	public String toString() {

		return  getCode() + "_" + getDescription() + "_" + getPrice()
		        + "_" + isAvailable();
	}
	public String toString2() {

		return  getCode() + "\t" + getDescription() + "\t" + getPrice();
		        
	}
}

