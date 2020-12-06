package ru.greenatom.demo.domain;
@Deprecated
public final class Views {
    public interface Id {}

    public interface IdName extends Id {}

    public interface FullProfile extends IdName{ }
    /*
    * защита от цикла
    * при получении данных из User или Role может возникнуть цык,
    * что User -> Role -> User или Role -> User -> Role
    * */
    public interface UserProfile extends FullProfile{ }
    public interface RoleProfile extends FullProfile{ }
}
