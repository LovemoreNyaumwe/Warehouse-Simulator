import java.util.ArrayList;
import java.util.LinkedList;


public class Server {
    private LinkedList<Package> queue;
    boolean busy;
    private Package currPackage;
    public int numPackages = 0;

    public Server(){
        queue = new LinkedList<Package>();
        busy = false;

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
