package com.example.TastyTrove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("southIndian")
public class SouthIndian implements Recipe {
    private String name ="SouthIndian";
    private String userName;

    @Autowired
    @Qualifier("lentils")
    private Ingredients ingredients;

    @Override
    public void setIngredients(String ingredients) {
        // This method is for generic approach
    }

    @Override
    public void setUserName(String name) {
        this.userName = name;
    }

    @Override
    public void getDetails() {
        this.ingredients.setIngredient(name);
        String dishName = this.ingredients.getDishDetail();
        List<String> ingredients = this.ingredients.getIngredientsDetail();
        System.out.println("Hello user " + this.userName + " we suggest you to make " + dishName + " you can use the following ingredients:");
        System.out.println("Ingredients:");
        for(int i = 0; i < ingredients.size(); i++){
            System.out.println(i + 1 + ". " + ingredients.get(i));
        }
    }
}