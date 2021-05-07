import java.text.DecimalFormat;

public class Main {
    public static double q = 0.5;

    public static void main(String[] args) {
        simulate(10, 0);
        DecimalFormat df = new DecimalFormat("#.##");

        for(double p = 0.01; p < 0.5; p = p +0.01){
            System.out.println(df.format(p));

        }
//        for(double p = 0.01; p < 0.5; p = p +0.01){
//            System.out.println(meanJobs(100000, p));
//        }

//        for(double p = 0.01; p < 0.5; p = p +0.01){
//            System.out.println(meanResponseTime(100000, p));
//
//        }


    }

    public static int generateRV(double p) {

        int myRV = 0;
        double Y = Math.random();

        if (Y < p)
            myRV = 1;

        for (int i = 2; myRV == 0; i++) {
            if ((Y > 1 - Math.pow(1 - p, i - 1)) && (Y < 1 - Math.pow(1 - p, i))) {
                myRV = i;
            }
        }

        return myRV;
    }

    public static void simulate(int n, double intArrTime) {
        int currTime;
        int nextArrTime;
        int nextDepTime;
        Server serverPRIORITY = new Server();
        Server serverSECONDARY = new Server();
        Dispatch dispatch = new Dispatch();

        Package firstPackage = new Package(0, generateRV(q));
        dispatch.addToQueue(firstPackage);
        nextDepTime = firstPackage.size;
        firstPackage.depTime = nextDepTime;
        nextArrTime = generateRV(intArrTime);
        dispatch.processNextPackage();


        while (dispatch.numPackages < n) {
            if (nextArrTime < nextDepTime) {
                currTime = nextArrTime;
                Package p = new Package(nextArrTime, generateRV(q));
                if (dispatch.isBusy()) {
                    dispatch.addToQueue(p);
                } else {
                    dispatch.addToQueue(p);
                    dispatch.processNextPackage();
                    nextDepTime = currTime + dispatch.currentPackage().size;
                    dispatch.currentPackage().depTime = nextDepTime;
                }
                nextArrTime = currTime + generateRV(intArrTime);
            }
            else {
                currTime = nextDepTime;
                System.out.println("arrival time: " + dispatch.currentPackage().arrTime + " size: " + dispatch.currentPackage().size + " departure time: " + dispatch.currentPackage().depTime);
                dispatch.completePackage();
                if (!dispatch.queueIsEmpty()) {
                    dispatch.processNextPackage();
                    nextDepTime = currTime + dispatch.currentPackage().size;
                    dispatch.currentPackage().depTime = nextDepTime;
                } else {
                    nextDepTime = Integer.MAX_VALUE;
                }
            }


        }
    }

}
