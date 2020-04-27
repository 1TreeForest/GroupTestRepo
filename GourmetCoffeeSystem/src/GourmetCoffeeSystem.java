
import java.io.*;
import java.util.*;
/**
 * This class implements a GourmetCoffeeSystem.
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
public class GourmetCoffeeSystem{

	private static BufferedReader  stdIn =
			new  BufferedReader(new  InputStreamReader(System.in));
	private static PrintWriter  stdOut = new  PrintWriter(System.out, true);
	private static PrintWriter  stdErr = new  PrintWriter(System.err, true);

	private ProductDatabase  productdatabase;
	private SalesDatabase salesdatabase;

	/**
	 * The main function loads the information of the product and
   sales database and starts the application.

	 * @param args
	 * @throws IOException
	 */

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		ProductDatabase  productdatabase = load();

		GourmetCoffeeSystem  app = new  GourmetCoffeeSystem(productdatabase,salesdatabase);

		app.run();

	}
	/**
	 * Loads the information of a product object.		
	 * @return
	 */

	private static ProductDatabase load()  {
		ProductDatabase productdatabase = new ProductDatabase();

		productdatabase.addProduct(new Coffee("C001", "Colombia,Whole,1 lb", 17.99,
				"Colombia", "Medium","Rich and Hearty","Rich","Medium","Full"));
		productdatabase.addProduct(new Coffee("C002", "Colombia,Ground,1 lb", 18.75,
				"Colombia", "Medium","Rich and Hearty","Rich","Medium","Full"));
		productdatabase.addProduct(new Coffee("C007", "Guatemala,Whole,1 lb", 17.99,
				"Guatemala", "Medium","Rich and complex","spicy","Medium to high","Medium to Full"));
		productdatabase.addProduct(new Coffee("C008", "Guatemala,Ground,1 lb", 18.75,
				"Guatemala", "Medium","Rich and complex","Spicy","Medium to high","Medium to Full"));
		productdatabase.addProduct(new CoffeeBrewer("B001", "Home Coffee Brewer", 150.00,
				"Brewer 100", "Pourover",6));
		productdatabase.addProduct(new CoffeeBrewer("B002", "Coffee Brewer,2 Warmers", 200.00,
				"Brewer 200", "Pourover",12));
		productdatabase.addProduct(new CoffeeBrewer("B003", "Coffee Brewer,3 Warmers", 280.00,
				"Brewer 210", "Pourover",12));
		productdatabase.addProduct(new CoffeeBrewer("B004", "Commercial Brewer,20 cups", 380.00,
				"Quick Coffee 100", "Automatic",20));
		productdatabase.addProduct(new CoffeeBrewer("B005", "Commercial Brewer,40 cups", 480.00,
				"Quick Coffee 200", "Automatic",40));
		productdatabase.addProduct(new CoffeeAccessary("A001", "Almond Flavored Syrup", 9.00));
		productdatabase.addProduct(new CoffeeAaProduct(new CoffeeAccessary("A005", "Gourmet Coffee Cokkies", 12.00));
		productdatabase.addProduct(new CoffeeAccessary("A007", "Gourmet Coffee Ceramic Mug", 8.00));
		productdatabase.addProduct(new CoffeeAccessary("A009", "Gourmet Coffee 36 Cup Filters", 45.00));

		return productdatabase;
	}

	/**
	 * Initialize the product and the sales database
	 * @param initialproduct
	 * @param initialSalesdatabase
	 */
	private GourmetCoffeeSystem(ProductDatabase initialproductdatabase,SalesDatabase initialSalesdatabase) {

		this.productdatabase= initialproductdatabase;
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
				displayCatalog();
			} else if (choice == 2)  {
				displayProductItem();
			} else if (choice == 3)  {
				displayCurrentOrder();
			} else if (choice == 4)  {
				addProduct();
			} else if (choice == 5)  {
				removeProduct();
			} else if (choice == 6)  {
				registerTheOrder();
			}else if (choice == 7)  {
				displaySaleslist();
			}else if (choice ==8)   {
				displaySpecific();
			}else if (choice ==9)   {
				displayTotalProduct();
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

	private void displayCatalog() {

		int numberOfItems = this.product.getNumberOfItems();

		if (numberOfItems == 0) {
			stdErr.println("The catalog is empty");
		} else {
			for (Iterator<ProductItem> i = product.getItemsIterator();
					i.hasNext();) {

				ProductItem item = (ProductItem) i.next();

				stdOut.println(item.getCode() + " " +item.getDescription()
				+ (item.isAvailable()? "(A)" : "(NA)"));
			}
		}
	}
	/*
	 * Displays a catalog item.
	 */

	private void displayProductItem()  throws IOException  {

		ProductItem item = readProductItem();

		if (item != null) {
			stdOut.println("  Price: " + item.getPrice());
			stdOut.println("  Description: " + item.getDescription());
			if (item instanceof Coffee) {

				Coffee coffee = (Coffee) item;

				stdOut.println("  Origin: " + coffee.getOrigin());
				stdOut.println("  Roast: " + coffee.getRoast());
				stdOut.println("  Flavor: " + coffee.getFlavor());
				stdOut.println("  Aroma: " + coffee.getAroma());
				stdOut.println("  Acidity: " + coffee.getAcidity());
				stdOut.println("  Body: " + coffee.getBody());
			} else if (item instanceof CoffeeBrewer) {

				CoffeeBrewer coffeebrewer = (CoffeeBrewer) item;

				stdOut.println("  Model: " + coffeebrewer.getModel());
				stdOut.println("  WaterSupply: " + coffeebrewer.getWaterSupply());
				stdOut.println("  Number: " + coffeebrewer.getNumber());
			}else if(item instanceof CoffeeAccessary) {
				CoffeeAccessary coffeeAccessary =(CoffeeAccessary) item;



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
	private void displayCurrentOrder() {

		if (salesdatabase.getNumberOfOrders() == 0) {
			stdErr.println("The database of order is empty");
		} else {
			for (Iterator<Order> i = salesdatabase.getOrdersIterator();
					i.hasNext();) {

				Order order = (Order) i.next();

				stdOut.println(order.getQuantity() + " " +	order.getCode()+" "+ order.getDescrption()+" "+order.getPrice());
			}
		}
	}

	/*
	 * add a product to order
	 */
	private void addProduct()  throws IOException {
		ProductItem item = readProductItem();

		Order order=salesDatabase.getOrder();
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
				order.getQuantity(i);
				order.getSalesItems().addItem(item);
				stdOut.println("The item " + item.getCode()
				+ " has been added to the order " );
				double total=0;
				for (Iterator<ProductItem> i = salesitems.getItemsIterator();
						i.hasNext();) {

					ProductItem item = (ProductItem) i.next();

					total=total+order.getPrice()*order.getQuantity();



				}

				stdOut.println("Order total is :"+ total +".");
			}	
		}
	}


	/*
	 * remove a product from saleslist
	 */
	private void removeProduct()  throws IOException  {

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
/*
 * register the order to database
 */
private void registerTheOrder() throws IOException{


	salesdatabase.addorder(order);
	Order saleslist = new Order();
}


/*
 * lists all the orders that have been sold
 */
private void displaySaleslist()throws IOException{
	ArrayList<Order> arrayList = new ArrayList<Order>();
	int numberOfItems = salesdatabase.getNumberOfItems();
	for (Iterator<Order> i = salesdatabase.getOrdersIterator();
			i.hasNext();) {

		arrayList.append(order);
	}
	arrayList = new ArrayList<>(new HashSet<>(arrayList.getCode));
	for (int i=0;i<arrayList.size();i++){
		println(arrayList.get(i).getCode);
	}
}

/*
 * 	Display number of orders with a specific product
 */
private void displaySpecific()throws IOException{
	ArrayList<Order> arrayList = new ArrayList<Order>();
	int numberOfItems = salesdatabase.getNumberOfItems();
	for (Iterator<Order> i = salesdatabase.getOrdersIterator();
			i.hasNext();) {

		arrayList.append(order);
	}
	ProductItem item = readProductItem();
	boolean compare=true;
	int number=0;
	if (item==null) {
		stdErr.println("There is no catalog item with that code");
	}else {
		for (int i=0;i<arrayList.size();i++){
			if(compare==item.getCode.equals(arrayList.get(i).getCode)) {
				number=number+arrayList.getSalesItems().getprice();
			}
		}
	}
	println("The number of the orders of "+item.getCode+"is"+number);



}

/*
 * Display the total quantity sold for each products
 */
private void displayTotalProduct()throws IOException{
	ArrayList<Order> arrayList = new ArrayList<Order>();
	int numberOfItems = salesdatabase.getNumberOfItems();
	for (Iterator<Order> i = salesdatabase.getOrdersIterator();
			i.hasNext();) {

		arrayList.append(order);
	}
	for(int i=0;i<arrayList.size();i++) {
		String compare=arrayList.get(i).getCode();
		for(int j=0;j<arrayList.size();j++) {
			if(arrayList.get(j).getCode()==arrayList.get(i).getCode()) {
				arrayList.get(i).setQuantity(arrayList.get(i).getQuantity()+arrayList.get(j).getQuantity());
				arrayList.remove(j);}

		}
	}
	for (int i=0;i<arrayList.size();i++){
		stdOut.println("code:"+arrayList.get(i).getCode()+"   quantity:"+arrayList.get(i).getQuantity());
	}
}












private  ProductItem readProductItem() throws IOException  {

	stdOut.print("Product item code> ");
	stdOut.flush();

	return this.ProductDatabase.getProduct(stdIn.readLine());
}



}
