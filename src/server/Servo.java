package server;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import stock.Order;
import stock.SharePrice;
import stock.Stock;

public class Servo {

	private static Map<UUID, Stock> _stocks;
	private static Map<UUID, SharePrice> _sharePrices;
	
	public static void main(String[] args) {
		// Set up the initial data
		Stock aapl = new Stock("AAPL", "Apple Inc.", "Apple Inc. is an American multinational technology company headquartered in Cupertino, California, that designs, develops, and sells consumer electronics, computer software, and online services.");
		Stock nvda = new Stock("NVDA", "NVIDIA Corporation", "Nvidia Corporation is an American technology company incorporated in Delaware and based in Santa Clara, California.");
		Stock msft = new Stock("MSFT", "Microsoft Corporation", "Microsoft Corporation is an American multinational technology company with headquarters in Redmond, Washington. It develops, manufactures, licenses, supports and sells computer software, consumer electronics, personal computers, and services.");
		Stock googl = new Stock("GOOGL", "Alphabet Inc Class A", "Alphabet Inc. is an American multinational conglomerate headquartered in Mountain View, California. It was created through a corporate restructuring of Google on October 2, 2015 and became the parent company of Google and several former Google subsidiaries.");
		Stock fb = new Stock("FB", "Facebook, Inc. Common Stock", "Facebook is an American online social media and social networking service company based in Menlo Park, California.");
		Stock amzn = new Stock("AMZN", "Amazon.com, Inc.", "Amazon.com, Inc., doing business as Amazon, is an American electronic commerce and cloud computing company based in Seattle, Washington that was founded by Jeff Bezos on July 5, 1994.");
		
		_stocks = new HashMap<UUID, Stock>(){{
			put(aapl.GetId(), aapl);
			put(nvda.GetId(), nvda);
			put(msft.GetId(), msft);
			put(googl.GetId(), googl);
			put(fb.GetId(), fb);
			put(amzn.GetId(), amzn);
		}};
		
		_sharePrices = new HashMap<UUID, SharePrice>(){{
			put(aapl.GetId(), new SharePrice(aapl.GetId(), null, null, null));
			put(nvda.GetId(), new SharePrice(nvda.GetId(), null, null, null));
			put(msft.GetId(), new SharePrice(msft.GetId(), null, null, null));
			put(googl.GetId(), new SharePrice(googl.GetId(), null, null, null));
			put(fb.GetId(), new SharePrice(fb.GetId(), null, null, null));
			put(amzn.GetId(), new SharePrice(amzn.GetId(), null, null, null));
		}};
	}
	
	// Returns true if successfully created the order
	public boolean CreateOrder(Order order) {
		return false;
	}
}
