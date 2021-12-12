

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] Args) throws IOException, InterruptedException {
        /*Store[] stores=new Store[6];
        Store store1=new Store("White bakery",37.02028754,30.59819901,30,"bake",5.00);
        stores[0]=store1;
        Store store2=new Store("McDonalds'",36.88682729,30.7025303,30,"burger",15.00);
        stores[1]=store2;
        Store store3=new Store("Beach Bar",36.61379416,30.5610624,30,"drink",45.00);
        stores[2]=store3;
        Store store4=new Store("Coffee Shop",36.99647015,30.85289727,30,"cofee",10.00);
        stores[3]=store4;
        Store store5=new Store("Burger King",36.88672206,30.81871253,30,"burger",17.00);
        stores[4]=store5;
        Store store6=new Store("maria's coffee",36.85133768,30.85056657,30,"cofee",10.00);
        stores[5]=store6;*/
        StoreJsonParser storeJsonParser =new StoreJsonParser();
        Store[] stores =new Store[storeJsonParser.getStoreCount()];
        for(int i=0;i<storeJsonParser.getStoreCount();i++){
            stores[i]=new Store(StoreJsonParser.getNames()[i],StoreJsonParser.getLatitudes()[i],StoreJsonParser.getLongtitudes()[i],StoreJsonParser.getPrices()[i],StoreJsonParser.getBusinessTypes()[i],StoreJsonParser.getDurations()[i] );
        }
        double[]location=new double[2];
        LocationJsonParser locationJsonParser=new LocationJsonParser();
        location=locationJsonParser.getLocations();
        String foodKind="";
        String drink="";
        String foodType="";
        TakeOrderJsonParser takeOrderJsonParser=null;
        while(foodKind!=""){
            try{
                takeOrderJsonParser =new TakeOrderJsonParser();
                foodKind=takeOrderJsonParser.getFoodKind();
                foodType=takeOrderJsonParser.getFoodType();
                drink= takeOrderJsonParser.getDrink();
            }catch (Exception e){

            }
        }

        StoreCalculator storeCalculator=new StoreCalculator(stores,location,foodType);
        System.out.println(storeCalculator.getTravelTimeToOrder());
        TimeUnit.SECONDS.sleep((long) storeCalculator.getTravelTimeToOrder());
        StoreOrder storeOrder=new StoreOrder(foodKind,drink);

    }

}
