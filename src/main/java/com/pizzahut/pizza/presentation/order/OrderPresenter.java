/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzahut.pizza.presentation.order;

import com.pizzahut.util.Icons;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;

/**
 *
 * @author abdul
 */
public class OrderPresenter implements Initializable{
    
    // order info
    @FXML private Label              orderPizzaNowLbl;
    @FXML private TextField         nameTxtField;
    @FXML private TextField         phoneTxtField;
    @FXML private TextField         addressTxtField;
    @FXML private Label               pizzaSizeLbl;
    @FXML private Label               crustLbl;
    @FXML private Label               toppingsLbl;
    @FXML private ToggleButton  smallSizeTogBtn;
    @FXML private ToggleButton  mediumSizeTogBtn;
    @FXML private ToggleButton  largeSizeTogBtn;
    @FXML private ToggleButton  thinCurstTogBtn;
    @FXML private ToggleButton  thickCurstTogBtn;
    @FXML private ToggleButton  peperooniTogBtn;
    @FXML private ToggleButton  mushroomsTogBtn;
    @FXML private ToggleButton  anchoviesTogBtn;
    @FXML private Button            saveBtn;
    @FXML private Button            cancelBtn;
    
    // toggle group for size and crust
    ToggleGroup sizeToggleGroup;
    ToggleGroup crustToggleGroup;
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // save button disable binding
        this.saveBtn.disableProperty().bind(getSaveButtonDisableBinding());
        
        // init toggles group
        sizeToggleGroup = new ToggleGroup();
        crustToggleGroup = new ToggleGroup();
        // add pizza size toggle buttons to size toggle group
        sizeToggleGroup.getToggles().add(smallSizeTogBtn);
        sizeToggleGroup.getToggles().add(mediumSizeTogBtn);
        sizeToggleGroup.getToggles().add(largeSizeTogBtn);
        // add crust toggle buttons to crust toggle group
        crustToggleGroup.getToggles().add(thinCurstTogBtn);
        crustToggleGroup.getToggles().add(thickCurstTogBtn);
      
    }

    // initi dialogs
    public void initDialogs(ResourceBundle bundle){
        // new order fields
        orderPizzaNowLbl.setText(bundle.getString("orderPizzaNow"));
        nameTxtField.setPromptText(bundle.getString("enterName"));
        phoneTxtField.setPromptText(bundle.getString("enterPhoneNo"));
        addressTxtField.setPromptText(bundle.getString("enterAddress"));
        pizzaSizeLbl.setText(bundle.getString("pizzaSize"));
        smallSizeTogBtn.setText(bundle.getString("smallPizza"));
        mediumSizeTogBtn.setText(bundle.getString("mediumPizza"));
        largeSizeTogBtn.setText(bundle.getString("largePizza"));
        crustLbl.setText(bundle.getString("pizzaCrust"));
        thinCurstTogBtn.setText(bundle.getString("thinPizza"));
        thickCurstTogBtn.setText(bundle.getString("thickPizza"));
        toppingsLbl.setText(bundle.getString("pizzaToppings"));
        peperooniTogBtn.setText(bundle.getString("pepperoni"));
        mushroomsTogBtn.setText(bundle.getString("mushrooms"));
        anchoviesTogBtn.setText(bundle.getString("anchovies"));
        saveBtn.setText(Icons.SAVE + bundle.getString("saveButton"));
        cancelBtn.setText(Icons.QUIT + bundle.getString("cancelButton"));
      
    }
    
    // save button disable binding
    BooleanBinding getSaveButtonDisableBinding(){
        BooleanBinding customerDetailsEntered = getCustomerDetailsBinding();
        BooleanBinding pizzaDetailsSelected = getPizzaInfoBinding();
        return customerDetailsEntered.and(pizzaDetailsSelected).not();
    }
    
    // binding customer details fields
    BooleanBinding getCustomerDetailsBinding(){
        return nameTxtField.textProperty().isNotEmpty()
                .and(phoneTxtField.textProperty().isNotEmpty())
                .and(addressTxtField.textProperty().isNotEmpty());
    }
    
    // binding pizza size selections
    BooleanBinding getPizzaInfoBinding(){
        return smallSizeTogBtn.selectedProperty()
                .or(mediumSizeTogBtn.selectedProperty())
                .or(largeSizeTogBtn.selectedProperty())
                .and(thinCurstTogBtn.selectedProperty())
                .or(thickCurstTogBtn.selectedProperty())
                .and(peperooniTogBtn.selectedProperty())
                .or(mushroomsTogBtn.selectedProperty())
                .or(anchoviesTogBtn.selectedProperty());
    }
 
}
