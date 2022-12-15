package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.InitCourseTransactionDTO;

import java.sql.SQLException;

public interface InitCourseTransactionService {
    void initCourse(InitCourseTransactionDTO initCourseTransactionDTO) throws SQLException;
}
