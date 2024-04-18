package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    UserPointTable userPointTable = new UserPointTable();
    PointHistoryTable pointHistoryTable = new PointHistoryTable();

    public UserPoint pointById(long id) {
        return userPointTable.selectById(id);
    }

    public List<PointHistory> historyFindByid(long id) {
        return pointHistoryTable.selectAllByUserId(id);
    }

    public UserPoint chargeById(long id, long amount) {
        pointHistoryTable.insert(id, amount, TransactionType.CHARGE, System.currentTimeMillis());
        UserPoint userPoint = userPointTable.selectById(id);
        long point = userPoint.point();
        point += amount;
        return userPointTable.insertOrUpdate(id, point);
    }


    public UserPoint useById(long id, long amount) {
        pointHistoryTable.insert(id, amount, TransactionType.USE, System.currentTimeMillis());
        UserPoint userPoint = userPointTable.selectById(id);
        long point = userPoint.point();
        if (point < amount) {
            System.out.println("lack of points");
            return userPoint;
        }
        point -= amount;
        return userPointTable.insertOrUpdate(id, point);
    }
}
