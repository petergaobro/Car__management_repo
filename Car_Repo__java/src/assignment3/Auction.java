package assignment3;

public class Auction extends Sale{
	
	//stage-3-A--instance variables
	private String highestBidder;
	
	
	
	//stage-3-B--constructor
	public Auction(String saleID, String propertyAddress, int reservePrice){
			
			//use superclass constructor
		super(saleID, propertyAddress, reservePrice);
		this.highestBidder = "NO BIDS PLACED";
	}
	
	
	
	//Only used for rebuild object from file
	public Auction(String saleID, String propertyAddress, int reservePrice, 
            int currentOffer, boolean acceptingOffers, String highestBidder){
		
        super(saleID, propertyAddress, reservePrice, currentOffer, acceptingOffers);
        this.highestBidder = highestBidder;
    }
	
	
	
	//Accessors
	public String getHighestBidder(){
		return highestBidder;
	}
	
	
	
	//stage-3-C--Override the method-getPropertyStatus()
	//T---if property allow offer---return "ACCEPTING BIDS"
	//F---if property was not sold---return "PASSED IN"
	//F---if property is sold---return "SOLD"
	@Override
	public String getPropertyStatus(){
		if(isAcceptingOffers()){
			return "ACCEPTING BIDS";
		}
		else if(getCurrentOffer() < getReservePrice()){
			return "PASSED IN";
		}
		else{
			return "SOLD";
		}
	} 
	
	
	
	//stage-3-D--implement a new method
	//update an Auction to be closed, no longer accept bids
	public boolean closeAuction(){
		if(!isAcceptingOffers()){
			return false;
		}
		setAcceptingOffers(false);
		return true;
	}
	
	
	
	//stage-3-E
	/*The processes an offer(bid) made for the Auction 
	in the following manner.*/
	public void makeBid(String bidder, int offerPrice) throws OfferException{
		
		if(!isAcceptingOffers()){
			throw new OfferException("Offer is closed!");
		}
		if(offerPrice <= getCurrentOffer()){
			throw new OfferException("Offer price should larger than current offer price"
									+ "[" + getCurrentOffer() + "]");
		}
		
		//makeOffer()
		makeOffer(offerPrice);
		setAcceptingOffers(true);
		highestBidder = highestBidder;	
	}
	
	
	
	//stage-3-F--override the method printDetails()
	@Override
	public void printDetails(){
		super.printDetails();
		System.out.printf("%20s %s\n", "Highest bidder: " , highestBidder);
	}
}