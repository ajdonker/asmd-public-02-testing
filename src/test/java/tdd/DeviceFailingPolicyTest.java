package tdd;
import devices.RandomFailing;
import devices.FailingPolicy;
import devices.StandardDevice;
import devices.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeviceFailingPolicyTest {
    private Device device;
    private Random random;
    private FailingPolicy randomFailing;

    @BeforeEach
    void init() {
        this.random = mock(Random.class);
        this.randomFailing = new RandomFailing(random);
        this.device = new StandardDevice(this.randomFailing);
    }
    @Test
    void deviceInitiallyOff() {
        assertFalse(device.isOn());
    }
    @Test
    void deviceSwitchesOnWhenPolicyAttemptSuccess() {
        when(random.nextBoolean()).thenReturn(false);

        device.on();
        assertTrue(device.isOn());

    }
    @Test
    void deviceStaysOffWhenPolicyFails() {
        when(random.nextBoolean()).thenReturn(true);
        assertThrows(IllegalStateException.class, () -> device.on());
    }

    @Test
    void deviceCannotSwitchOnUntilReset() {
        when(random.nextBoolean()).thenReturn(true, false);
        assertThrows(IllegalStateException.class, () -> device.on());
        assertFalse(device.isOn());

        assertThrows(IllegalStateException.class, () -> device.on());
        assertFalse(device.isOn());

        device.reset();
        device.on();
        assertTrue(device.isOn());
    }

}
