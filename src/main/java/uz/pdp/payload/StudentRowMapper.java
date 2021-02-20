package uz.pdp.payload;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student=new Student();
        
        student.setId(resultSet.getInt("id"));
        student.setAge(resultSet.getInt("age"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        
        
        return student;
    }
}
