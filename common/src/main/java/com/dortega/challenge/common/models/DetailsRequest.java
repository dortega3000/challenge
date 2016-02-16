package com.dortega.challenge.common.models;

/**
 * Created by dortega on 2/15/16.
 */
public class DetailsRequest {
    public String title;
    public QueueConfiguration replyQueue;


    public DetailsRequest() {
    }

    public DetailsRequest(String title, QueueConfiguration replyQueue) {
        this.title = title;
        this.replyQueue = replyQueue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QueueConfiguration getReplyQueue() {
        return replyQueue;
    }

    public void setReplyQueue(QueueConfiguration replyQueue) {
        this.replyQueue = replyQueue;
    }
}
