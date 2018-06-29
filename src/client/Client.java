package client;

import java.util.UUID;

import server.MetaClient;
import server.Server;
import stock.Order;
import stock.OrderType;
import stock.Portfolio;
import stock.Stock;

public class Client {
	protected final Server _server;
	protected double _capital;
	protected final Portfolio _portfolio;
	protected final UUID _id;
	protected final MetaClient _meta;

	public Client(Server server, double initialCapital) {	
		if (server == null) {
			throw new IllegalArgumentException("server cannot be null");
		}
		
		_server = server;
		_capital = initialCapital;
		_portfolio = new Portfolio();
		_id = UUID.randomUUID();
		_meta = new MetaClient(_capital);
	}
	
	public UUID GetId() {
		return _id;
	}
	
	public double GetCapital() {
		return _capital;
	}
	
	public Portfolio GetPorfolio() {
		return _portfolio;
	}
	
	public void CreateOrder(Stock stock, double pricePerShare, double volume, OrderType orderType) {		
		// Create an order
		Order order = new Order(stock, pricePerShare, volume, orderType);
		_server.RequestQueueOrder(_meta.GetId(), order);
	}
}
