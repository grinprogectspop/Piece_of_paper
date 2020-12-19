# Piece of paper
1. [Примечания](#Примечания)
2. [swagger](#swagger)



# Примечания

1. Создаём БД `pop` с учётной записью `username=root`, `password=root`. Создание пользователя root:

CREATE ROLE root LOGIN PASSWORD 'root'; CREATE DATABASE root; ALTER DATABASE root OWNER TO root;

Права суперпользователя для root:
ALTER USER root WITH SUPERUSER;

2. Файл для загрузки файлов создастся автоматически

Внимание кто на linux надо проверить работает ли `file:///` для получения файлов.

Ссылки:

1. [ECM.Spec.Req]( https://docs.google.com/document/d/1Cl_ODNYa6SD3kMDGC9Oe_ZAThl4ZbTUfSrDesAwvpw0/edit)
2. [API]( https://docs.google.com/document/d/1a_dS6EJJqt3a9z35XfsW9CUnLNWlNTuIuOnUZhHX0q0/edit?usp=sharing)
3. [Доска задач](https://trello.com/invite/b/a5RVPYil/0a8bf0486a2dfc4731471282234dd1f8/%D0%BF%D1%80%D0%BE%D1%8D%D0%BA%D1%82)
4. [ER-диаграмма]( https://yadi.sk/i/Z290e3R4JSs5-Q)

# swagger

## Для избежания ошибок

```
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.springframework.cglib.core.ReflectUtils (file:/C:/Users/Bkmz/.m2/repository/org/springframework/spring-core/5.3.1/spring-core-5.3.1.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
WARNING: Please consider reporting this to the maintainers of org.springframework.cglib.core.ReflectUtils
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
```

### Использовать эти параметры:

```properties  
--add-opens java.base/java.lang=ALL-UNNAMED 
```

или

```properties
java --add-opens java.base/java.lang=ALL-UNNAMED -jar target/*.jar
```

Это происходит потому, что новая модульная система JDK 9 обнаружила незаконный доступ, который будет запрещен
когда-нибудь в ближайшем будущем. Более подробно по [ссылке](https://docs.oracle.com/javase/9/migrate/toc.htm#JSMIG-GUID-2F61F3A9-0979-46A4-8B49-325BA0EE8B66).