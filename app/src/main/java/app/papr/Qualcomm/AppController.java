package app.papr.Qualcomm;

public class AppController {


    public int heartRate,pnnPercentage,pnnCount,rrThreshold, totalNN ,lastRRvalue;
    public String sessionId;

    private static AppController instance = new AppController();
    public static AppController getInstance() {
        return instance;
    }
}