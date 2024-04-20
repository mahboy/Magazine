package com.example.magazine.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_posts")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime create;

    @Transient
    @JsonIgnore
    private MultipartFile file;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    @JsonIgnore
    private Users user;

    @NotNull
    private String subject;

    @NotBlank
    @Size(min = 4,max = 15)
    private String title;
    @NotBlank
    @Size(min = 100)
    private String body;
    @NotBlank
    @Size(min = 15,max = 150)
    private String smallBody;
    private String photo;

    public Posts() {
    }

    public Posts(Long id) {
        this.id = id;
    }

    public Posts(String subject, String title, String body, String photo) {
        this.subject = subject;
        this.title = title;
        this.body = body;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreate() {
        return create;
    }

    public void setCreate(LocalDateTime create) {
        this.create = create;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSmallBody() {
        return smallBody;
    }

    public void setSmallBody(String smallBody) {
        this.smallBody = smallBody;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users users) {
        this.user = users;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
