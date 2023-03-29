package com.basketball.basketball.service.history;

import com.basketball.basketball.enums.Operation;
import com.basketball.basketball.error.NotFoundObjectException;
import com.basketball.basketball.model.History;
import com.basketball.basketball.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    @Transactional
    @Override
    public void save(Operation operationType, Object object) {
        History history = new History();
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            history.setChangedBy(user.getUsername());
        } catch (ClassCastException ex) {
            throw new NotFoundObjectException("Login Username Not Found");
        }
        history.setOperationType(operationType);
        history.setCreateTime(OffsetDateTime.now());
        history.setDetail(object.toString());
        historyRepository.save(history);
    }

    @Override
    public List<History> getHistory() {
        return historyRepository.findAll();
    }
}
