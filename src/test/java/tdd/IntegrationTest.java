package tdd;

import master.a01a.sol2.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTest {
    @Test
    void hittingEmptyCellMarksAndLogs() {
        LoggerImpl logger = new LoggerImpl();
        Logic logic = new LogicImpl(3, logger);
        var result = logic.hit(new Position(0,0));

        assertEquals(1, result.orElseThrow());
        assertEquals(1, logic.getMark(new Position(0, 0)).orElseThrow());

        assertEquals(1, logger.getLogs().size());
        assertTrue(logger.getLogs().getFirst().contains("added mark 1"));

    }
    @Test
    void hittingNeighbourStartsMovingAndLogs() {
        LoggerImpl logger = new LoggerImpl();
        Logic logic = new LogicImpl(3, logger);

        logic.hit(new Position(0, 0));
        logic.hit(new Position(1, 0));
        assertTrue(logger.getLogs().stream().anyMatch(s -> s.contains("Moving marks")));

    }
    @Test
    void clickingButtonUpdatesGUIandLogs() {
        LoggerImpl logger = new LoggerImpl();
        Logic logic = new LogicImpl(3, logger);

        GUI gui = new GUI(3, logic, () -> {});

        JPanel panel = (JPanel) gui.getContentPane().getComponent(0);
        JButton firstButton = (JButton) panel.getComponent(0);

        firstButton.doClick();

        assertEquals("1", firstButton.getText());
        assertEquals(1, logic.getMark(new Position(0,0)).orElseThrow());

        assertEquals(1, logger.getLogs().size());

        assertTrue(logger.getLogs().get(0).contains("added mark 1"));
        gui.dispose();
    }

}
