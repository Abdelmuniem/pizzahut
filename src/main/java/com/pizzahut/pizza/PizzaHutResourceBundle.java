/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzahut.pizza;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author abdul
 */
public class PizzaHutResourceBundle {
    
    private Locale locale;
    

    public PizzaHutResourceBundle() {
        
        // set default locale US
        this.locale = Locale.US;
    }

    public PizzaHutResourceBundle(Locale locle) {
        this.locale = locle;
    }
    
    public ResourceBundle getBundles(Locale locale){
        
        return ResourceBundle.getBundle("PizzaHutResourceBundle", locale);
    }  
}
