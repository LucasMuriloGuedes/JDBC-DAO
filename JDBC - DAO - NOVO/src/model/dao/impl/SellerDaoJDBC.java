/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.dao.SellerDao;
import model.entites.Department;
import model.entites.Seller;

/**
 *
 * @author Lucas Murilo
 */
public class SellerDaoJDBC implements SellerDao {
    
    private Connection conn;
    
    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }
    

    @Override
    public void insert(Seller seller) {
        PreparedStatement st = null;
        
        try{
            st = conn.prepareStatement(
                    
                    "INSERT INTO seller "
                    +"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    +"VALUES "
                    +"(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartament().getId());
            
            int rowsAffected = st.executeUpdate();
            
            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    seller.setId(id);
                }
                DB.closeResuldSet(rs);
            }
            else{
                throw new DbException("Unexpected error! No rows affected1 ");
            }
            
       
        }
         catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        
        finally{
            DB.closeStatement(st);
           
        }
        
    }

    @Override
    public void update(Seller seller) {
         PreparedStatement st = null;
        
        try{
            st = conn.prepareStatement(                   
                    "UPDATE seller "
                    + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                    + "WHERE Id = ?");
            
            st.setString(1, seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartament().getId());
            st.setInt(6, seller.getId());
            
            st.executeUpdate();       
       
        }
         catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        
        finally{
            DB.closeStatement(st);
           
        }
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Seller findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");
            
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Department dep = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
                Seller seller = instatiateSelller(rs, dep);
                return seller;
                
            }
            return null;
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResuldSet(rs);
        }
        
        
        
    }

    @Override
    public List<Seller> findAll() {
        
         
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY Name ");
            
           
            
            rs = st.executeQuery();
            
            List<Seller> list = new ArrayList<>();
            
            Map<Integer, Department> map = new HashMap<>();
             
            while (rs.next()){
                
                Department dep = map.get(rs.getInt("DepartmentId"));
                
                if(dep == null){
                dep = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
                map.put(rs.getInt("DepartmentId"), dep);
                }
                
                
                Seller seller = instatiateSelller(rs, dep);
                list.add(seller);
                
            }
            return list;
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResuldSet(rs);
        }
        
    }
        
    
    
    private Seller instatiateSelller(ResultSet rs, Department dep) throws SQLException{
        
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartament(dep);
        return obj;

    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name ");
            
            st.setInt(1, department.getId());
            
            rs = st.executeQuery();
            
            List<Seller> list = new ArrayList<>();
            
            Map<Integer, Department> map = new HashMap<>();
             
            while (rs.next()){
                
                Department dep = map.get(rs.getInt("DepartmentId"));
                
                if(dep == null){
                dep = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
                map.put(rs.getInt("DepartmentId"), dep);
                }
                
                
                Seller seller = instatiateSelller(rs, dep);
                list.add(seller);
                
            }
            return list;
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResuldSet(rs);
        }
        
    }
    
}
