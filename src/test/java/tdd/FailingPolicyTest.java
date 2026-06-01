package tdd;

import devices.FailingPolicy;
import devices.RandomFailing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
public class FailingPolicyTest {
    private FailingPolicy failingPolicy;
    private Random random;
    @BeforeEach
    void init() {
        this.random = new Random();
        this.failingPolicy = new RandomFailing(random);
    }
    @Test





}
