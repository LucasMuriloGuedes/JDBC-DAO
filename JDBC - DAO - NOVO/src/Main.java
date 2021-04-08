

import java.util.List;
import javafx.scene.control.Slider;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entites.Department;
import model.entites.Seller;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas Murilo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("==== TESTE 1: seller findById === ");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        
        System.out.println("==== TESTE 2: seller findByIdDepartment === ");
        Department dep = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(dep);
        
        for(Seller c: list){
            System.out.println(c);
        }
        
         System.out.println("==== TESTE 3: seller findAAll === ");
        
        list = sellerDao.findAll();
        
        for(Seller c: list){
            System.out.println(c);
        }
        
        System.out.println("=== TESTE 4: seller insert");
        Seller newSeller;
        newSeller = new Seller(null, "Lorenzo", "lorenzo@gmail.com",  new Date(), 4000.0, dep);
        sellerDao.insert(newSeller);
        System.out.println("Insert! New id = " + newSeller.getId());
        
        System.out.println("=== TESTE 5: seller update");
        seller = sellerDao.findById(1);
        seller.setName("Izabel Cristina");
        sellerDao.update(seller);
        System.out.println("Update completed!");
        
        System.out.println("=== TESTE 6: seller update");
        sellerDao.deleteById(9);
        System.out.println("Deletado!");
        
        

    }
    
}
