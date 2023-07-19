# Repository 소개
## ****개요****

---

본 프로젝트는 Java와 관련 기술을 사용하여 CRUD(Create, Read, Update, Delete) 기능을 갖춘 게시판 웹 애플리케이션을 개발하였습니다. 이를 통해 사용자는 게시물을 작성, 조회, 수정 및 삭제할 수 있습니다.

주요 기술 스택으로는 Java, JavaScript, Thymeleaf, MyBatis를 사용하였으며, 데이터베이스로는 MariaDB를 선택하였습니다.

프로젝트는 AWS의 다양한 서비스를 활용하여 구축되었습니다. EC2를 사용하여 애플리케이션을 호스팅하고, RDS를 사용하여 데이터베이스를 관리하였으며, Route 53으로 도메인을 구성하였습니다. 또한, CodeDeploy를 통해 지속적인 배포를 구현하고, Certificate Manager로 SSL 인증서를 발급하여 보안을 강화하였습니다.

웹 서버로는 Nginx를 사용하였으며, GitHub Actions를 통해 자동 빌드 및 배포 환경을 구성하였습니다.

## **구현 내용**

---

- 게시물 목록 조회: 등록된 게시물들을 목록 형태로 조회할 수 있습니다.
- 게시물 작성: 사용자는 제목과 내용을 입력하여 게시물을 작성할 수 있습니다.
- 게시물 상세 조회: 선택한 게시물의 상세 내용을 조회할 수 있습니다.
- 게시물 수정: 작성한 게시물의 내용을 수정할 수 있습니다.
- 게시물 삭제: 작성한 게시물을 삭제할 수 있습니다.

## ****기술 스택****

---

- 언어: Java, JavaScript
- 프레임워크: Spring Boot
- 웹 템플릿 엔진: Thymeleaf
- 데이터베이스: MariaDB
- SQL Framework: MyBatis
- 웹 서버: Nginx
- 배포 및 인프라: AWS(EC2, RDS, Route 53, CodeDeploy, Certificate Manager)
- CI/CD: GitHub Actions

## **프로젝트 특징**

---

- 무중단 배포 환경: Nginx를 사용하여 로드 밸런싱과 리버스 프록시를 구성하여 무중단 배포 환경을 구축하였습니다.
- AWS 활용: AWS를 사용하여 프로젝트를 배포하였으며, RDS, Route53등의 기능을 활용하였습니다.
- 자동화된 빌드 및 배포: GitHub Actions, AWS CodeDeploy를 활용하여 소스 코드의 자동 빌드 및 배포를 구성하였습니다.

## 프로젝트 관리 및 배포 환경

---

- 프로젝트 관리: GitHub를 사용하여 버전 관리 및 이슈 트래킹을 수행하였습니다.
- 자동 빌드 및 배포: GitHub Actions를 활용하여 소스 코드에 변경이 있을 때마다 자동으로 빌드 및 배포를 수행하였습니다.
- 무중단 배포: Nginx를 사용하여 로드 밸런싱과 리버스 프록시를 구성하여 무중단 배포를 실현하였습니다.
- 클라우드 인프라: AWS EC2를 사용하여 애플리케이션을 호스팅하였으며, RDS를 사용하여 데이터베이스를 관리하였습니다. 또한, Route 53을 이용하여 도메인을 구성하였습니다. SSL 인증서 발급을 위해 Certificate Manager를 활용하였습니다.

## 프로젝트 링크

---

GitHub 저장소: https://github.com/ha2ee/board

배포된 웹 사이트: https://www.spring-web.com/

## 스크린샷

---

- 배포환경 아키텍쳐 구성
  ![architecture](https://github.com/ha2ee/cicd_project/assets/115638416/7669a8d5-5f71-4ef4-a107-d001b5866015)

- 게시글 목록
  - 게시판 레이어에서는 LoginCheckInterceptor를 적용하여 로그인을 체크 했습니다.
  ![Screenshot from 2023-07-19 09-14-47](https://github.com/ha2ee/board/assets/115638416/a5664605-6f93-473d-88ec-27ba1cf7a09a)

- 게시글 작성
  - 파일 당 10MB의 용량 제한이 있으며, 다중 파일을 업로드 가능하나 파일 용량의 합은 최대 50MB의 제한을 걸었습니다.
  ![image](https://github.com/ha2ee/cicd_project/assets/115638416/5bb05af9-b256-4670-8a55-7a998301196b)

- 게시글 상세
  - 댓글은 API 통신으로 구현하였습니다.
  ![image](https://github.com/ha2ee/cicd_project/assets/115638416/f6d269ff-c8e4-4197-bc86-aaa970bd090a)

## 배운 점

---

- Java와 관련된 기술 스택의 활용과 통합
- AWS의 다양한 서비스를 활용한 클라우드 인프라 구성
- GitHub Actions를 활용한 자동화된 빌드 및 배포 환경 구축
- 무중단 배포를 위한 Nginx와 CodeDeploy의 활용
- SSL 인증서 발급 및 적용 방법 이해
