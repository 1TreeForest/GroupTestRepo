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
		ProductDatabase  productdatabase = loadProductDatabase();
		SalesDatabase  salesdatabase = loadSalesDatabase();

		GourmetCoffeeSystem  app = new  GourmetCoffeeSystem(productdatabase,salesdatabase);

		app.run();

	}
	private static SalesDatabase loadSalesDatabase() {
		SalesDatabase salesdatabase = new SalesDatabase();
		return salesdatabase;
	}



	private static ProductDatabase loadProductDatabase()  {
		ProductDatabase productdatabase = new ProductDatabase();

		productdatabase.addProduct(new CoffeeAccessary("A001", "Almond Flavored Syrup", 9.00));
		productdatabase.addProduct(new CoffeeAccessary("A005", "Gourmet Coffee Cokkies", 12.00));
		productdatabase.addProduct(new CoffeeAccessary("A007", "Gourmet Coffee Ceramic Mug", 8.00));
		productdatabase.addProduct(new CoffeeAccessary("A009", "Gourmet Coffee 36 Cup Filters", 45.00));

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

		productdatabase.addProduct(new Coffee("C001", "Colombia,Whole,1 lb", 17.99,
				"Colombia", "Medium","Rich and Hearty","Rich","Medium","Full"));
		productdatabase.addProduct(new Coffee("C002", "Colombia,Ground,1 lb", 18.75,
				"Colombia", "Medium","Rich and Hearty","Rich","Medium","Full"));
		productdatabase.addProduct(new Coffee("C007", "Guatemala,Whole,1 lb", 17.99,
				"Guatemala", "Medium","Rich and complex","spicy","Medium to high","Medium to Full"));
		productdatabase.addProduct(new Coffee("C008", "Guatemala,Ground,1 lb", 18.75,
				"Guatemala", "Medium","Rich and complex","Spicy","Medium to high","Medium to Full"));

		return productdatabase;
	}

	/**
	 * Initialize the product and the sales database
	 * @param initialproduct
	 * @param initialSalesdatabase
	 */
	private GourmetCoffeeSystem(ProductDatabase initialproduct,SalesDatabase initialSalesdatabase) {

		this.productdatabase= initialproduct;
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
				pauseEnter();
			} else if (choice == 2)  {
				displayProductItem();
				pauseEnter();
			} else if (choice == 3)  {
				displayCurrentOrder();
				pauseEnter();
			} else if (choice == 4)  {
				addProduct();
				pauseEnter();
			} else if (choice == 5)  {
				removeProduct();
				pauseEnter();
			} else if (choice == 6)  {
				registerNewOrder();
				pauseEnter();
			}else if (choice == 7)  {
				displaySales();
				pauseEnter();
			}else if (choice ==8)   {
				displayOrdersNumToProduct();
				pauseEnter();
			}else if (choice ==9)   {
				displayQuantityToProducts();
				pauseEnter();
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
						+ "[6]  Register new order\n"
						+ "[7]  Display sales\n"
						+ "[8]  Display number of orders with a specific product\n"
						+ "[9]  Display the total quantity sold for each products\n"
						+ "choice> ");
				stdOut.flush();

				input = Integer.parseInt(stdIn.readLine());
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

		int numberOfItems = this.productdatabase.getNumberOfItems();

		if (numberOfItems == 0) {
			stdErr.println("The product is empty");
		} else {
			for (Iterator<ProductItem> i = productdatabase.getProductsIterator();
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
	private void displayCurrentOrder() throws IOException {
		Order order = null;
		if (salesdatabase.getNumberOfOrders() == 0) {
			stdErr.println("The database of order is empty");
			return;
		} else {
			order = (Order) salesdatabase.getOrder(salesdatabase.getNumberOfOrders()-1);
		}
		stdOut.println("OrderCode:"+order.getOrderCode()+"\nQuantity\tCode\tDescription\tPrice");
		for (Iterator<SalesItem> i = order.getSalesItemIterator();i.hasNext();) {
			SalesItem salesitem = (SalesItem) i.next();
			stdOut.println(salesitem.toString()+'\t'+salesitem.getProductItem().toString2());
		}
		stdOut.println("Order total:\t"+order.toString());
	}

	/*
	 * add a product to order
	 */
	private void addProduct()  throws IOException {
		Order order =null;
		if (salesdatabase.getNumberOfOrders() == 0) {
			stdErr.println("The database of order is empty");
			return;
		} else {
			order = (Order) salesdatabase.getOrder(salesdatabase.getNumberOfOrders()-1);
		}
		ProductItem item = readProductItem();
		Scanner input=new Scanner(System.in);

		if (item==null) {
			stdErr.println("There is no product item with that code");
		} else if (item.isAvailable()) {

			if(item.isAvailable()==false) {
				stdErr.println("There is no enough items");
			}else {
				stdOut.println("The quantity of the item:");

				int n=input.nextInt();
				((SalesItem) order.getSalesItemIterator()).setQuantity(n);
				order.addSalesItem(item.getCode(),productdatabase,salesdatabase);
				stdOut.println("The item " + item.getCode()
				+ " has been added to the order " );
			}

		}	
		input.close();
	}	


	/*
	 * remove a product from order
	 */
	private void removeProduct()  throws IOException  {
		Order order =null;
		if (salesdatabase.getNumberOfOrders() == 0) {
			stdErr.println("The database of order is empty");
			return;
		} else {
			order = (Order) salesdatabase.getOrder(salesdatabase.getNumberOfOrders()-1);
		}

		ProductItem item = readProductItem();

		Scanner input=new Scanner(System.in);
		if (item == null) {
			stdErr.println("There is no product item with that code");
		} else if (item.isAvailable()) {


			if(((SalesItem) order.getSalesItemIterator()).getQuantity()!=0) {
				order.deleteSalesItem(item.getCode(),salesdatabase);
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
	private void registerNewOrder() throws IOException{
		Order order = new Order(salesdatabase.getNumberOfOrders());
		salesdatabase.addOrder(order);

		stdOut.println("A new order has been created");
	}



	/*
	 * lists all the orders that have been sold
	 */
	private void displaySales()throws IOException {
		Order order = null;
		if (salesdatabase.getNumberOfOrders() == 0) {
			stdErr.println("The database of order is empty");
			return;
		}
		for (Iterator<Order> i = salesdatabase.getOrdersIterator();
				i.hasNext(); ) {
			order = (Order) i.next();

			System.out.println("Order code: " + order.getOrderCode());
			stdOut.println("Quantity\tCode\tDescription\tPrice");
			for (Iterator<SalesItem> k = order.getSalesItemIterator(); k.hasNext(); ) {
				SalesItem salesItem = (SalesItem) k.next();
				stdOut.println(salesItem.toString() + '\t' + salesItem.getProductItem().getCode() + '\t'
						+ salesItem.getProductItem().getDescription() + '\t' + salesItem.getProductItem().getPrice());
			}

			stdOut.println("Total:\t"+order.getTotal()+"\n");
		}
	}

	/*
	 * 	Display number of orders with a specific product
	 */
	private void displayQuantityToProducts()throws IOException{
		HashMap<String,Integer> quantityToProductsLog = salesdatabase.getQuantityToProductsLog();
		stdOut.println("Code\tDescription\tQuantity");
		for (Map.Entry<String,Integer> ha : quantityToProductsLog.entrySet()){
			String code = (String) ha.getKey();
			String descrip=productdatabase.getProduct(code).getDescription();
			stdOut.println(code+"\t"+descrip+"\t"+ha.getValue()+"\n");

		}
	}

	/*
	 * Display the total quantity sold for each products
	 */
	private void displayOrdersNumToProduct()throws IOException{
		ProductItem item = readProductItem();
		int number=0;
		if (item==null) {
			stdErr.println("There is no Order with that code of product");
			return;
		}else {
			String codeOfPruduct = item.getCode();
			HashMap<String,Integer> ordersNumToProduct = salesdatabase.getOrdersNumToProductsLog();
			for (Map.Entry<String,Integer> ha : ordersNumToProduct.entrySet()){
				if(ha.getKey()==codeOfPruduct){
					stdOut.print(ha.getValue()+"  ");
					number++;
				}
			}
			System.out.println("The number of the orders of "+codeOfPruduct+" is "+number);
		}
	}

	private  ProductItem readProductItem() throws IOException  {

		stdOut.print("Product item code> ");
		stdOut.flush();

		return this.productdatabase.getProduct(stdIn.readLine());
	}

	public static void pauseEnter() throws IOException  { //Enter to continue
		System.out.println("Press any key to continue...");
		new BufferedReader(new InputStreamReader(System.in)).readLine();
	}


}
