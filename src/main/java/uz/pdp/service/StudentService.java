package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.pdp.payload.Student;
import uz.pdp.payload.StudentRowMapper;

import java.util.List;

@Service
public class StudentService {
    private final JdbcTemplate jdbcTemplate;
//    @Autowired
//    JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_STUDENT = "select * from student where id = ?";
    private final String SQL_DELETE_STUDENT = "delete from student where id = ?";
    private final String SQL_UPDATE_STUDENT = "update student set name = ?, age  = ?,email=? where id = ?";
    private final String SQL_GET_ALL = "select * from student";
    private final String SQL_INSERT_STUDENT = "insert into student(name, age,email) values(?,?,?)";

    @Autowired
    public StudentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> getListStudent(){
//        return jdbcTemplate.query("select * from student",new StudentRowMapper());
        return jdbcTemplate.query(SQL_GET_ALL,
                new BeanPropertyRowMapper<>(Student.class));
    }

    public boolean addStudent(Student student){
        return jdbcTemplate.update(SQL_INSERT_STUDENT,student.getName(),student.getAge(),student.getEmail())>0;
    }

    public Student getOne(Integer id){
        return jdbcTemplate.query(SQL_FIND_STUDENT,new Object[]{id},new BeanPropertyRowMapper<>(Student.class)).get(0);
    }

    public boolean update(Student student){
        return jdbcTemplate.update(SQL_UPDATE_STUDENT,student.getName(),student.getAge(),student.getEmail(), student.getId())>0;
    }

    public boolean delete(Integer id){
        return jdbcTemplate.update(SQL_DELETE_STUDENT,id)>0;
    }




}
