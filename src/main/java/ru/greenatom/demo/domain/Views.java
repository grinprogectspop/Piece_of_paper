package ru.greenatom.demo.domain;

public final class Views {
    public interface Id {}

    public interface IdName extends Id {}

    public interface documents extends IdName {}

    public interface documentTypes extends IdName {}
}