package com.example.quiz_poke.vo;

public class PokemonVo {

    /* 画像名 */
    private String image;
    /* 名前 */
    private String name;
    /* タイプ1 */
    private String type_1;
    /* タイプ2 */
    private String type_2;
    /* 特性1 */
    private String characteristic_1;
    /* 特性2 */
    private String characteristic_2;
    /* 夢特性 */
    private String characteristic_dream;
    /* 分類 */
    private String species;
    /* 最も使用率の高い技 */
    private String most_used_move;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_1() {
        return type_1;
    }

    public void setType_1(String type_1) {
        this.type_1 = type_1;
    }

    public String getType_2() {
        return type_2;
    }

    public void setType_2(String type_2) {
        this.type_2 = type_2;
    }

    public String getCharacteristic_1() {
        return characteristic_1;
    }

    public void setCharacteristic_1(String characteristic_1) {
        this.characteristic_1 = characteristic_1;
    }

    public String getCharacteristic_2() {
        return characteristic_2;
    }

    public void setCharacteristic_2(String characteristic_2) {
        this.characteristic_2 = characteristic_2;
    }

    public String getCharacteristic_dream() {
        return characteristic_dream;
    }

    public void setCharacteristic_dream(String characteristic_dream) {
        this.characteristic_dream = characteristic_dream;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getMost_used_move() {
        return most_used_move;
    }

    public void setMost_used_move(String most_used_move) {
        this.most_used_move = most_used_move;
    }
}
