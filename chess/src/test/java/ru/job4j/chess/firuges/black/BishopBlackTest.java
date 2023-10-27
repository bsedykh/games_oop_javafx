package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {
    @Test
    public void whenCreatedThenHasInitialPosition() {
        Cell position = Cell.F8;
        BishopBlack bishop = new BishopBlack(position);
        assertThat(bishop.position()).isEqualTo(position);
    }

    @Test
    public void whenCopiedThenHasDestinationPosition() {
        Cell source = Cell.F8;
        Cell dest = Cell.D6;
        BishopBlack bishop = new BishopBlack(source);
        assertThat(bishop.copy(dest).position()).isEqualTo(dest);
    }

    @Test
    public void whenWayIsZeroLengthThenEmptyArray() throws ImpossibleMoveException {
        Cell source = Cell.C1;
        Cell dest = Cell.C1;
        Cell[] way = {};
        BishopBlack bishop = new BishopBlack(source);
        assertThat(bishop.way(dest)).containsExactly(way);
    }

    @Test
    public void whenWayC1G5ThenD2E3F4G5() throws ImpossibleMoveException {
        Cell source = Cell.C1;
        Cell dest = Cell.G5;
        Cell[] way = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        BishopBlack bishop = new BishopBlack(source);
        assertThat(bishop.way(dest)).containsExactly(way);
    }

    @Test
    public void whenWayIsNotDiagonalThenThrowException() {
        Cell source = Cell.C1;
        Cell dest = Cell.F5;
        BishopBlack bishop = new BishopBlack(source);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> bishop.way(dest));
        assertThat(exception.getMessage()).isEqualTo(
                String.format("Could not move by diagonal from %s to %s", source, dest)
        );
    }
}
