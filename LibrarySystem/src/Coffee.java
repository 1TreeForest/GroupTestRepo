/**
 * This class models coffee. It extends {@link ProductItem} and adds the
 * following information:
 * <ol>
 * <li>the origin of the coffee, a <code>String</code></li>
 * <li>the roast of the coffee, a <code>String</code></li>
 * <li>the flavor of the coffee, a <code>String</code></li>
 * <li>the aroma of the coffee, a <code>String</code></li>
 * <li>the acidity of the coffee, a <code>String</code></li>
 * <li>the body of the coffee, a <code>String</code></li>
 * </ol>
 *
 * @author Tingting Song
 * @version 1.0.0
 * @see ProductItem
 */

public class Coffee extends ProductItem {
	private String origin;
	private String roast;
	private String flavor;
	private String aroma;
	private String acidity;
	private String body;

	/**
	 * Constructs a <code>CoffeeBrewer</code> object.
	 *
	 * @param inicoffeeCode        the code of the coffee.
	 * @param inicoffeeDescription the description of the coffee.
	 * @param inicoffeePrice       the price of the coffee.
	 * @param iniRoast             the roast of the coffee
	 * @param iniWaterSupply       the way of water supply
	 * @param initialNumofCups     the number of cups.
	 */
	public Coffee(String inicoffeeCode, String inicoffeeDescription, double inicoffeePrice, String iniOrigin,
			String iniRoast, String iniFlavor, String iniAroma, String iniAcidity, String iniBody) {
		super(inicoffeeCode, inicoffeeDescription, inicoffeePrice);
		origin = iniOrigin;
		roast = iniRoast;
		flavor = iniFlavor;
		aroma = iniAroma;
		acidity = iniAcidity;
		body = iniBody;
	}
	
	/**
	 * Returns the origin of this coffee.
	 *
	 * @return the origin of this coffee.
	 */
	public String getorigin() {
		return origin;
	}

	/**
	 * Returns the roast of this coffee.
	 *
	 * @return the roast of this coffee.
	 */
	public String getroast() {
		return roast;
	}

	/**
	 * Returns the flavor of this coffee.
	 *
	 * @return the flavor of this coffee.
	 */
	public String getflavor() {
		return flavor;
	}

	/**
	 * Returns the aroma of this coffee.
	 *
	 * @return the aroma of this coffee.
	 */
	public String getaroma() {
		return aroma;
	}

	/**
	 * Returns the acidity of this coffee.
	 *
	 * @return the acitidy of this coffee.
	 */
	public String getacidity() {
		return acidity;
	}

	/**
	 * Returns the body of this coffee.
	 *
	 * @return the body of this coffee.
	 */
	public String getbody() {
		return body;
	}

   	/**
   	 * Returns the string representation of this coffee.
   	 *
   	 * @return  the string representation of this coffee.
   	 */ 
	public String toString() {
		return getcode() + "_" + getdescription() + "_" + getprice() + "_" + getorigin() + "_" + getroast() + "_"
				+ getflavor() + "_" + getaroma() + "_" + getacidity() + "_" + getbody();
	}
}
