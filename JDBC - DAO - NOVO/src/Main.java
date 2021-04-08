
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entites.Seller;

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
        
        

    }
    
}
