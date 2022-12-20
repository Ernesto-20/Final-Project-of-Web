package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.InitCourseTransactionDTO;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitCourseTransactionServiceImpl implements InitCourseTransactionService{

    @Autowired
    private RestService restService;

    @Override
    public void initCourse(InitCourseTransactionDTO initCourseTransactionDTO) {
        restService.POST("/api/v1/initcourse", initCourseTransactionDTO, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
