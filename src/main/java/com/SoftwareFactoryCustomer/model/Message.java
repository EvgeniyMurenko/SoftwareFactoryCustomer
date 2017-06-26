package com.SoftwareFactoryCustomer.model;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "s_messages")
public class Message implements Serializable {

    public Message() {
    }

    public Message(Case aCase, User user, Date messageTime, String messageText, String isRead, Set<MessageLink> messageLinks) {
        this.aCase = aCase;
        this.user = user;
        this.messageTime = messageTime;
        this.messageText = messageText;
        this.isRead = isRead;
        this.messageLinks = messageLinks;
    }

    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "message_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case aCase;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "message_time", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date messageTime;

    @Column(name = "message_text")
    @Type(type = "text")
    private String messageText;

    @Column(name = "is_read")
    private String isRead;


    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<MessageLink> messageLinks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Case getaCase() {
        return aCase;
    }

    public void setaCase(Case aCase) {
        this.aCase = aCase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Set<MessageLink> getMessageLinks() {
        return messageLinks;
    }

    public void setMessageLinks(Set<MessageLink> messageLinks) {
        this.messageLinks = messageLinks;
    }
}
