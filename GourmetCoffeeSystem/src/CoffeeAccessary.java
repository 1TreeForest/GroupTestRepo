/**
 * This class models coffee accessory. It extends {@link ProductItem} 
 *
 * @author Tingting Song
 * @version 1.0.0
 * @see ProductItem
 */

public class CoffeeAccessary extends ProductItem{
	
	
	
	public CoffeeAccessary(String iniCode,String iniDescription,double iniPrice) {
		super(iniCode,iniDescription,iniPrice);
	}

	/**
	 * Returns the string representation of this accessory.
	 *
	 * @return  the string representation of this accessory.
	 */
	public String toString() {
		return getCode()+"_"+getDescription()+"_"+getPrice();
	}

}
