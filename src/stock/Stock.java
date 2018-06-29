package stock;

import java.util.UUID;

public class Stock {
	private String _tickerSymbol;
	private String _name;
	private String _description;
	private double _outstandingShares;
	private double _initialPricePerShare;
	private UUID _id;
	
	public Stock(String tickerSymbol, String name, double sharesIssued, double pricePerShare) {
		Init(tickerSymbol, name, null, sharesIssued, pricePerShare);
	}
	
	public Stock(String tickerSymbol, String name, String description, double sharesIssued, double pricePerShare) {
		Init(tickerSymbol, name, description, sharesIssued, pricePerShare);
	}
	
	private void Init(String tickerSymbol, String name, String description, double sharesIssued, double pricePerShare) {
		_tickerSymbol = tickerSymbol;
		_name = name;
		_description = null;
		_outstandingShares = sharesIssued;
		_initialPricePerShare = pricePerShare;
		_id = UUID.randomUUID();
	}
	
	public String GetSymbol() {
		return _tickerSymbol;
	}
	
	public String GetName() {
		return _name;
	}
	
	public String GetDescription() {
		return _description;
	}
	
	public double OutstandingShares() {
		return _outstandingShares;
	}
	
	public double InitialPricePerShare() {
		return _initialPricePerShare;
	}
	
	public UUID GetId() {
		return _id;
	}
}
