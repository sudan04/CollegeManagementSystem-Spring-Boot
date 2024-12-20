package com.example.cms.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cms.dtoEntityMapper.UserDTOMapper;
import com.example.cms.entity.Users;
import com.example.cms.enums.Role;
import com.example.cms.service.CourseService;
import com.example.cms.service.DepartmentService;
import com.example.cms.service.FacultyService;
import com.example.cms.service.StudentService;
import com.example.cms.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserDTOMapper userMapper;
    
    @Autowired
    private FacultyService facultyService;
    
    @Autowired 
    private CourseService courseService;
    
    @Autowired
    private DepartmentService departmentService;

    //get user register form
    @GetMapping("/createUser")
    public String createUser(Model model) throws Exception {
    	model.addAttribute("departments", departmentService.fetchAllDepartments());
    	model.addAttribute("content", "register");
    	return "sidebar";
    }
    
    
    // create new USer
    @PostMapping("/createUser")
    public String createUser(@ModelAttribute Users user, Model model) {
        try {
            Users newUser = userService.addNewUser(user);
           
            if(newUser.getRole().equals(Role.STUDENT)) {
            	studentService.addStudent(newUser);
            	
            }else if(newUser.getRole().equals(Role.FACULTY)) {
            	facultyService.addFaculty(newUser);
            
            }
            model.addAttribute("successMessage", "User created successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Cannot register user: " + e.getMessage());
        }
        model.addAttribute("content", "register");
        
        return "sidebar";  
    }

    // Delete user by userID
    @GetMapping("/deleteUser/userId/{userId}")
    public String deleteUser(@PathVariable Long userId, Model model) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            model.addAttribute("successMessage", "User deleted successfully!");
        } else {
            model.addAttribute("errorMessage", "User not found!");
        }
        return "register";
    }

    //Update user by email
//    @PostMapping("/updateUser/email/{email}")
//    public String updateUserByEmail(@PathVariable String email, @ModelAttribute Users user, Model model) {
//        try {
//            Users oldUser = userService.findByEmail(email);
//            if (oldUser == null) throw new Exception("Cannot find user by this email");
//            Users updatedUser = userService.updateUser(oldUser, user);
//            model.addAttribute("user", userMapper.usersToDTO(updatedUser));
//            model.addAttribute("successMessage", "User updated successfully!");
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", e.getMessage());
//        }
//        return "register";
//    }

    // update user by userID
//    @PostMapping("/updateUser/userId/{userId}")
//    public String updateUserById(@PathVariable Long userId, @ModelAttribute Users user, Model model) {
//        try {
//            Users oldUser = userService.findByUserId(userId);
//            if (oldUser == null) throw new Exception("Cannot find user by this userId");
//            Users updatedUser = userService.updateUser(oldUser, user);
//            model.addAttribute("user", userMapper.usersToDTO(updatedUser));
//            model.addAttribute("successMessage", "User updated successfully!");
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", e.getMessage());
//        }
//        return "register";
//    }

    // search user by email
    @GetMapping("/findUser/email/{email}")
    public String findUserByEmail(@PathVariable String email, Model model) {
        try {
            Users oldUser = userService.findByEmail(email);
            if (oldUser == null) throw new Exception("Cannot find user by this email");
            model.addAttribute("user", userMapper.usersToDTO(oldUser));
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "register";
    }

    
    // find user by userId
    @GetMapping("/findUser/userId/{userId}")
    public String findUserById(@PathVariable Long userId, Model model) {
        try {
            Users oldUser = userService.findByUserId(userId);
            if (oldUser == null) throw new Exception("Cannot find user by this userId");
            model.addAttribute("user", userMapper.usersToDTO(oldUser));
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "register";
    }

    
   

    // fetch all the users
    @GetMapping("/fetchAllUsers")
    public String fetchAllUsers(Model model) {
        List<Users> userList = userService.fetchAllUsers();
        if (userList != null && !userList.isEmpty()) {
            model.addAttribute("users", userMapper.usersToDTOs(userList));
        } else {
            model.addAttribute("errorMessage", "No users found.");
        }
        return "";
    }
    
    
    // get data for adminHome
    @GetMapping("/adminHomeData")
    public String getAdminHomeData(Model model) {
    	Long totalStudents= studentService.getStudentCount();
    	Long totalFaculies= facultyService.getFacultyCount();
    	Long totalCourses= courseService.getCourseCount();
    	HashMap<String,Integer> studentEnrollment= courseService.getMostPopularCourses(3);
    	model.addAttribute("topNCourse", studentEnrollment);
    	
    	model.addAttribute("studentCount", totalStudents);
    	model.addAttribute("facultyCount", totalFaculies);
    	model.addAttribute("courseCount", totalCourses);
    	
    	model.addAttribute("content", "AdminDashboard");
    	
    	return "sidebar";
    }
    
}
