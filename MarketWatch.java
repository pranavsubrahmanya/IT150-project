import org.json.JSONException;

import java.time.LocalDateTime;

public class MarketWatch {
    public void forEachStock(Stock s) throws JSONException {

        LocalDateTime date = LocalDateTime.now().minusDays((long)1.0);
        String d = date.toString().substring(0,10);
        String day = date.getDayOfWeek().toString();
        String LatestDay ;
        String earlierDay =  date.minusDays((long) 7.0).toString().substring(0,10);
        if(day.equals("SUNDAY")){
            LatestDay = date.minusDays((long) 2.0).toString().substring(0,10);
            earlierDay = date.minusDays((long) 9.0).toString().substring(0,10);
        }
        else if(day.equals("SATURDAY")){
            LatestDay = date.minusDays((long) 1.0).toString().substring(0,10);
            earlierDay = date.minusDays((long) 8.0).toString().substring(0,10);
        }
        else{
            LatestDay = d;

        }

        System.out.println(String.format("%s | %.3f | %s",s,s.getClosePrice(),s.StockTrend(LatestDay,earlierDay)));

    }
    public void popularTechStocks() throws JSONException {

        System.out.println("Stock : Current price : Performance");
        Stock[] StockArr = {new Stock("MSFT"), new Stock("IBM"), new Stock("TSLA"), new Stock("FB"), new Stock("GS"),new Stock("AMZN"), new Stock("ORCL"), new Stock("GOOGL"), new Stock("TXN"),new Stock("AAPL") };
        for(Stock A : StockArr){
            forEachStock(A);
        }

    }
    public void marketIndices(){
        System.out.println("NYSE Composite index: $16,868.11");
        System.out.println("Dow Jones Industrial Average: $35,515.38");
        System.out.println("S&P 500 Index: $14,822.55");
        System.out.println("Russell 2000 Index: $2,223.11");
        System.out.println("Global Dow Realtime USD: $4,097.02");
        System.out.println("Dow Jones U.S. Total Stock Market Index: $46,820.65");
        System.out.println("NASDAQ 100 Index (NASDAQ Calculation): $15,136.68");
        System.out.println("NYSE Composite Index: $16,868.11");
    }
    public void completeStockData(String symbol) throws JSONException {
        Stock stock = new Stock(symbol);
        forEachStock(stock);
    }
}
