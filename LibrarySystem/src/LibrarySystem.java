
import java.io.*;
import java.util.*;
/**
 * This class implements a coffee system.
 * @author 29698
 * @see Coffee
 * @see ProductItem
 * @see CoffeeBrewer
 * @see CoffeeAccessary
 * @see SalesDatabase
 * @see Saleslist
 * @see SalesItems
 * @see Product
 *
 */
public class CoffeeSystem{

	private static BufferedReader  stdIn =
			new  BufferedReader(new  InputStreamReader(System.in));
	private static PrintWriter  stdOut = new  PrintWriter(System.out, true);
	private static PrintWriter  stdErr = new  PrintWriter(System.err, true);

	private Product  product;
	private SalesDatabase salesdatabase;

	/**
	 * The main function loads the information of the product and
   sales database and starts the application.

	 * @param args
	 * @throws IOException
	 */

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Product  product = load();
		SalesDatabase salesdatabase = load(product);


		CoffeeSystem  app = new  CoffeeSystem(product,salesdatabase);

		app.run();

	}
	/**
	 * Loads the information of a product object.		
	 * @return
	 */

	private static Product load()  {
		Product product = new Product();

		product.addItem(new Coffee("C001", "Colombia,Whole,1 lb", 17.99,
				"Colombia", "Medium","Rich and Hearty","Rich","Medium","Full"));
		product.addItem(new Coffee("C002", "Colombia,Ground,1 lb", 18.75,
				"Colombia", "Medium","Rich and Hearty","Rich","Medium","Full"));
		product.addItem(new Coffee("C007", "Guatemala,Whole,1 lb", 17.99,
				"Guatemala", "Medium","Rich and complex","spicy","Medium to high","Medium to Full"));
		product.addItem(new Coffee("C008", "Guatemala,Ground,1 lb", 18.75,
				"Guatemala", "Medium","Rich and complex","Spicy","Medium to high","Medium to Full"));
		product.addItem(new CoffeeBrewer("B001", "Home Coffee Brewer", 150.00,
				"Brewer 100", "Pourover",6));
		product.addItem(new CoffeeBrewer("B002", "Coffee Brewer,2 Warmers", 200.00,
				"Brewer 200", "Pourover",12));
		product.addItem(new CoffeeBrewer("B003", "Coffee Brewer,3 Warmers", 280.00,
				"Brewer 210", "Pourover",12));
		product.addItem(new CoffeeBrewer("B004", "Commercial Brewer,20 cups", 380.00,
				"Quick Coffee 100", "Automatic",20));
		product.addItem(new CoffeeBrewer("B005", "Commercial Brewer,40 cups", 480.00,
				"Quick Coffee 200", "Automatic",40));
		product.addItem(new CoffeeAccessories("A001", "Almond Flavored Syrup", 9.00));
		product.addItem(new CoffeeAccessories("A002", "Irish Creme Flavored Syrup", 9.00));
		product.addItem(new CoffeeAccessories("A005", "Gourmet Coffee Cokkies", 12.00));
		product.addItem(new CoffeeAccessories("A007", "Gourmet Coffee Ceramic Mug", 8.00));
		product.addItem(new CoffeeAccessories("A009", "Gourmet Coffee 36 Cup Filters", 45.00));

		return product;
	}
	/**
	 * Loads a SalesDatabase object.
	 * @param product
	 * @return
	 */
	private static SalesDatabase load(Product product) {

		SalesrDatabase salesdatabase = new Salesdatabase();

		return salesdatabase;
	}
	/**
	 * Initialize the product and the sales database
	 * @param initialproduct
	 * @param initialSalesdatabase
	 */
	private CoffeeSystem(Product initialproduct,SalesDatabase initialSalesdatabase) {

		this.product= initialproduct;
		this.salesdatabase = initialSalesdatabase;
	}
	/**
	 * Presents the user with a menu of options and processes the selection.
	 * @throws IOException
	 */
	private void run() throws IOException  {

		int  choice = getChoice();

		while (choice != 0)  {

			if (choice == 1)  {
				displayproduct();
			} else if (choice == 2)  {
				displayproductitem();
			} else if (choice == 3)  {
				displaycurrentorder();
			} else if (choice == 4)  {
				addproduct();
			} else if (choice == 5)  {
				removeproduct();
			} else if (choice == 6)  {
				registerorder();
			}else if (choice == 7)  {
				displaysaleslist();
			}else if (choice ==8)   {
				displayspecific();
			}else if (choice ==9)   {
				displaytotalproduct();
			}
			choice = getChoice();
		}
	}



	/* Validates the user's choice. */
	private int  getChoice() throws IOException  {

		int  input;

		do  {
			try  {
				stdOut.println();
				stdOut.print("[0]  Quit\n"
						+ "[1]  Display catalog\n"
						+ "[2]  Display product\n"
						+ "[3]  Display current order\n"
						+ "[4]  Add modify product to in current order\n"
						+ "[5]  Remove product from current order\n"
						+ "[6]  Register sale of current order\n"
						+ "[7]  Display sales\n"
						+ "[8]  Display number of orders with a specific product\n"
						+ "[9]  Display the total quantity sold for each products\n"
						+ "choice> ");
				stdOut.flush();

				input = Integer.parseInt(stdIn.readLine());

				//stdErr.println();

				if (0 <= input && 9 >= input)  {
					break;
				} else {
					stdErr.println("Invalid choice:  " + input);
				}
			} catch (NumberFormatException  nfe)  {
				stdErr.println(nfe);
			}
		}  while (true);

		return  input;
	}
	/*
	 *  Displays the product.
	 */

	private void displayproduct() {

		int numberOfItems = this.product.getNumberOfItems();

		if (numberOfItems == 0) {
			stdErr.println("The catalog is empty");
		} else {
			for (Iterator<ProductItem> i = product.getItemsIterator();
					i.hasNext();) {

				ProductItem item = (ProductItem) i.next();

				stdOut.println(item.getcode() + " " +item.getdescription()
				+ (item.isAvailable()? "(A)" : "(NA)"));
			}
		}
	}
	/*
	 * Displays a catalog item.
	 */

	private void displayproductitem()  throws IOException  {

		ProductItem item = readProductItem();

		if (item != null) {
			stdOut.println("  Price: " + item.getprice());
			stdOut.println("  Description: " + item.getdescription());
			if (item instanceof Coffee) {

				Coffee coffee = (Coffee) item;

				stdOut.println("  Origin: " + coffee.getorigin());
				stdOut.println("  Roast: " + coffee.getroast());
				stdOut.println("  Flavor: " + coffee.getflavor());
				stdOut.println("  Aroma: " + coffee.getaroma());
				stdOut.println("  Acidity: " + coffee.getacidity());
				stdOut.println("  Body: " + coffee.getbody());
			} else if (item instanceof CoffeeBrewer) {

				CoffeeBrewer coffeebrewer = (CoffeeBrewer) item;

				stdOut.println("  Model: " + coffeebrewer.getmodel());
				stdOut.println("  WaterSupply: " + coffeebrewer.getwatersupply());
				stdOut.println("  Number: " + coffeebrewer.getnumber());
			}else if(item instanceof CoffeeAccessary) {
				CoffeeAccessary coffeeaccessary =(CoffeeAccessary) item;



			}
			stdOut.println("  Status: "
					+ (item.isAvailable() ? "Available" : "Not available"));
		} else {
			stdErr.println("There is no catalog item with that code");
		}
	}

	/*
	 * Displays the borrower database.
	 */
	private void displaycurrentorder() {

		if (salesdatabase.getNumberOfOrders() == 0) {
			stdErr.println("The database of order is empty");
		} else {
			for (Iterator<SalesList> i = salesdatabase.getordersiterator();
					i.hasNext();) {

				SalesList saleslist = (SalesList) i.next();

				stdOut.println(saleslist.getquantity() + " " +	saleslist.getcode()+" "+ saleslist.getdescription()+" "+saleslist.getprice());
			}
		}
	}

	/*
	 * add a product to saleslist
	 */
	private void addproduct()  throws IOException {
		ProductItem item = readProductItem();

		SalesList saleslist=salesdatabase.getorder();
		int number=input.nextInt();
		if (item==null) {
			stdErr.println("There is no catalog item with that code");
		} else if (item.isAvailable()) {

			if(item.getNumberOfItems()>null) {
				stdErr.println("There is no enough items");
			}else {
				stdOut.print("Product item quality>");
				Scanner input=new Scanner(System.in);
				int  i=input.nextInt();
				saleslist.getquantity(i);
				saleslist.getSalesItems().addItem(item);
				stdOut.println("The item " + item.getCode()
				+ " has been added to the order " );
				double total=0;
				for (Iterator<ProductionItem> i = salesitems.getItemsIterator();
						i.hasNext();) {

					ProductItem item = (ProductItem) i.next();

					total=total+saleslist.getprice()*saleslist.getquantity();



				}

				stdOut.println("Order total is :"+ total +".");
			}	
		}
	}


	/*
	 * remove a product from saleslist
	 */
	private void removeproduct()  throws IOException  {

		ProductItem item = readProductItem();
		if (item == null) {
			stdErr.println("There is no catalog item with that code");
		} else if (item.isAvailable()) {

			SalesList saleslist=salesdatabase.getorder();
			if(saleslist .getSalesItems().removeItem(item)) {;
			stdOut.println("The item " + item.getCode()
			+ " has been removed from the order" );
			}
			else {
				stdErr.println("The item " +  item.getCode() +
						" is not added");
			}
		}
	}
}
/*
 * register the order to database
 */
private void registerorder() throws IOException{


	salesdatabase.addorder(saleslist);
	SalesList saleslist = new SalesList();
}


/*
 * lists all the orders that have been sold
 */
private void displaysaleslist()throws IOException{
	ArrayList<SalesList> arrayList = new ArrayList<>();
	int numberOfItems = salesdatabase.getNumberOfItems();
	for (Iterator<SalesList> i = salesdatabase.getordersiterator();
			i.hasNext();) {

		arrayList.append(saleslist);
	}
	arrayList = new ArrayList<>(new HashSet<>(arrayList.getcode));
	for (int i=0;i<arrayList.size();i++){
		println(arrayList.get(i).getcode);
	}
}

/*
 * 	Display number of orders with a specific product
 */
private void displayspecific()throws IOException{
	ArrayList<SalesList> arrayList = new ArrayList<>();
	int numberOfItems = salesdatabase.getNumberOfItems();
	for (Iterator<SalesList> i = salesdatabase.getordersiterator();
			i.hasNext();) {

		arrayList.append(saleslist);
	}
	ProductItem item = readProductItem();
	boolean compare=true;
	int number=0;
	if (item==null) {
		stdErr.println("There is no catalog item with that code");
	}else {
		for (int i=0;i<arrayList.size();i++){
			if(compare==item.getcode.equals(arrayList.get(i).getcode)) {
				number=number+arrayList.getSalesItems().getprice();
			}
		}
	}
	println("The number of the orders of "+item.getcode+"is"+number);



}

/*
 * Display the total quantity sold for each products
 */
private void displaytotalproduct()throws IOException{
	ArrayList<SalesList> arrayList = new ArrayList<>();
	int numberOfItems = salesdatabase.getNumberOfItems();
	for (Iterator<SalesList> i = salesdatabase.getordersiterator();
			i.hasNext();) {

		arrayList.append(saleslist);
	}
	for(int i=0;i<arrayList.size();i++) {
		String compare=arrayList.get(i).getcode();
		for(int j=0;j<arrayList.size();j++) {
			if(arrayList.get(j).getcode()==arrayList.get(i).getcode()) {
				arrayList.get(i).setquantity(arrayList.get(i).getquantity()+arrayList.get(j).getquantity());
				arrayList.remove(j);}

		}
	}
	for (int i=0;i<arrayList.size();i++){
		println("code:"+arrayList.get(i).getcode()+"   quantity:"+arrayList.get(i).getquantity());
	}
}












private  ProductItem readProductItem() throws IOException  {

	stdOut.print("Product item code> ");
	stdOut.flush();

	return this.Product.getItem(stdIn.readLine());
}



}
