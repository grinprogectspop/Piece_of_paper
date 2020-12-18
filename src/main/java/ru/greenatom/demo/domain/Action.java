package ru.greenatom.demo.domain;


public enum Action {
    READ(null), WRITE(null), SAVE(null), DELETE(null);


    private User user;


    Action(User user) {
    }

    Action() {

    }

    public void setUser(User user) {
    this.user =user;
    }


}
