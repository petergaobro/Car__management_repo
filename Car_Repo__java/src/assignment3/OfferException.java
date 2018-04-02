package assignment3;


/*
 * class OfferException
 * Represent an issue that occurs when 
 * attempting to make an offer for a Sale
 */
public class OfferException extends Exception{

	public OfferException(String message){
		super(message);
	}
}
