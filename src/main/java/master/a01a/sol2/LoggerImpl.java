package master.a01a.sol2;
import java.util.ArrayList;
import java.util.List;
public class LoggerImpl implements Logger{
    private final List<String> logs = new ArrayList<>();
    @Override
    public void log(String message) {
        System.out.println(message);
        this.logs.add(message);
    }
    public List<String> getLogs() {
        return List.copyOf(this.logs);
    }
}
