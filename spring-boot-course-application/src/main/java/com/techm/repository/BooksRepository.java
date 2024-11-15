package com.techm.repository;
import org.springframework.data.repository.CrudRepository;

import com.techm.model.Course;
public interface BooksRepository extends CrudRepository<Course, Integer>
{
}
