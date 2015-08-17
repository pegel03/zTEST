package zTest;

public class Calling {

}

interface CallBack {

    void methodToCallBack(String string);
}

class CallBackImpl implements CallBack {

    @Override
    public void methodToCallBack(String string) {
        System.out.println("I've been called back: " + string);
    }
}

class Caller {

    public void register(CallBack callback) {
        callback.methodToCallBack("halllo");
    }

    public static void main(String[] args) {
        Caller caller = new Caller();
        CallBack callBack = new CallBackImpl();
        caller.register(callBack);
    }
}