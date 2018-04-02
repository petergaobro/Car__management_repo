package assignment3;

import java.util.*;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/*
 * class RealEstateSystem
 *
 * Written by: Craig Hamilton, Daryl D'Souza, Ross Nye
 *
 * Student #1
 * Name: Peng Gao
 * Student number: s3457558
 *
 * Student #2
 * Name: Yuze Li
 * Student number: s3482460
 *
 * Implements the data storage and functional requirements for a menu-driven
 * program that manages property sales.
 *
 * This is the start-up code for Assignment 3 and you should work off this
 * program - the features described in the specification should be implemented
 * in the corresponding helper methods included at the bottom of this class.
 *
 * You are welcome to add other helper methods as you see fit, please add them
 * at the end of this class. However, please DO NOT CHANGE the existing method
 * signatures [eg. private static void addNewSale()]  or the any of the
 * code inside the main method.
 */

// Refer to comment above BEFORE MAKING ANY CHANGES to this class!

import java.util.Scanner;

public class RealEstateSystem {
	/*
	 * You can refer to the array and Scanner object here anywhere within this
	 * class, even within helper methods that you are required to implement the
	 * code for each of the features within
	 */
	
	
	//private static final List<Sale> sales = new ArrayList<>();
	
	
	private static final Sale[] sales = new Sale[100];
	private static int saleCount = 0;
    
	private static final Scanner sc = new Scanner(System.in);
    
	public static void main(String[] args)
	{
		// DO NOT CHANGE any code main method!
        
		startup();
		
		// declare variables used for menu
		String userInput;
		char selection = 0;
        
		// implementation of the program menu
		do
		{
            
			// print menu to screen
			System.out.println("*** Real Estate System Menu ***");
			System.out.println();
            
			System.out.printf("%-25s%s\n", "Display Sales Summary", "A");
			System.out.printf("%-25s%s\n", "Add New Sale", "B");
			System.out.printf("%-25s%s\n", "Submit Offer", "C");
			System.out.printf("%-25s%s\n", "Add New Auction", "D");
			System.out.printf("%-25s%s\n", "Close Auction", "E");
			System.out.printf("%-25s%s\n", "Exit Program", "X");
			System.out.println();
            
			// prompt user to enter selection
			System.out.print("Enter selection: ");
			userInput = sc.nextLine();
            
			System.out.println();
            
			// validate selection input length
			if(userInput.length() != 1)
			{
				System.out.println("Error - invalid selection!");
			}
			else
			{
				// make selection "case insensitive"
				selection = Character.toUpperCase(userInput.charAt(0));
                
				// process user's selection
				switch(selection)
				{
					case 'A':
						displaySalesSummary();
                        break;
                        
					case 'B':
						addNewSale();
						break;
                        
					case 'C':
						submitOffer();
						break;
                        
					case 'D':
						addNewAuction();
						break;
                        
					case 'E':
						closeAuction();
						break;
                        
					case 'X':
						exit();
						break;
                        
					default:
						System.out.println("Error - invalid selection!");
				}
			}
			System.out.println();
            
		} while(selection != 'X');
        
	}
	
	// DO NOT CHANGE the method signatures below (only add code to their bodies)
    
	private static void startup()
	{
		// You can add some Sales/Auction objects to the
		// sales array and update salesCount for easier testing of your program.
		
		// IF ATTEMPTED add code for Bonus Marks restoring Sales to file here
		// (and remove any test Sales/Auction object you may have added above
		
        //sales[0] = new Sale ("grd", "grgr",3,3,3); 
		
		
		//save the file--"SaleData.txt"
		String line;
		
		try(Scanner sc = new Scanner(new File("Database.txt"))){
			while(sc.hasNextLine()){
				line = sc.nextLine();
			}
		} 
		catch (IOException e){
			System.out.println("No sale data was load!");
		}
	}
    
	private static void exit()
	{
		System.out.println("Exiting the program...");
        
		// IF ATTEMPTED add code for Bonus Marks saving Sales to file here
	}
  
	
	
	
	
	private static Sale getSale(String id)
	{
		// This could be a handy method that searches the sales array for an
		// Sale with an ID matching the parameter value (id). If such an
		// object is located the method returns a reference to the matching
		// Sale object, otherwise it should return null. This method could be
		// invoked in any of the tasks that involve searching for and locating
		// an object with a given ID (or the absence of one).
		
		//Search for each Sale
		//Make sure each sale was matched
		for(int i = 0; i < saleCount; i++){
			if (sales[i].getSaleID().equals(id))
            {
                // return matched Sale
                return sales[i];
            }
		}
		return null;
	}
    
	
	
	private static void displaySalesSummary()
	{
		// implement your code for Stage 2 Requirement A) here
		System.out.println("Display Sales Summary option selected");
		
		//Invoke printDetails() through for loop with array
		for(int i = 0; i < saleCount; i++){
			sales[i].printDetails();
		}
		System.out.println();
	}
	
	
	
    private static void addNewSale()
	{
		// implement your code for Stage 2 Requirement B) here
    	//Locates a sale by the user
    	//Display the detail on the screen
		System.out.println("Add New Sale option selected");
		
		System.out.print("Please enter the name of the sale: ");
		String saleID = sc.nextLine();
		
		//Make sure the sale ID which was entered 
		//by user with duplicate sale ID
		Sale oldSale = getSale(saleID);
		if(oldSale != null){
			System.out.println("Validate duplicate sale ID.");
			return;
		}
		
		System.out.print("Please enter the address of the property: ");
		String propertyAddress = sc.nextLine();
		
		System.out.print("Please enter the price of the reserve: ");
		int reservePrice = Integer.parseInt(sc.nextLine());
		
		//Add the user inputs into the sales array list
		sales[saleCount] = new Sale(saleID, propertyAddress, reservePrice);
		saleCount++;
		
		System.out.println();
        
	}
    
    	
    
	private static void submitOffer()
	{
		
		// implement your code for Stage 2 Requirement C) here
		System.out.println("Submit Offer option selected");
		
		System.out.print("Please enter a sale ID: ");
		String saleID = sc.nextLine();
		Sale sale = getSale(saleID);
		
		//Check saleID is exist or not
		if(sale == null){    
			System.out.println("Sale ID is not found!");
			return;
		}
		
		//Check saleID is accepted offers or not
		if(!sale.isAcceptingOffers()){
			System.out.println("Sale ID is not accepted offers.");
		}
		
		//Enter____offer price
		System.out.println("Please enter the offer price: ");
		//Integer.parseInt: change String to "int" value.
		int offerPrice = Integer.parseInt(sc.nextLine());
		
		//Invoke the makeOffer()
		try{
			sale.makeOffer(offerPrice);
			System.out.println("Offer accepted.");
			
			if(sale.getCurrentOffer() >= sale.getReservePrice()){
				System.out.println("Offer met/exceed reserve price.");
			}
			else{
				System.out.println("Offer was not below the reserve price");
			}
		}
		catch(Exception e){
			System.out.println("Offer rejected: " + e.getMessage());
		}
		System.out.println();
	}
    

	
	private static void addNewAuction()
	{
		// implement your code for Stage 4 Requirement A) here
		System.out.println("Add New Auction option selected");
		
		System.out.print("Please enter the name of the sale: ");
		String saleID = sc.nextLine();

		Sale oldSale = getSale(saleID);
		if(oldSale != null){
			System.out.println("Validate duplicate sale ID.");
			return;
		}
	 
		System.out.print("Please enter the address of the property: ");
		String propertyAddress = sc.nextLine();
		
		System.out.print("Please enter the price of the reserve: ");
		int reservePrice = Integer.parseInt(sc.nextLine());
		
		//Add the user inputs into the sales array list
		sales[saleCount] = new Auction(saleID, propertyAddress, reservePrice);
		saleCount++;
		
		System.out.println();
	}

	
	
	private static void closeAuction()
	{
		// implement your code for Stage 4 Requirement B) here
		System.out.println("Close Auction option selected");
		
		System.out.print("Please enter a sale ID: ");
		String saleID = sc.nextLine();
		Sale sale = getSale(saleID);
		
		//if not matching - display error
		if(sale == null){
			System.out.print("Invalid sale ID!");
			return;
		}
		
	    // if the object is a Auction - return result and show correct
		if(!(sale instanceof Auction)){
			System.out.println("The sale was not an auction.");
			return;
		}
		
		//Calling closeAuction()
		Auction acution = (Auction) sale;
		if(acution.closeAuction()){
			System.out.println("Auction close succeed!");
		}
		else{
			System.out.println("Auction close failed!");
		}
		System.out.println();
	}
	// Add your own helper methods here (optional)
}