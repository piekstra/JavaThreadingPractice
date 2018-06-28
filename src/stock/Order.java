package stock;

import java.util.UUID;

public class Order {	
	// Price per share
	private double _pps;	
	// Volume
	private double _vol;
	
	private OrderType _type;
	
	private UUID _id;
	
	public Order(double pricePerShare, double volume, OrderType orderType) {
		_pps = pricePerShare;
		_vol = volume;
		_type = orderType;
		_id = UUID.randomUUID();
	}
	
	public double GetPricePerShare() {
		return _pps;
	}
	
	public double GetVolume() {
		return _vol;
	}
	
	public OrderType GetType() {
		return _type;
	}
	
	public UUID GetId() {
		return _id;
	}
}
