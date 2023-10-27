package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {
    @Test
    public void whenMoveThenFigureNotFoundException() {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenImpossibleMoveException() {
        Cell source = Cell.C1;
        Cell dest = Cell.H5;
        Logic logic = new Logic();
        logic.add(new BishopBlack(source));
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(source, dest);
        });
        assertThat(exception.getMessage()).isEqualTo(
                String.format("Could not move by diagonal from %s to %s", source, dest)
        );
    }

    @Test
    public void whenMoveThenOccupiedCellException() {
        Cell source = Cell.C1;
        Cell dest = Cell.H6;
        Figure[] figures = {new BishopBlack(source), new BishopBlack(dest)};
        Logic logic = new Logic();
        logic.add(figures[0]);
        logic.add(figures[1]);
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(source, dest);
        });
        assertThat(exception.getMessage()).isEqualTo(
                String.format("Cell %s is occupied with figure %s", dest, figures[1])
        );
    }

    @Test
    public void whenMoveThenNoException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Cell source = Cell.C1;
        Cell dest = Cell.H6;
        Logic logic = new Logic();
        logic.add(new BishopBlack(source));
        logic.move(source, dest);
    }
}
