package wjc920.demo.java.util;

public class Message {

    private String body;
    private Long delay;

    public Message(String body, Long delay) {
        super();
        this.body = body;
        this.delay = delay;
    }

    public Message() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

}
