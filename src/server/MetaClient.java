package server;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import stock.Order;
import stock.Position;

public class MetaClient {
	private double _capital;
	
	/**
	 * The key should be the Id of the stock corresponding to the position
	 */
	private static Map<UUID, Position> _positions = new HashMap<UUID, Position>();
	protected final LinkedBlockingQueue<Order> _orders = new LinkedBlockingQueue<Order>();
	private final UUID _id = UUID.randomUUID();

	public MetaClient(double capital) {
		_capital = capital;
	}
	
	public boolean AddPosition(Position position) {
		if (position == null) {
			return false;
		}
		
		Position currentPosition = _positions.get(position.GetStockInfo().GetId());
		
		if (currentPosition == null) {
			// Start new position
			_positions.put(position.GetId(), position);
		} else {
			// Extend existing position
			currentPosition.AddShares(position.GetSharecount(), position.GetAveragePricePerShare());
		}
		
		return true;
	}
	
	public Map<UUID, Position> GetPositions() {
		return _positions;
	}
	
	public double GetCapital() {
		return _capital;
	}
	
	public UUID GetId() {
		return _id;
	}
}
