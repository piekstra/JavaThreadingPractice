package testSuite;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import client.Client;
import server.Server;
import stock.OrderType;
import stock.Portfolio;
import stock.Position;
import stock.Stock;

public class MockClient extends Client {
	private static final SecureRandom  _random = new SecureRandom();

	/*
	 * Super parameters
	 */
	static final int MIN_CAPITAL = 50000;
	static final int MAX_CAPITAL = 10000000;
	private static final int minOrderDelay = 5;
	private static final int maxOrderDelay = 60;
	private static final int minShareVolume = 1;
	private static final int maxShareVolume = 100;
	private static final int minSharePrice = 1;
	private static final int maxSharePrice = 2000;
	
	public MockClient(Server server) {		
		super(server, _random.nextInt(MAX_CAPITAL) + MIN_CAPITAL);
		StartRandomOrderGeneratorThread();		
		ScheduleDisplays();
	}
	
	private void ScheduleDisplays() {
		// Display Portfolio every minute
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			  @Override
			  public void run() {
				  DisplayPortfolio();
			  }
			}, 0, 60, TimeUnit.SECONDS);
	}
	
	private void DisplayPortfolio() {
		System.out.format("\nPortfolio for %s", GetId());
		Portfolio portfolio = GetPorfolio();
		for(Iterator<Position> positionIterator = portfolio.GetPositions().values().iterator(); positionIterator.hasNext();) {
			Position position = positionIterator.next();
			System.out.format("\n%s: %d %f", position.GetStockInfo().GetSymbol(), position.GetSharecount(), position.GetAveragePricePerShare());
		}
	}
	
	private void RunTicker() {
		// Keep displaying ticks
		// TODO
	}
	
	
	private void DisplayTick(String symbol) {
		// TODO
	}
	
	private void StartRandomOrderGeneratorThread() {		
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
			exec.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(_random.nextInt(maxOrderDelay));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					CreateRandomOrder();
				}
			}, 0, minOrderDelay, TimeUnit.SECONDS);
	}
	
	private void CreateRandomOrder() {		
		// TODO - Make the random values generate within the constraints of the client's capital and positions
		// For now, just do random things - the server should reject invalid requests
		CreateOrder(GetRandomStock(), GetRandomSharePrice(), GetRandomShareVolume(), GetRandomOrderType());
	}
	
	private OrderType GetRandomOrderType() {
		return randomEnum(OrderType.class);
	}
	
	private double GetRandomShareVolume() {
		int minShares = minShareVolume;
		int maxShares = maxShareVolume;
		int shares = _random.nextInt(maxShares) + minShares;
		return shares;
	}
	
	private double GetRandomSharePrice() {
		int minPrice = minSharePrice;
		int maxPrice = maxSharePrice;
		double sharePrice = minPrice + (maxPrice - minPrice) * _random.nextDouble();
		return sharePrice;
	}
	
	private Stock GetRandomStock() {
		Map<UUID, Stock> stocks = _server.GetSupportedStocks();
		Random generator = new Random();
		Object[] values = stocks.values().toArray();
		Object randomValue = values[generator.nextInt(values.length)];
		return (Stock)randomValue;
	}
	
	private static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = _random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
