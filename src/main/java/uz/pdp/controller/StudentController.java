package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.payload.Student;
import uz.pdp.service.StudentService;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String getAllStudents(Model model){
        model.addAttribute("students",studentService.getListStudent());



        return "studentList";
    }

    @GetMapping("/addOrEdit")
    public String addOrEdit(){
        return "edit";
    }
    @GetMapping("/addOrEdit/{id}")
    public String addOrEdit(@PathVariable("id") Integer id,Model model){
        model.addAttribute("student",studentService.getOne(id));
        return "edit";
    }

    @PostMapping("/addOrEdit")
    public String addOrEdit(@RequestParam(defaultValue = "0") Integer id,
                            @RequestParam String name,
                            @RequestParam Integer age,
                            @RequestParam String email){
        Student student=new Student();
            student.setId(id);
            student.setName(name);
            student.setAge(age);
            student.setEmail(email);
        boolean b = id != 0 ? studentService.update(student) : studentService.addStudent(student);
        return b?"redirect:/":"Amalni bajarishni imkoni bo'lmadi";
    }

    @GetMapping("/save")
    public String save(){
        return "save";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        boolean delete = studentService.delete(id);
        return delete?"redirect:/":"Amalni bajarishni imkoni bo'lmadi";
    }
}
