package tdd;

import devices.FailingPolicy;

import java.util.Objects;
import java.util.Random;

public class RandomFailing implements FailingPolicy {
    private final Random random; //inject random so i can mock it
    private boolean failed = false;
    public RandomFailing(Random random) {
        this.random = Objects.requireNonNull(random);
    }
    @Override
    public boolean attemptOn() {
        this.failed = this.failed || random.nextBoolean();
        return !this.failed;
    }

    @Override
    public void reset() {
        this.failed = false;
    }

    @Override
    public String policyName() {
        return "random";
    }
}
