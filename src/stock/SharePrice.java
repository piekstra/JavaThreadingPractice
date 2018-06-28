package stock;

import java.util.UUID;

public class SharePrice {
	private Order _bid;
	private Order _ask;
	private Order _last;
	private UUID _stockId;
	
	public SharePrice(UUID stockId, Order bid, Order ask, Order last) {
		_stockId = stockId;
		SetBid(bid);
		SetAsk(bid);
		SetLast(bid);
	}
	
	public UUID GetStockId() {
		return _stockId;
	}
	
	public Order GetBid() {
		return _bid;
	}
	
	public void SetBid(Order bid) {
		_bid = bid;
	}
	
	public Order GetAsk() {
		return _ask;
	}
	
	public void SetAsk(Order ask) {
		_ask = ask;
	}
	
	public Order GetLast() {
		return _last;
	}
	
	public void SetLast(Order last) {
		_last = last;
	}
}
