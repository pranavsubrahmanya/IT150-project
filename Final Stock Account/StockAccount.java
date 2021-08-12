import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class StockAccount extends Account{

    JSONObject UserAccountData,USERDATA,dataUAD,dataUD;
    private final String UserID;
    StockAccount(String UserID) throws JSONException, IOException {
        super(UserID);
        String s="";
        this.UserID = UserID;
        FileReader reader = new FileReader("UserAccount_Data.json");
        Scanner scan = new Scanner(reader);
        while(scan.hasNext()){
            s = s + scan.nextLine();
        }
        reader.close();
        dataUAD = new JSONObject(s);
        UserAccountData = dataUAD.getJSONObject(UserID);

        String s3 = "";
        FileReader readerx = new FileReader("USERDATA.json");
        Scanner scanx = new Scanner(readerx);
        while(scanx.hasNext()){
            s3  = s3 + scanx.nextLine();
        }
        readerx.close();
        dataUD = new JSONObject(s3);
        USERDATA = dataUD.getJSONObject(UserID);

    }
    public String getUserID(){
        return this.UserID;
    }
    public String getUsername() throws JSONException {
        return USERDATA.getString("userName");
    }
    public double getBalance() throws JSONException {
        return Double.valueOf(USERDATA.getString("balance"));
    }

    public LinkedList<String> StockList(){
        LinkedList<String> listOfStocks = new LinkedList<String>();
        Iterator it = UserAccountData.keys();
        while(it.hasNext()){
            listOfStocks.add(it.next().toString());
        }
        return listOfStocks;
    }

    public void addMoney(double amount) throws JSONException, IOException {
        double currentBalance = Double.valueOf(USERDATA.get("balance").toString()) ;
        USERDATA.put("balance",currentBalance + amount);

        dataUD.put(this.getUserID(),USERDATA);
        FileWriter file = new FileWriter("USERDATA.json");
        file.write(dataUD.toString());
        file.close();

    }

    public void deductMoney(double amount) throws IOException, JSONException {
        double currentBalance = Double.valueOf(USERDATA.get("balance").toString()) ;
        USERDATA.put("balance",currentBalance - amount);

        dataUD.put(this.getUserID(),USERDATA);
        FileWriter file = new FileWriter("USERDATA.json");
        file.write(dataUD.toString());
        file.close();
    }

    public void buyStock(String symbol, int noOfShares) throws JSONException, IOException {
        Stock stock = new Stock(symbol);
        LinkedList<String> listOfStocks = this.StockList();
        boolean exists = false;
        for(int i=0;i<listOfStocks.size();i++){
            if(listOfStocks.get(i).toString().equals(symbol)){
                exists = true;
                break;
            }
        }
        if(exists){
            UserAccountData.getJSONObject(symbol).getJSONObject("buys").put(String.valueOf(LocalDateTime.now().minusDays((long) 1)).substring(0,10),noOfShares);
            int currentHoldings = Integer.parseInt(UserAccountData.getJSONObject(symbol).get("CurrentHoldings").toString());
            UserAccountData.getJSONObject(symbol).put("CurrentHoldings",currentHoldings + noOfShares);
        }
        else{
            JSONObject stockJSONObject = new JSONObject();
            JSONObject buys = new JSONObject();
            JSONObject sells = new JSONObject();

            buys.put(String.valueOf(LocalDateTime.now().minusDays((long) 1)).substring(0,10),noOfShares);

            stockJSONObject.put("buys",buys);
            stockJSONObject.put("sells",sells);

            stockJSONObject.put("CurrentHoldings",noOfShares);

            UserAccountData.put(symbol,stockJSONObject);
        }

        double currPrice = new Stock(symbol).getClosePrice();
        this.deductMoney(currPrice*noOfShares);

        dataUAD.put(this.getUserID(),UserAccountData);
        FileWriter file = new FileWriter("UserAccount_Data.json");
        file.write(dataUAD.toString());
        file.close();

    }

    public void sellStock(String symbol,int noOfShares) throws JSONException, IOException {
        Stock stock = new Stock(symbol);

        UserAccountData.getJSONObject(symbol).getJSONObject("sells").put(String.valueOf(LocalDateTime.now().minusDays((long) 1)).substring(0,10),noOfShares);
        int currentHoldings = Integer.parseInt(UserAccountData.getJSONObject(symbol).get("CurrentHoldings").toString());
        UserAccountData.getJSONObject(symbol).put("CurrentHoldings",currentHoldings - noOfShares);
        double currPrice = new Stock(symbol).getClosePrice();
        this.deductMoney(currPrice*noOfShares);
        dataUAD.put(this.getUserID(),UserAccountData);
        FileWriter file = new FileWriter("UserAccount_Data.json");
        file.write(dataUAD.toString());
        file.close();

    }
//    public static void main(String[] args) throws JSONException, IOException {
//        StockAccount pie = new StockAccount("ID1");
//        pie.addMoney(200000);
//
//    }
}
