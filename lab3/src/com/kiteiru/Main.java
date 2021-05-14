package com.kiteiru;

public class Main {
    public static void main(String[] args) {
        try {
            Model model = new Model();
            model.Play();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
