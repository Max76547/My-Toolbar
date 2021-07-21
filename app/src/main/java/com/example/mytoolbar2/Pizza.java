package com.example.mytoolbar2;

//класс для добавления массива с текстом и картинкой
public class Pizza {

    private String name;
    private int imageRecourceId;

    //создаем массив из текста и картинок
    public static final Pizza[] pizzas = {
            new Pizza("Грибная", R.drawable.diavolo),
            new Pizza("4 сыра", R.drawable.funghi)
    };

    //конструктор на получение ссыллки
    private Pizza(String name, int imageRecourceId) {
        this.name = name;
        this.imageRecourceId = imageRecourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageRecourceId;
    }

}

