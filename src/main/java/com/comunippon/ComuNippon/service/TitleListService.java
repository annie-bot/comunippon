package com.comunippon.ComuNippon.service;

import com.comunippon.ComuNippon.model.TitleList;
import com.comunippon.ComuNippon.repository.TitleListRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class TitleListService {

    private final TitleListRepository repository;

    public void createTitleList(TitleList titleListDTO) {
        TitleList titleList = new TitleList();
        titleList.setName(titleListDTO.getName());

        repository.save(titleList);
    }

    public List<TitleList> listTitleList() {
        return repository.findAll();
    }

    public TitleList getTitleListById(Long id) {
        return repository.findById(id).get();
    }

    public void deleteTitleList(Long id) throws Exception {
        TitleList titleList = getTitleListById(id);
        if (Objects.isNull(titleList)) {
            throw new Exception("Lista de títulos não encontrada.");
        }
        repository.deleteById(id);
    }

    public TitleList updateTitleList(Long id, TitleList titleListDTO) {
        TitleList titleList = getTitleListById(id);
        titleList.setName(titleListDTO.getName());

        return repository.save(titleList);
    }

}
