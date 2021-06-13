package com.kiteiru;

public class Main {
    public static void main(String[] args) {
        try {
            Model model = new Model();
            View view = new View(model);
            model.Play();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}



