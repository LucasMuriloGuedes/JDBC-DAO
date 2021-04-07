/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entites.Department;

/**
 *
 * @author Lucas Murilo
 */
public interface DepartmentDao {
    
    void insert(Department department);
    void update(Department department);
    void deleteById(int id);
    Department findById(int id);
    List<Department> findAll();
    
    
}
