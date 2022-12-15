package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.InitCourseTransactionDTO;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;

public class InitCourseTransactionServiceImpl implements InitCourseTransactionService{

    @Autowired
    private RestService restService;

    @Override
    public void initCourse(InitCourseTransactionDTO initCourseTransactionDTO) {
        restService.POST("/api/v1/init-course", initCourseTransactionDTO, String.class).getBody();
    }

}
