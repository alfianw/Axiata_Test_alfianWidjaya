File ini adalah konfigurasi Maven (pom.xml) untuk project Spring Boot "myboostTestMuhamadAlfianWidjaya".
Berikut penjelasan singkat tiap bagian:

1. Project Information
   - groupId: com.axiata
   - artifactId: myboostTestMuhamadAlfianWidjaya
   - version: 0.0.1-SNAPSHOT
   - description: Membuat REST API CRUD untuk Purchase Order

2. Java Version
   - Menggunakan Java 21

3. Dependencies
   - spring-boot-starter-data-jpa : Untuk ORM dan akses database dengan Hibernate/JPA
   - spring-boot-starter-web      : Untuk membuat REST API berbasis Spring MVC
   - spring-boot-devtools         : Membantu development (auto reload, hot swap)
   - mysql-connector-j            : Driver MySQL
   - lombok                       : Mengurangi boilerplate code (getter, setter, dll.)
   - spring-boot-starter-test     : Library untuk testing (JUnit, Mockito, dll.)
   - modelmapper                  : Untuk mapping object DTO <-> Entity
   - spring-boot-starter-security : Menambahkan fitur security dasar
   - jjwt-api, jjwt-impl, jjwt-jackson : Library JWT untuk autentikasi
   - spring-security-crypto       : Untuk hashing password dengan BCrypt

4. Build Plugins
   - maven-compiler-plugin        : Mengatur compiler dan annotation processor (Lombok)
   - spring-boot-maven-plugin     : Membantu build dan menjalankan aplikasi Spring Boot

Kesimpulan:
File ini mendefinisikan struktur dan dependency project Spring Boot
dengan fitur utama:
- REST API CRUD Purchase Order
- Database MySQL dengan JPA/Hibernate
- Security dengan JWT
- Utility tambahan (ModelMapper, Lombok, DevTools, Testing)
