package stock;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Portfolio {

	/**
	 * The key should be the UUID of the Stock
	 */
	private final Map<UUID, Stock> _stocks = new HashMap<UUID, Stock>();

	/**
	 * The key should be the UUID of a Stock
	 */
	private final Map<UUID, Position> _positions = new HashMap<UUID, Position>();

	/**
	 * The key should be the UUID of a Stock
	 */
	private final Map<UUID, List<Order>> _orderHistory = new HashMap<UUID, List<Order>>();
		
	public Map<UUID, Stock> GetStocks(){
		return _stocks;		
	}
	
	public Map<UUID, Position> GetPositions(){
		return _positions;		
	}
	
	public Map<UUID, List<Order>> GetFullOrderHistory(){
		return _orderHistory;		
	}
	
	public List<Order> GetOrderHistoryForStock(Stock stock){
		return _orderHistory.get(stock.GetId());		
	}
	
	public boolean AddOrder(Order order){
		if (order == null) {
			return false;
		}
		
		Stock orderStock = order.GetStock();
		UUID orderStockId = orderStock.GetId();
		Stock existingStock =_stocks.get(orderStockId);
		
		if (existingStock == null) {
			_stocks.put(orderStockId, orderStock);
		}
		
		Position existingPosition = _positions.get(orderStockId);
		if (existingPosition == null) {
			existingPosition = new Position(existingStock, order.GetVolume(), order.GetPricePerShare());
			_positions.put(orderStockId, existingPosition);
		} else {
			existingPosition.AddShares(order.GetVolume(), order.GetPricePerShare());
		}
		
		List<Order> existingHistory = _orderHistory.get(orderStockId);
		if (_orderHistory == null) {
			existingHistory = Collections.singletonList(order);
		} else {
			existingHistory.add(order);
		}
		
		return true;		
	}
}
