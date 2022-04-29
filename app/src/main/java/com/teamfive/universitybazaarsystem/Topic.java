package com.teamfive.universitybazaarsystem;

public class Topic {
    private String Topicname;
    private String Topiccategory;
    private String TopicAuthor;
    private String Bodytext;

    public Topic() {
    }
    public Topic(String name, String category, String Author, String body)
    {
        this.Topicname = name;
        this.Topiccategory = category;
        this.TopicAuthor = Author;
        this.Bodytext = body;
    }

    public String getTopicname() {
        return Topicname;
    }

    public void setTopicname(String topicname) {
        Topicname = topicname;
    }

    public String getTopiccategory() {
        return Topiccategory;
    }

    public void setTopiccategory(String topiccategory) {
        Topiccategory = topiccategory;
    }

    public String getTopicAuthor() {
        return TopicAuthor;
    }

    public void setTopicAuthor(String topicAuthor) {
        TopicAuthor = topicAuthor;
    }

    public String getBodytext() {
        return Bodytext;
    }

    public void setBodytext(String bodytext) {
        Bodytext = bodytext;
    }


}
