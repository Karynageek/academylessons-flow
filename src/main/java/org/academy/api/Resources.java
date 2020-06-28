package org.academy.api;

import org.academy.PropertyReader;

public class Resources {
    private static String read(String value) {
        return PropertyReader.get().readValue(value);
    }
    public static String getIssueRequests() {
        return read("issues_requests");
    }
}
