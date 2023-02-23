package ua.d1nque.mongodb.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.d1nque.mongodb.repository.PepEntityRepository;
import ua.d1nque.mongodb.repository.impl.PepEntityRepositoryCustomImpl;

import java.util.List;

@Service
public class MongoService {
    private final PepEntityRepositoryCustomImpl pepEntityRepositoryCustomImpl;
    private final PepEntityRepository pepEntityRepository;

    @Autowired
    public MongoService(PepEntityRepositoryCustomImpl pepEntityRepositoryCustomImpl, PepEntityRepository pepEntityRepository){
        this.pepEntityRepositoryCustomImpl = pepEntityRepositoryCustomImpl;
        this.pepEntityRepository = pepEntityRepository;
    }

    public List<Object> getTopTenPepNames(){
        return pepEntityRepositoryCustomImpl.getTopTenPepNames();
    }

    public List<Object> findByFullNameEnContaining(String fullNameEn){
        return pepEntityRepositoryCustomImpl.findByFullNameEn(fullNameEn);
    }

}
