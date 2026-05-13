// Tool Imports
import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

// Classes
class CoffeeShop
{
    File prices = new File("prices.txt");
    Scanner input = new Scanner(System.in);
    Scanner reader = new Scanner(prices);
    double discount = 0;
    int discountStatus = 0;
    int drink = 0;
    double price = 0;

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

    void order() throws FileNotFoundException
    {
        System.out.println(" ");
        System.out.println("~~~Menu~~~");
        int i =1;
        for (reader.hasNextLine(); i <= 5; i++)
        {
            String line = reader.nextLine();
            System.out.println(i + ". " + line);
        }
        System.out.print("Please enter drink selection (1-5): ");
        int drinkOption = input.nextInt();
        switch(drinkOption) {
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
        System.out.println("Order Summary: ");
        try {
            String selectedLine = Files.readAllLines(Paths.get("prices.txt")).get(drink-1);
            System.out.println("Drink Ordered: " + selectedLine);
        } catch (IOException e) {
            System.out.println("Error Found. Please Try Again.");
        }
        System.out.println("Milk Type: " + milk);

        System.out.println("Subtotal Cost with Upgrades: " + price);

        System.out.println("Discount: " + discount);
        price = (1-discount)*price;

        System.out.println("Final Total: " + price);

    }

    void saveOrder()
    {

    }

    void reverseSearch()
    {

    }

// End Class
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException
    {
        CoffeeShop c1 = new CoffeeShop();
        c1.beginOrder();
        c1.order();
        c1.customize();
        c1.orderSummary();




    }
}