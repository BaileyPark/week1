package io.hhplus.tdd.point;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    @Autowired
    UserPointTable userPointTable;
    @Autowired
    PointHistoryTable pointHistoryTable;

    public UserPoint pointById(long id) {
        return userPointTable.selectById(id);
    }

    public List<PointHistory> historyFindByid(long id) {
        return pointHistoryTable.selectAllByUserId(id);
    }

    public UserPoint chargeById(long id, long amount) {
        UserPoint userPoint = userPointTable.selectById(id);
        long point = userPoint.point();
        point += amount;
        pointHistoryTable.insert(id, amount, TransactionType.CHARGE, System.currentTimeMillis());
        return userPointTable.insertOrUpdate(id, point);
    }


    public UserPoint useById(long id, long amount) {
        UserPoint userPoint = userPointTable.selectById(id);
        long point = userPoint.point();
        if (point < amount) {
            /*
            * 예외처리 진행
            * */
            System.out.println("lack of points");
            return userPoint;
        }
        pointHistoryTable.insert(id, amount, TransactionType.USE, System.currentTimeMillis());
        point -= amount;
        return userPointTable.insertOrUpdate(id, point);
    }
}
