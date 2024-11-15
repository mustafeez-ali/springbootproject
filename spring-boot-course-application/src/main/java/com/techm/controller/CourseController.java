package com.techm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.techm.model.Course;
import com.techm.service.CourseService;

@RestController
@CrossOrigin
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/courses")
	private List<Course> getAllBooks() {
		System.out.println("TEST");
		return courseService.getAllBooks();
	}

//creating a get mapping that retrieves the detail of a specific book
	@GetMapping("/course/{courseId}")
	private List<Course> getBooks(@PathVariable("courseId") int bookid) {
		 Course course=courseService.getBooksById(bookid);
		 System.out.println("Course::"+course);
		 List<Course> courseList=new ArrayList<>();
		 courseList.add(course);
		 return courseList;
	}

//creating a delete mapping that deletes a specified book
	@DeleteMapping("/course/{courseId}")
	private void deleteBook(@PathVariable("courseId") int courseId) {
		System.out.println("deleting"+courseId);
		courseService.delete(courseId);
	}

//creating post mapping that post the book detail in the database
	@PostMapping("/course")
	private int saveBook(@RequestBody Course course) {
		System.out.println("Saving course to db"+course);
		courseService.saveOrUpdate(course);
		return course.getCourseId();
	}

//creating put mapping that updates the book detail 
	@PutMapping("/course")
	private Course update(@RequestBody Course course) {
		courseService.saveOrUpdate(course);
		return course;
	}
	

}
