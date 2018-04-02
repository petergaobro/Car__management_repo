package assignment3;

import java.io.*;
import java.util.Scanner;

/*
 * class Sale
 *
 * Written by: Craig Hamilton, Daryl D'Souza, Ross Nye
 *
 * Encapsulates the attribute detail and functionality required to
 * record and manage "standard" property sales in the real estate
 * as described in Stage 1 of the specification.
 *
 * You should leave this class as-is while implementing the requirements
 * in stages 2-4, but you can/will need to amend this class for stage 5
 * (exception handling) and the bonus marks stage (file handling) to
 * facilitate the addressing of the requirements set out in those stages.
 *
 * In particular you shouldn't make any changes to the instance variables or
 * method signatures or their visibility!!
 */

// DO NOT MAKE CHANGES TO THIS CLASS except for those outlined above!

public class Sale {
	private String saleID;
	private String propertyAddress;
	private int reservePrice;
	private int currentOffer;
	private boolean acceptingOffers;
    
	public Sale(String saleID, String propertyAddress, int reservePrice)
	{
		this.saleID = saleID;
		this.propertyAddress = propertyAddress;
		this.reservePrice = reservePrice;
        
		// initialise sale status to true to reflect the sale being "open"
		// initially
		this.acceptingOffers = true;
	}
    
	
	
	//Only used for rebuild object from file
	public Sale(String saleID, String propertyAddress, int reservePrice, 
            int currentOffer, boolean acceptingOffers) 
    {
        this.saleID = saleID;
        this.propertyAddress = propertyAddress;
        this.reservePrice = reservePrice;
        this.currentOffer = currentOffer;
        this.acceptingOffers = acceptingOffers;
    }
	
	
	
	/*
	 * Accessors (getters)
	 *
	 * Use these as needed in latter stages of the assignment.
	 */
	public String getSaleID()
	{
		return saleID;
	}
    
	public String getPropertyAddress()
	{
		return propertyAddress;
	}
    
	public int getReservePrice()
	{
		return reservePrice;
	}
    
	public int getCurrentOffer()
	{
		return currentOffer;
	}
    
	public boolean isAcceptingOffers()
	{
		return acceptingOffers;
	}
    
	/*
	 * Mutators (setters)
	 *
	 * This is the only mutator that is required for stages 2-5 of the
	 * assignment, but you may define additional mutators for the
	 * bonus marks stage (file handling) as needed, or perhaps a single method
	 * that makes all such required changes.
	 */
	public void setAcceptingOffers(boolean acceptingOffers)
	{
		this.acceptingOffers = acceptingOffers;
	}
    
	/*
	 * getPropertyStatus()
	 *
	 * Returns the current state of this Sale in String form - "ON SALE" when
	 * Sale is currently open and "SOLD" when it has been closed.
	 */
	public String getPropertyStatus()
	{
		// Sale is currently open so property is still on sale
		if(this.acceptingOffers == true)
		{
			return "ON SALE";
		}
		else
		{
			// sale is closed so property has been sold
			return "SOLD";
		}
	}
    
	/*
	 * makeOffer()
	 *
	 * Operation which accepts a new offer as a parameter and updates the
	 * current highest offer if the new offer is higher.
	 *
	 * Returns false (indicating that the offer has been rejected) if the Sale
	 * is currently closed or if the new offer is not higher than the current
	 * highest offer, otherwise returns true (to indicate that the new offer
	 * has been accepted / recorded).
	 */
	public boolean makeOffer(int offer)
	{
		// reject offer if sale isn't open for offers (acceptingOffers == false)
		// OR reject offer if it is not higher than the current highest offer
		if(!this.acceptingOffers || offer <= this.currentOffer)
		{
			return false;
		}
        
		// new offer is valid so update current highest offer
		this.currentOffer = offer;
        
		// check to see if reserve price has been reached or exceeded
		if(offer >= this.reservePrice)
		{
			// close the Sale if reserve has been met
			this.acceptingOffers = false;
		}
		return true;
	}
    
	/*
	 * printDetails()
	 *
	 * Prints the instance variable details for this Sale to the screen, as well
	 * as the "outcome" of the sale.
	 */
	public void printDetails()
	{
		System.out.println();
		System.out.printf("%20s %s\n", "Sale ID:", this.saleID);
		System.out.printf("%20s %s\n", "Property Address:", this.propertyAddress);
		System.out.printf("%20s $%d\n", "Reserve Price:", this.reservePrice);
		System.out.printf("%20s $%d\n", "Current Offer:", this.currentOffer);
		System.out.printf("%20s %b\n", "Accepting Offers:", this.acceptingOffers);
		System.out.printf("%20s %s\n", "Property Status:",
                          this.getPropertyStatus());
		System.out.println("\n");
	}
}
