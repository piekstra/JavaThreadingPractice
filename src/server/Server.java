package server;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import client.Client;
import stock.Order;
import stock.OrderType;
import stock.Position;
import stock.SharePrice;
import stock.Stock;

public class Server {

	/**
	 * The key should be the id of the Stock
	 * A map of all registered stocks
	 */
	protected final Map<UUID, Stock> _stocks = new HashMap<UUID, Stock>();
	
	/**
	 * The key should be the id of the stock to which the SharePrice corresponds
	 */
	protected final Map<UUID, SharePrice> _sharePrices = new HashMap<UUID, SharePrice>();
	
	/**
	 * The key should be the id of the stock to which the Order corresponds
	 */
	protected final Map<UUID, PriorityQueue<Order>> _placedBuyOrders = new HashMap<UUID, PriorityQueue<Order>>();
	
	/**
	 * The key should be the id of the stock to which the Order corresponds
	 */
	protected final Map<UUID, PriorityQueue<Order>> _placedSellOrders = new HashMap<UUID, PriorityQueue<Order>>();
	protected final LinkedBlockingQueue<Order> _newOrderQueue = new LinkedBlockingQueue<Order>();
	
	/**
	 * The key should be the id of the MetaClient
	 */
	protected final Map<UUID, MetaClient> _clients = new HashMap<UUID, MetaClient>();
	
	public Map<UUID, Stock> GetSupportedStocks() {
		return _stocks;
	}
			
	/** 
	 * @param clientId The id of the MetaClient
	 * @param order
	 * @return true if successfully created the order
	 */
	public boolean RequestQueueOrder(UUID clientId, Order order) {		
		// Validate client can do this order
		MetaClient client = _clients.get(clientId);
		if (client == null) {
			// Could not queue order, client not registered
			return false;
		}
		
		double totalCost = order.GetPricePerShare() * order.GetVolume();
		OrderType orderType = order.GetType();
		
		// Verify the client has sufficient funds to make a purchase
		if (orderType == OrderType.MarketBuy || orderType == OrderType.LimitBuy) {
			if (totalCost > client.GetCapital()) {
				// The client has insufficient funds available for the order
				return false;
			}
		}
		
		// Verify the client has sufficient shares to sell
		if (orderType == OrderType.MarketSell || orderType == OrderType.LimitSell) {
			Position currentPosition = client.GetPositions().get(order.GetStock().GetId());
			if (currentPosition == null) {
				// The client has no position in the company, nothing to sell
				return false;
			}
			
			if (order.GetVolume() > currentPosition.GetSharecount()) {
				// The order is too large. The client does not own enough shares
				return false;
			}
		}
		
		// Validation passed - the order is valid for the client and it can be queued
		_newOrderQueue.add(order);
		
		// The order was queued
		return true;
	}
	
	/**
	 * @param stock The stock to register
	 * @return Whether the stock was registered
	 */
	protected boolean RegisterStock(Stock stock) {
		if (stock == null) {
			// Can't register a null stock
			return false;		
		}		
		
		UUID stockId = stock.GetId();
		Stock currentStock = _stocks.get(stockId);
		if (currentStock != null) {
			// Stock already registered
			return false;
		}
		
		// Add the stock
		_stocks.put(stockId, stock);
		
		// Set the initial share price
		// For the purposes of this exercise, we consider the "IPO" to be a limit sell of all oustanding shares
		// at the initial price per share
		Order order = new Order(stock, stock.InitialPricePerShare(), stock.OutstandingShares(), OrderType.LimitSell);
		_placedSellOrders.put(stockId, Collections.singletonList(order));
		_sharePrices.put(stock.GetId(), new SharePrice(stockId, null, order, null));
		
		// Successfully registered the stock
		return true;
	}
	
	/**
	 * All of the orders in the queue should be valid to execute
	 * @return whether the order executed successfully
	 */
	public boolean ProcessQueuedOrder() {
		Order order;
		
		try {
			order = _newOrderQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		
		SharePrice sharePrice = _sharePrices.get(order.GetStock().GetId());

		boolean canExecute = false;
		switch(order.GetType()) {
			case LimitBuy:
				break;
			case LimitSell:
				break;
			case MarketBuy:
				break;
			case MarketSell:
				break;
			default:
				break;		
		}

		// If this order can be executed, do so		
		if (canExecute) {
			ExecuteOrder(order);
		}
		
		return true;
	}
	
	public boolean ExecuteOrder(Order order) {
		// TODO - 
		return false;
	}
	
	public boolean RegisterClient(MetaClient client) {
		if (client == null) {
			// Can't register null client
			return false;
		}
		
		MetaClient currentClient = _clients.get(client.GetId());
		if (currentClient != null) {
			// Client already registered
			return false;
		}
		
		// Register client
		_clients.put(client.GetId(), client);	
		
		// Client registered successfully
		return true;
	}
}
