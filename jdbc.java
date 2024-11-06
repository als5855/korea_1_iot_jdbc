//JDBC (Java Database Connectivity)
// : 자바에서 관계형 데이터베이스 사이에 통신을 담당하는 인터페이스
// - SQL 쿼리를 실행, 데이터를 처리하기위한 API
// - 플랫폼에 독립적인 인터페이스
// - JDBC API는 DB의 테이블에서 데이터를 CRUD하는 작업을 수행하도록 도와줌

//? JDBC의 구조
// 1. JDBC Driver Manger
// : 데이터베이스와 연결을 설정하는 데 사용

// 2. JDBC Driver
// : 각 DBMS에 맞는 드라이버를 통해 잡와 통신

// 3. Connection 객체
// : DB와 연결을 유지하는 객체
// : 쿼리 실행에 필요 + 연결 종료 시 반드시 닫아야 함

// 4. Statement 객체
// : SQL 쿼리를 실행하는 데 사용

// 5. ResultSet 객체
// : 쿼릴과 결과를 저장하고, 데이터를 탐색할 수 있도록 하는 객체

// ?  JDBC & JPA & Spring의 차이
// 1. JDBC
// : SQL을 직접 작성하여 데이터베이스와 상호작용하는 방식
// > SQL을 직접 작성하기 때문에 코드이 복잡성이 증가, 유지보수 어려움

// 2. JPA (Java Persistence API)
// : 객체지향적으로 DB와 상호작용하는 방법
// : SQL을 직접 작성하기 X, 엔터티 객체를 통해 DB와 상호작용

// 3. Spring Framevork
// : JDBC와 JPA를 더 쉽게 처리하기 위한 템플릿







