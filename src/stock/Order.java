package stock;

import java.util.Comparator;
import java.util.UUID;

public class Order implements Comparator<Order>{	
	// Price per share
	private double _pps;	
	// Volume
	private double _vol;
	
	private OrderType _type;
	
	private OrderStatus _status;
	
	private Stock _stock;
	
	private UUID _id;
	
	public Order(Stock stock, double pricePerShare, double volume, OrderType orderType) {
		_stock = stock;
		_pps = pricePerShare;
		_vol = volume;		
		_type = orderType;
		_status = OrderStatus.Open;
		_id = UUID.randomUUID();
	}
	
	public Stock GetStock() {
		return _stock;
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
	
	public OrderStatus GetOrderStatus() {
		return _status;
	}
	
	public UUID GetId() {
		return _id;
	}

	@Override
	public int compare(Order order1, Order order2) {
		if (_type == OrderType.LimitBuy || _type == OrderType.MarketBuy) {
			return 
		}
		return 0;
	}
}
