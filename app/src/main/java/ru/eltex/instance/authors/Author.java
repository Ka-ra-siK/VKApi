package ru.eltex.instance.authors;

/**
 * Автор поста
 */
public class Author {

    Integer id;
    String name;
    String photo50;
    String photo100;

    public Author(Integer id, String name, String photo50, String photo100) {
        this.id = id;
        this.name = name;
        this.photo50 = photo50;
        this.photo100 = photo100;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto50() {
        return photo50;
    }

    public void setPhoto50(String photo50) {
        this.photo50 = photo50;
    }

    public String getPhoto100() {
        return photo100;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }
}
