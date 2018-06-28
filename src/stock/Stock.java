package stock;

import java.util.UUID;

public class Stock {
	private String _tickerSymbol;
	private String _name;
	private String _description;
	private UUID _id;
	
	public Stock(String tickerSymbol, String name) {
		Init(tickerSymbol, name, null);
	}
	
	public Stock(String tickerSymbol, String name, String description) {
		Init(tickerSymbol, description, null);
	}
	
	private void Init(String tickerSymbol, String name, String description) {
		_tickerSymbol = tickerSymbol;
		_name = name;
		_description = null;
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
	
	public UUID GetId() {
		return _id;
	}
}
