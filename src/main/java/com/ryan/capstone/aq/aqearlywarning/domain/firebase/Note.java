package com.ryan.capstone.aq.aqearlywarning.domain.firebase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Note {
    private String subject;
    private String content;
    private String topic;
    private Map<String, String> data;

    public Note(String subject, String content, String topic, Map<String, String> data) {
        this.subject = subject;
        this.content = content;
        this.topic = topic;
        this.data = data;
    }

    public Note(String subject, String content, String topic) {
        this.subject = subject;
        this.content = content;
        this.topic = topic;
        this.data = new HashMap<String, String>();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Note{" +
                "subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", topic='" + topic + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(subject, note.subject) && Objects.equals(content, note.content) && Objects.equals(topic, note.topic) && Objects.equals(data, note.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, content, topic, data);
    }
}
