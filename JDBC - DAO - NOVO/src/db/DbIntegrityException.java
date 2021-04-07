/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author Lucas Murilo
 */
public class DbIntegrityException extends RuntimeException{
    
    private static final long serialVersionUID = 1l;
    public DbIntegrityException(String msg){
        super(msg);
    }
    
}
