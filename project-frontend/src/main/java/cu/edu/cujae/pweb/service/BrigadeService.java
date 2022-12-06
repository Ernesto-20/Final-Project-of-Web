package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.BrigadeDTO;

public interface BrigadeService {

    void insert(BrigadeDTO brigadeDTO);

    List<BrigadeDTO> findAll();

    BrigadeDTO findById(Integer brigadeId);

    List<BrigadeDTO> findByYearId(Integer yearId);

//    BrigadeDTO myFindById(int id);

    void update(BrigadeDTO dto);

    void delete(Integer id);
}
