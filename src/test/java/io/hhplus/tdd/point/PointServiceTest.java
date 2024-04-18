package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PointServiceTest {

    private PointService pointService = new PointService();
    private UserPointTable userPointTable;
    PointHistoryTable pointHistoryTable;

    private long id = 1;

    @BeforeEach
    public void setUp() {
        userPointTable = new UserPointTable();
        pointHistoryTable = new PointHistoryTable();
    }

    @Test
    public void pointTest() {
        userPointTable.selectById(id);
        
    }

}
