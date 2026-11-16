//Jomana adel 4TI  NO:16
import java.util.*;

class Car extends Thread{
    int id;
    int round;                                  

    Car(int id,int round){
        this.id=id;
        this.round=round;
    }
    public void run(){
        
        for(int i=0; i< round; i++) {
           Main.s.acquire(); 
           Main.busy++;
        if(Main.busy > Main.maxBusy){
            Main.maxBusy=Main.busy;
        }
        Main.uses[id-1]++;

        Main.total++;
           try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

           Main.busy--;
        Main.s.release();
        }
        

    }
}
public class Main{
    static int total;
    static int busy;
    static int maxBusy;
    static int uses[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int mutex =1;
   static MySemaphore s = new MySemaphore (5);

    public static void main(String[]args)throws InterruptedException{


        Car[] cars = new Car[10];
        int[] rounds = {2, 2, 3, 4, 2, 3, 5, 2, 3, 4};
 

        for(int i=0 ; i<10 ; i++){
            cars[i] = new Car(i+1,rounds[i]);
            cars[i].start();
        }
        for(int i=0 ; i<10 ; i++){
            cars[i].join();
        }

         System.out.println("Total uses:" + total);
         for(int i=0 ; i<10 ; i++){
            System.out.println("Car "+ (i+1) + " : " + (uses[i]) + " uses");
        }


       System.out.println("most in use at the same time :"+ maxBusy);
        }
      
        
    }
