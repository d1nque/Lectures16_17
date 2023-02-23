package ua.d1nque.mongodb.repository;


import java.util.List;

public interface PepEntityRepositoryCustom {

    List<Object> getTopTenPepNames();

    List<Object> findByFullNameEn(String fullNameEn);
}

