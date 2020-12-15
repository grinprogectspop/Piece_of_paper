# Piece of paper
Примечания:
1. Создаём БД `pop` с учётной записью `username=root`, `password=root`.
Создание пользователя root:

  CREATE ROLE root LOGIN PASSWORD 'root';
  CREATE DATABASE root;
  ALTER DATABASE root OWNER TO root;

Права суперпользователя для root:
  ALTER USER root WITH SUPERUSER;
  
2. Файл для загрузки файлов создастся автоматически

Внимание кто на linux надо проверить работает ли `file:///` для получения файлов.

Ссылки:

1. ECM.Spec.Req https://docs.google.com/document/d/1Cl_ODNYa6SD3kMDGC9Oe_ZAThl4ZbTUfSrDesAwvpw0/edit
2. API: https://docs.google.com/document/d/1a_dS6EJJqt3a9z35XfsW9CUnLNWlNTuIuOnUZhHX0q0/edit?usp=sharing
3. Доска задач: https://trello.com/invite/b/a5RVPYil/0a8bf0486a2dfc4731471282234dd1f8/%D0%BF%D1%80%D0%BE%D1%8D%D0%BA%D1%82
