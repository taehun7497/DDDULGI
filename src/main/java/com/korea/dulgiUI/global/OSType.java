package com.korea.dulgiUI.global;

import lombok.Getter;

public enum OSType {
    Window("C:/temp", "file:///c:/Temp/"),
    Linux("/home/ubuntu/dulgiUI/data", "file:/home/ubuntu/dulgiUI/data/")
    //
    ;
    @Getter
    private final String loc;
    @Getter
    private final String resourceHandler;

    OSType(String loc, String resourceHandler) {
        this.loc = loc;
        this.resourceHandler = resourceHandler;
    }

    public static OSType getInstance() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win"))
            return Window;
        if (osName.contains("linux"))
            return Linux;
        else {
            System.out.println(osName);
            return null;
        }
    }
}
