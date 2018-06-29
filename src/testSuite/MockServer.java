package testSuite;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import server.Server;
import stock.Order;
import stock.SharePrice;
import stock.Stock;

public class MockServer extends Server {
	
	public MockServer() {
		// Set up the initial data
		Stock aapl = new Stock("AAPL", "Apple Inc.", 
				"Apple Inc. is an American multinational technology company headquartered in Cupertino, California, that designs, develops, and sells consumer electronics, computer software, and online services.",
				100000000, 1);
		Stock nvda = new Stock("NVDA", "NVIDIA Corporation", 
				"Nvidia Corporation is an American technology company incorporated in Delaware and based in Santa Clara, California.",
				100000000, 1);
		Stock msft = new Stock("MSFT", "Microsoft Corporation", 
				"Microsoft Corporation is an American multinational technology company with headquarters in Redmond, Washington. It develops, manufactures, licenses, supports and sells computer software, consumer electronics, personal computers, and services.",
				100000000, 1);
		Stock googl = new Stock("GOOGL", "Alphabet Inc Class A", 
				"Alphabet Inc. is an American multinational conglomerate headquartered in Mountain View, California. It was created through a corporate restructuring of Google on October 2, 2015 and became the parent company of Google and several former Google subsidiaries.",
				100000000, 1);
		Stock fb = new Stock("FB", "Facebook, Inc. Common Stock", 
				"Facebook is an American online social media and social networking service company based in Menlo Park, California.",
				100000000, 1);
		Stock amzn = new Stock("AMZN", "Amazon.com, Inc.", 
				"Amazon.com, Inc., doing business as Amazon, is an American electronic commerce and cloud computing company based in Seattle, Washington that was founded by Jeff Bezos on July 5, 1994.",
				100000000, 1);
		
		RegisterStock(aapl);
		RegisterStock(nvda);
		RegisterStock(msft);
		RegisterStock(googl);
		RegisterStock(fb);
		RegisterStock(amzn);
	}
}
