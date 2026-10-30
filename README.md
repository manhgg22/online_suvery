# Online Survey

Ứng dụng khảo sát trực tuyến viết bằng Java web (Servlet/JSP), dự án NetBeans dùng Ant.

## Cấu trúc

```
src/          # mã Java (servlet, model, DAO)
web/          # JSP, CSS, JavaScript
lib/          # thư viện JAR
nbproject/    # cấu hình NetBeans
build.xml     # script build Ant
```

## Chạy

Mở bằng NetBeans rồi Run, hoặc build thủ công:

```bash
ant clean dist
```

Deploy file WAR sinh ra trong `dist/` lên Tomcat.
