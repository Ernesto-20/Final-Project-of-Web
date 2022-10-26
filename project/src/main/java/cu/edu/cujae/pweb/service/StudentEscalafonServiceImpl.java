package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.StudentEscalafonDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentEscalafonServiceImpl implements StudentEscalafonService{
    @Override
    public List<StudentEscalafonDTO> getStudentsEscalafon() {

        List<StudentEscalafonDTO> students = new ArrayList<>();
        students.add(new StudentEscalafonDTO(UUID.randomUUID().toString(), 1, 3, "Juan Luis", "Heredia Cristante", "M", "Playa", 4.86));
        students.add(new StudentEscalafonDTO(UUID.randomUUID().toString(), 2, 2, "Cristina", "Garcia Matos", "F", "Marianao", 4.37));
        students.add(new StudentEscalafonDTO(UUID.randomUUID().toString(), 3, 5, "Amelia", "Perez Hernandez", "F", "Boyeros", 4.10));
        students.add(new StudentEscalafonDTO(UUID.randomUUID().toString(), 4, 1, "Eladio", "Rodriguez Moreno", "M", "La Lisa", 3.76));
        students.add(new StudentEscalafonDTO(UUID.randomUUID().toString(), 5,  4, "Marcos", "Alonso Martinez", "M", "Plaza", 3.4));
        students.add(new StudentEscalafonDTO(UUID.randomUUID().toString(), 6, 6, "Christopher", "Mujica Gonzalez", "M", "Plaza", 3.05));

        return students;
    }

    @Override
    public StudentEscalafonDTO getStudentEscalafonById(String id) {
        return getStudentsEscalafon().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst().get();
    }
}
