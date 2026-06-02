package tdd;

public interface FailingPolicy {
    boolean attemptOn();
    void reset();
    String policyName();
}
