## 👍2019 UCSD QI Summer IoT Camp Team A - Web Part

## 🦘Project Name : Kangaroo

#### 📒Information

* 웹 개발자 : 조명근 
* 개발기간 : 총 5주
* 사용 기술 스택
  * Back-end
    * `PHP Slim Framework`
    * `Ubuntu`
    * `Mysql`
    * `Apache Tomcat`
  * Front-end
    * `HTML, CSS,JavaScript, JQuery `
    * `Google Map API, Google Chart, AM Chart, Apex Chart`
* [다녀온 후기](https://ddingg.tistory.com/24?category=845598)

---

### 📕 프로젝트 개요

- 임산부를 위한 공기질, 심박수 측정 및 실시간 모니터링
- 프로젝트 진행 기본 조건
  - Udoo Board를 사용할 것.
  - Air Quality Sensor를 사용할 것.
  - 심박 측정 Sensor를 사용할 것.
  - Android, Web 두가지 플랫폼을 구성할 것.
  - PHP Slim Framework, MySQL을 사용할 것.
  - 구현 기능
    - 회원가입(이메일 인증 포함), 로그인, 회원 정보 수정, 비밀번호 & 이메일 찾기
    - Android GPS를 이용한 위치추적
    - AirQuality Sensor를 통해 받아온 데이터 실시간 모니터링 및 측정 데이터
      - 데이터가 측정된 장소를 Google Map에 Display
    - 심박 센서를 통해 받아온 데이터 실시간 & 기록된 데이터 Display

---

### 📕 프로젝트 설계

- [Reference Model](https://github.com/dding-g/Project_UCSD/blob/qi_jmg/%EC%84%A4%EA%B3%84/ReferenceModel/reference%20model.pngg)
- [SystemArchitecture](https://github.com/dding-g/Project_UCSD/blob/qi_jmg/%EC%84%A4%EA%B3%84/SystemArchitecture/System%20Architecture%20-%20group%20A.png)
- [DB Schema](https://github.com/dding-g/Project_UCSD/blob/qi_jmg/%EC%84%A4%EA%B3%84/DB_Schema/TeamA_Schema_kangaroo.png)
- [Procedure](https://github.com/dding-g/Project_UCSD/tree/qi_jmg/%EC%84%A4%EA%B3%84/Procedure)
  * [User Management](https://github.com/dding-g/Project_UCSD/tree/qi_jmg/%EC%84%A4%EA%B3%84/Procedure/UserManagement)
  * [Sensor](https://github.com/dding-g/Project_UCSD/tree/qi_jmg/%EC%84%A4%EA%B3%84/Procedure/Sensor)
  * [Data Monitoring](https://github.com/dding-g/Project_UCSD/tree/qi_jmg/%EC%84%A4%EA%B3%84/Procedure/DataMornitoring)
  * [Data Management](https://github.com/dding-g/Project_UCSD/tree/qi_jmg/%EC%84%A4%EA%B3%84/Procedure/DataManagement)

---

### 🥕 결과 화면

- 회원가입 페이지

![image](https://user-images.githubusercontent.com/29707967/81568371-a1809900-93d8-11ea-9766-2e63a20c8aeb.png)

- 홈페이지 (Google Map API)

  - 대기 오염도에 따라 [AQI 지수에 따른 색상]([https://ko.wikipedia.org/wiki/%EB%8C%80%EA%B8%B0%EC%A7%88_%EC%A7%80%EC%88%98](https://ko.wikipedia.org/wiki/대기질_지수)) 으로 Google Map Pin 및 하단부의 대기 오염도를 보여주는 박스 색상을 변하게 하는 식으로 구현. 실시간으로 데이터가 새로고침 되어 볼 수 있다.

  ![image](https://user-images.githubusercontent.com/29707967/81568558-e60c3480-93d8-11ea-820a-ef0760186691.png)

- 실시간 심박수와 측정 위치 Display (Google Map API, AM Chart)

![image](https://user-images.githubusercontent.com/29707967/81568717-24095880-93d9-11ea-8000-c8606f10c35e.png)

- 심박수를 측정한 기록 Display (Apex Chart)

![image](https://user-images.githubusercontent.com/29707967/81568809-413e2700-93d9-11ea-9c3b-4d0bc3d2ee91.png)

- 대기 오염도를 측정 및 기록 Display (Google Map API, Google Chart)

![image](https://user-images.githubusercontent.com/29707967/81569690-93337c80-93da-11ea-82b0-b5e7f6a1e4c4.png)


