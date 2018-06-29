package stock;

import java.util.UUID;

public class Position {
	
	private Stock _stock;
	
	private double _shares;
	
	private double _averagePurchasePrice;
	
	private UUID _id;
	
	public Position(Stock stock, double shares, double pricePaidPerShare) {
		_stock = stock;
		_shares = shares;
		_averagePurchasePrice = pricePaidPerShare;
		_id = UUID.randomUUID();
	}
	
	public Stock GetStockInfo() {
		return _stock;
	}
	
	public double GetSharecount() {
		return _shares;
	}
	
	public double GetAveragePricePerShare() {
		return _averagePurchasePrice;
	}

	public void AddShares(double shares, double pricePerShare) {
		
		double originalTotal = _shares * _averagePurchasePrice;
		
		_shares += shares;
		_averagePurchasePrice = (originalTotal + shares * pricePerShare) / _shares;
	}
	
	public UUID GetId() {
		return _id;
	}
}
