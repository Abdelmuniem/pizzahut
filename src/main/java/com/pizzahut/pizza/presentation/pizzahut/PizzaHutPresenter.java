/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzahut.pizza.presentation.pizzahut;

import com.pizzahut.pizza.PizzaHutResourceBundle;
import com.pizzahut.pizza.presentation.order.OrderPresenter;
import com.pizzahut.pizza.presentation.order.OrderView;
import com.pizzahut.util.Icons;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;

/**
 *
 * @author abdul
 */
public class PizzaHutPresenter  implements Initializable{
    
    @FXML private AnchorPane        mainWindow;
    @FXML private Button                newOrderBtn;
    @FXML private Button                listOrdersBtn;
    @FXML private AnchorPane        leftView;
    @FXML private AnchorPane        rightView;
    @FXML private ToggleButton      englishBtn;
    @FXML private ToggleButton      arabicBtn;
    @FXML private Menu                  fileMenu;
    @FXML private Menu                  editMenu;
    @FXML private Menu                  toolsMenu;
    @FXML private Menu                  helpMenu;
    @FXML private MenuItem           closeMenItem;
    @FXML private MenuItem           optionsMenItem;
    @FXML private MenuItem           copyMenItem;
    @FXML private MenuItem           cutMenItem;
    @FXML private MenuItem           pastMenItem;
    @FXML private MenuItem           deleteMenItem;
    
    private ToggleGroup toggleGroup;
    
    // Order View
    private OrderView orderView;
    private OrderPresenter orderPresenter;
    
    // resource bundle,
    ResourceBundle bundle;
    // pizza hut app resource bundle
    @Inject
    PizzaHutResourceBundle pizaaResourceBundle;
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
//        // get default bundle
//       this.bundle = this.pizaaResourceBundle
//               .getBundles(Locale.US);
        
          // create new order view instance
        orderView = new OrderView();
        orderPresenter = (OrderPresenter) orderView.getPresenter();
       
        // set the default language
        toggleGroup = new ToggleGroup();
        // add languages toggles
        // add english language toggle and set it as default
        englishBtn.setUserData("En");
        toggleGroup.getToggles().add(englishBtn);
        toggleGroup.selectToggle(englishBtn);
        // add arabic toggle
        arabicBtn.setUserData("Ar");
        toggleGroup.getToggles().add(arabicBtn);
        
        // add user prefrence language
        toggleGroup.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> observable, 
                        Toggle oldValue, Toggle newValue) -> {
                  
            if (newValue.getUserData().equals("En")) {
                // load english resource bundle
                System.out.printf("%s%n", "loading English resource Bundle");
                setMainWindowOrientation("en");
                 initDialogs("en");
            }
            
            if (newValue.getUserData().equals("Ar")) {
                // load arabic resource bundle
                System.out.printf("%s%n", "loading Arabic resource Bundle");
                setMainWindowOrientation("ar");
                initDialogs("ar");
            }
        });
       
       
        // new order button action, 
        // open order view, in right anchore pane
        newOrderBtn.setOnAction((e) -> {
            // add order view to right anchore pane
            if (!rightView.getChildren().contains(orderView.getView()) ){
                System.out.printf("%s%n", "Order View added to right view");
                // set language preference, bundle
                rightView.getChildren().add(orderView.getView());
            }
        });
        
        // change the language
    }
    
    private void initDialogs(String locale){
  
         this.bundle = ResourceBundle
                 .getBundle("bundle.PizzaHutResourceBundle",
                     new Locale(locale));
         
          // menu bar
         fileMenu.setText(this.bundle.getString("file"));
         editMenu.setText(this.bundle.getString("edit"));
         toolsMenu.setText(this.bundle.getString("tools"));
         helpMenu.setText(this.bundle.getString("help"));
         
        // first menu items
        closeMenItem.setText(Icons.EJECT + this.bundle.getString("close"));
        deleteMenItem.setText(this.bundle.getString("delete"));
        copyMenItem.setText(Icons.COPY + this.bundle.getString("copy"));
        cutMenItem.setText(Icons.CUT + this.bundle.getString("cut"));
        pastMenItem.setText(Icons.PASTE + this.bundle.getString("paste"));
        optionsMenItem.setText(Icons.SETTING + this.bundle.getString("options"));
        // order buttons
        newOrderBtn.setText(this.bundle.getString("newOrder"));
        listOrdersBtn.setText(this.bundle.getString("listOrders"));
       // init order dialog 
        orderPresenter.initDialogs(this.bundle);
    }
    // change orientation
    private void setMainWindowOrientation(String lang) {
        if (lang.equals("en")) {
            this.mainWindow
                    .setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
        if (lang.equals("ar")) {
            this.mainWindow
                    .setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
    }
    
}
