package com.basketball.basketball.repository;

import com.basketball.basketball.dto.LogDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepository extends ElasticsearchRepository<LogDto, Integer> {
}
