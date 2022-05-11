package com.codegym.service.status;

import com.codegym.model.Status;
import com.codegym.repository.IStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusService implements IStatusService{

    @Autowired
    private IStatusRepository statusRepository;


    @Override
    public Iterable<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Optional<Status> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public Status save(Status status) {
        return null;
    }
}
