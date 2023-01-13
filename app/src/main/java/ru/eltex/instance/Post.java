package ru.eltex.instance;

import java.util.List;

import ru.eltex.api_service.api_service_news.body.items.VKNewsAttachments;
import ru.eltex.instance.authors.Author;

/**
 * Класс с основной выводимой информацией
 */
public class Post {

    Integer id;
    Author author;
    String date;
    String textPost;
    List<VKNewsAttachments> content;
    Integer likes;
    Integer reposts;

    public Post(Integer id, Author author, String date, String textPost, List<VKNewsAttachments> content, Integer likes, Integer reposts) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.textPost = textPost;
        this.content = content;
        this.likes = likes;
        this.reposts = reposts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String textPost) {
        this.textPost = textPost;
    }

    public List<VKNewsAttachments> getContent() {
        return content;
    }

    public void setContent(List<VKNewsAttachments> content) {
        this.content = content;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getReposts() {
        return reposts;
    }

    public void setReposts(Integer reposts) {
        this.reposts = reposts;
    }
}
