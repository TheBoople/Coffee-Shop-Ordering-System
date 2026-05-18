// Tool Imports
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

// Classes
class CoffeeShop
{
   File prices = new File("Prices.txt");
/*

Vanilla Latte $4.85
Caramel Macchiato $6.30
Americano $3.50
Hazelnut Latte $4.95
Vanilla Bean Creme $7.00

 */
    Scanner input = new Scanner(System.in);
    Scanner priceReader = new Scanner(prices);

    String customerName = "N/A";
    double discount = 0;
    int discountStatus = 0;
    int drink = 0;
    double price = 0;
    String selectedLine = "N/A";

    // Customizations
    String size = "Regular";
    String milk = "2%";


    CoffeeShop() throws FileNotFoundException {
    }

    // 1 = student, 10%
    // 2 = veteran, 15%
    // 3 = member, 20%
    void beginOrder() {
        System.out.println(" ");
        System.out.println("Welcome to Java190 Coffee!");
        System.out.println("Are you a Student? Y/N");
        String status = input.nextLine();
        if (status.equalsIgnoreCase("Y"))
        { discountStatus = 1; }
        System.out.println("Are you Active Military or Veteran? Y/N");
        status = input.nextLine();
        if (status.equalsIgnoreCase("Y"))
        { discountStatus = 2; }
        System.out.println("Are you a Premium Member? Y/N");
        status = input.nextLine();
        if (status.equalsIgnoreCase("Y"))
        { discountStatus = 3;
            System.out.print("Please enter Member ID: ");
            int r = input.nextInt(); }

        switch(discountStatus)
        {
            case 1: System.out.println("Welcome Student: Enjoy a 10% discount"); discount = 0.10; break;
            case 2: System.out.println("Welcome Honored Serviceman: Enjoy a 15% discount"); discount = 0.15; break;
            case 3: System.out.println("Welcome Rewards Member: Enjoy a 20% discount"); discount = 0.20; break;
            default: System.out.println("No Discount Applied");
        }
    }

    void order()
    {
        System.out.println(" ");
        System.out.println("~~~Menu~~~");
        int i =1;
        for (priceReader.hasNextLine(); i <= 5; i++)
        {
           String line = priceReader.nextLine();
           System.out.println(i + ". " + line);
        }
        System.out.print("Please enter drink selection (1-5): ");
        int drinkOption = input.nextInt();
        switch(drinkOption)
        {
            case 1: { System.out.println("Thank you for choosing: Vanilla Latte"); drink = 1; price = 4.85; break; }
            case 2: { System.out.println("Thank you for choosing: Caramel Macchiato"); drink = 2; price = 6.30; break; }
            case 3: { System.out.println("Thank you for choosing: Americano"); drink = 3; price = 3.50; break; }
            case 4: { System.out.println("Thank you for choosing: Hazelnut latte"); drink = 4; price = 4.95; break; }
            case 5: { System.out.println("Thank you for choosing: Vanilla Bean Creme"); drink = 5; price = 7.00; break; }
            default: System.out.println("That is not a drink option.");
        }
    }

    void customize()
    {
        System.out.println(" ");
        System.out.print("Would you like that as Regular or Large for + $1?: ");
        size = input.next();

        if (size.equalsIgnoreCase("Large"))
        {
            price += 1;
        }
        System.out.println("Current Price: " + price);
        System.out.println(" ");
        System.out.println("Would you like to change your milk preference? Y/N");
        String changePref = input.next();

        if (changePref.equalsIgnoreCase("Y")) {
            System.out.println("Please input desired milk type");
            milk = input.next();
            System.out.println("Milk Preference: " + milk);
            price +=1;

        }
        System.out.println("Current Price: " + price);

    }

    void orderSummary()
    {
        System.out.println(" ");
        System.out.println("~~Order Summary~~");
        try {
            selectedLine = Files.readAllLines(Paths.get("Prices.txt")).get(drink-1);
            System.out.println("Drink Ordered: " + selectedLine);
        } catch (IOException e) {
            System.out.println("Error Found. Please Try Again.");
        }
        System.out.println("Milk Type: " + milk);

        System.out.println("Subtotal Cost with Upgrades: " + price);

        System.out.println("Discount: " + discount);
        price = (1-discount)*price;

        System.out.println("Final Total: " + price + "\n");

    }

    void orderName()
    {
        System.out.print("Enter Name for Order: ");
        customerName = input.next();
        System.out.println("Thank you for ordering \"" + customerName + "\"\n");
    }

    void saveOrder() throws IOException
    {
            FileWriter writer = new FileWriter("Receipt.txt");
            writer.write(customerName + "\n");
            writer.write(selectedLine + "\n");
            writer.write("Milk Type: " + milk + "\n");
            writer.write("Customer Discount: " + discount * 100 + "%" + "\n");
            writer.write("Total: " + price + "\n");
            writer.close();
    }

    void reverseSearch()
    {
// The Cancer Function
        try (BufferedReader reverseSearch = new BufferedReader(new FileReader("Receipt.txt"));)
        {
            int i = 0;
            System.out.print("Please enter order name (case sensitive): ");
            String find = input.next();
            String line;
            while ((line = reverseSearch.readLine()) != null)
            {
                if (line.contains(find))
                {
                    System.out.println("Order Found \n");
                while (i<=3)
                    {
                        System.out.println(reverseSearch.readLine());
                        i++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No Order Found");
        } catch (IOException e)
        {
            System.out.println("Invalid Operation");
        }
    }
// End Class
}

public class Main {
    public static void main(String[] args) throws IOException
    {
        CoffeeShop c1 = new CoffeeShop();
        c1.beginOrder();
        c1.order();
        c1.customize();
        c1.orderSummary();
        c1.orderName();
        c1.saveOrder();
        c1.reverseSearch();

    }
}
