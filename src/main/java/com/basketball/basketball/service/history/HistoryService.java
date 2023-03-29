package com.basketball.basketball.service.history;

import com.basketball.basketball.enums.Operation;
import com.basketball.basketball.model.History;

import java.util.List;

public interface HistoryService {
    void save(Operation operationType, Object object);

    List<History> getHistory();
}
