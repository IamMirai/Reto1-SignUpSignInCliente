/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import datatransferobject.Model;

/**
 * Model class factory.
 * @author haize
 */
public class ModelFactory {
        private static Model model;
    
    public static Model getModel() {
        if (model == null) {
            model = new DAO();
        }
        return model;
    }
}
