package tdd;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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
        this.random = mock(Random.class);
        this.failingPolicy = new RandomFailing(random);
    }
    @Test
    void attemptOnReturnsTrueWhenRandomReturnsFalse() {
        when(random.nextBoolean()).thenReturn(false);
        assertTrue(failingPolicy.attemptOn());
    }
    @Test
    void onceFailedStaysFailed() {
        when(random.nextBoolean()).thenReturn(true, false, false);
        assertFalse(failingPolicy.attemptOn());
        assertFalse(failingPolicy.attemptOn());
        assertFalse(failingPolicy.attemptOn());
    }
    @Test
    void resetAllowsToAttemptAgain() {
        when(random.nextBoolean()).thenReturn(true, false);
        assertFalse(failingPolicy.attemptOn());
        failingPolicy.reset();
        assertTrue(failingPolicy.attemptOn());
    }






}
