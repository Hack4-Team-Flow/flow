import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreCalculator {
    public int closestStoreIndex=-1;
    public Store[] stores;
    public double[] distances;
    public double[] location;
    public double travelTimeToOrder;
    public Integer[] indexes;
    List<Integer> indexlist;
    public StoreCalculator(Store[] stores, double[] location) {

        this.stores = stores;
        this.location = location;
        distances=new double[stores.length];
        indexes=new Integer[stores.length];
        indexlist=new ArrayList<>(Arrays.asList(indexes));
        arrangeDistances();
        findClosest(indexes);
        int count=0;
        System.out.println("BEFORE WHİLE closest: "+closestStoreIndex+" store duration: "+stores[closestStoreIndex].getDuration()+" calculated duration to the store: "+calculateDuration()+" distance: "+distances[closestStoreIndex]);
        if(stores[closestStoreIndex].getDuration()>calculateDuration()){
            while(stores[closestStoreIndex].getDuration()>calculateDuration()){
                System.out.println("WHEN FIRST ENTERED THE WHILE closest: "+closestStoreIndex+" store duration: "+stores[closestStoreIndex].getDuration()+" calculated duration to the store: "+calculateDuration()+" distance: "+distances[closestStoreIndex]);
                indexes[count]=closestStoreIndex;
                findClosest(indexes);
                System.out.println("AFTER FIND closest: "+closestStoreIndex+" store duration: "+stores[closestStoreIndex].getDuration()+" calculated duration to the store: "+calculateDuration()+" distance: "+distances[closestStoreIndex]);
                if(count>15){
                    while (true){

                    }
                }
                count++;
            }
        }
        count=0;
        for(Double d:distances){
            System.out.print(d);
            System.out.println("    "+stores[count].toString());
            count++;
        }/*
        System.out.println(closestStoreIndex);
        System.out.println(stores[closestStoreIndex].toString());
        System.out.println(calculateDuration());*/

        travelTimeToOrder= (stores[closestStoreIndex].getDuration()-calculateDuration());
    }
    public void arrangeDistances(){
        int index=0;
        for(Store s:stores){
            double storeLatitude=s.getLatitude();
            double storeLongitude=s.getLongitude();
            double distance=calculateDistance(location[0],storeLatitude,location[1],storeLongitude,0.00,0.00);
            distances[index]=distance;
            index++;
        }
    }
    public double calculateDistance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
    public void findClosest(Integer[] index){
        int i=0;
        while(indexlist.contains(i)){
            i++;
        }
        System.out.println("index: "+i);
        double minValue = distances[i];
        for(;i<distances.length;i++){
            System.out.println(minValue);
            if(distances[i] < minValue && !indexlist.contains(i)){
                minValue = distances[i];
                closestStoreIndex=i;
                System.out.println(closestStoreIndex);
            }
        }
        System.out.println("FIND BITTI ");
    }
    public double calculateDuration(){
        double speed=50.00;
        return (distances[closestStoreIndex]/speed)/60.00;
    }

    public double getTravelTimeToOrder() {
        return travelTimeToOrder;
    }
}
