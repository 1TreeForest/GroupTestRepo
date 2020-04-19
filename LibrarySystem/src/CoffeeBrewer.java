/**
 * This class models a CoffeeBrewer. It extends {@link ProductItem} and
 * adds the following information:
 * <ol>
 * <li>the model of the brewer, a <code>String</code></li>
 * <li>the way of water supply, a <code>String</code></li>
 * <li>the number of cups,an <code>int</code></li>
 * </ol>
 *
 * @author Tingting Song
 * @version  1.0.0
 * @see ProductItem
 */
public class CoffeeBrewer extends ProductItem {
	
	/*the model of the brewer*/
       private String model;
       
    /*the way of water supply*/
       private String watersupply;
       
    /*the number of cups*/
       private int number;
      
   	/**
   	 * Constructs a <code>CoffeeBrewer</code> object.
   	 *
   	 * @param brewerCode  the code of the brewer.
   	 * @param brewerDescription  the description of the brewer.
   	 * @param brewerPrice  the price the price of the brewer.
   	 * @param iniModel	the model of the brewer
   	 * @param iniWaterSupply  the way of water supply
   	 * @param initialNumofCups  the number of cups.
   	 */
       public CoffeeBrewer(String brewerCode,String brewerDescription,double brewerPrice,String iniModel,String iniWaterSupply,int iniNumofCups) {
    	   super(brewerCode,brewerDescription,brewerPrice);
    	   model=iniModel;
    	   watersupply=iniWaterSupply;
    	   number=iniNumofCups;
       }
       
   	/**
   	 * Returns the model of this brewer.
   	 *
   	 * @return  the model of this brewer.
   	 */
       
       public String getmodel() {
    	   return model;
       }
       
   	/**
   	 * Returns the way of water supply.
   	 *
   	 * @return  the way of water supply.
   	 */
       public String getwatersupply() {
    	   return watersupply;
       }
       
   	/**
   	 * Returns the number of cups.
   	 *
   	 * @return  the number of cups.
   	 */
       public int getnumber() {
    	   return number;
       }
  
   	/**
   	 * Returns the string representation of this brewer.
   	 *
   	 * @return  the string representation of this brewer.
   	 */     
        public String toString() {
       	 return getCode()+"_"+getDescription()+"_"+getPrice()+"_"+getmodel()+"_"+getwatersupply()+"_"+getnumber();     
       	 }
   }
