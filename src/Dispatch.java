import java.util.Queue;
import java.util.LinkedList;

public class Dispatch{
    private int currTime;
    private int arrTime;
    private int depTime;
    private Queue<Package> queue;
    boolean busy;
    private Package currPackage;
    public int numPackages = 0;


    public Dispatch(){
        System.out.println("testing");

    }

    public boolean queueIsEmpty(){
        if(queue.size() == 0){
            return true;
        }
        return false;
    }

    public void processNextPackage(){
        currPackage = queue.poll();
        busy = true;

    }

    public void completePackage(){
        numPackages++;
        queue.remove();
        busy = false;
    }

    public void completePackage1(){
        numPackages++;
        queue.remove();
        busy = false;
    }

    public void addToQueue(Package j){
        queue.add(j);
    }


    public boolean isBusy(){

        return busy;
    }

    public Package currentPackage(){
        return currPackage;
    }

    public int queueSize(){
        return queue.size();
    }



}