package ru.eltex.instance.authors;

/**
 * Класс в случае если автором является группа
 */
public class GroupAuthor extends Author {

    String photo200;


    public GroupAuthor(Integer id, String name, String photo50, String photo100, String photo200) {
        super(id, name, photo50, photo100);
        this.photo200 = photo200;
    }
}
