package zTest;

public class Calling2Event {
}

interface InterestingEvent {

    // This is just a regular method so it can return something or
    // take arguments if you like.
    public void interestingEvent();
}

class EventNotifier {

    private final InterestingEvent ie;
    private final boolean somethingHappened;

    public EventNotifier(InterestingEvent event) {
        // Save the event object for later use.
        ie = event;
        // Nothing to report yet.
        somethingHappened = false;
    }

    // ...
    public void doWork() {
        // Check the predicate, which is set elsewhere.
        if (somethingHappened) {
            // Signal the even by invoking the interface's method.
            ie.interestingEvent();
        }
        // ...
    }
    // ...
}
