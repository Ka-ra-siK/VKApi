package ru.eltex.instance.authors;


/**
 * Класс в случае если автором является человек
 */
public class ProfileAuthor extends Author {

    String lastName;
    Integer sex;

    public ProfileAuthor(Integer id, String name, String lastName, Integer sex, String photo50, String photo100) {
        super(id, name, photo50, photo100);
        this.lastName = lastName;
        this.sex = sex;
    }
}
