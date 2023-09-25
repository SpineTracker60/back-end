package com.spinetracker.spinetracker.domain.posture.command.domain.service;

public interface RequestPostFcmMessage {

    boolean send(String title, String body, String receiverToken);
}
