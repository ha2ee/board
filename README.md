# CI/CD Project


`CI/CD Project`는 GitHub Actions와 AWS의 여러 기능을 활용하여 실제 웹 사이트를 배포해보는 것을 중점으로 개발하였습니다.  
`2023-07-01 ~ 2023-07-17`동안 Spring의 기본 기능들을 사용해 구현했습니다. 

## 프로젝트 링크

배포된 웹 사이트: https://www.spring-web.com/

## 목차
- [구현 내용](#구현-내용)
- [기술 스택](#기술-스택)
- [프로젝트 특징](#프로젝트-특징)
- [기능 실행화면](#기능-실행화면)
- [배포환경 아키텍쳐 구성](#배포환경-아키텍쳐-구성)
- [배운 점](#배운-점)

## 구현 내용

- 게시물 목록 조회: 등록된 게시물들을 목록 형태로 조회할 수 있습니다.
- 게시물 작성: 사용자는 제목과 내용을 입력하여 게시물을 작성할 수 있습니다.
- 게시물 상세 조회: 선택한 게시물의 상세 내용을 조회할 수 있습니다.
- 게시물 수정: 작성한 게시물의 내용을 수정할 수 있습니다.
- 게시물 삭제: 작성한 게시물을 삭제할 수 있습니다.

## 기술 스택

|종류|기술|
|----|----|
|언어|Java, JavaScript|
|프레임워크|Spring Framework|
|템플릿엔진|Thymeleaf|
|DBMS|MariaDB|
|SQL|MyBatis|
|인프라|Nginx, AWS|
|CI/CD|GitHub Actions|

## 프로젝트 특징

- 무중단 배포 환경: Nginx를 사용하여 로드 밸런싱과 리버스 프록시를 구성하여 무중단 배포 환경을 구축하였습니다.
- AWS 활용: AWS를 사용하여 프로젝트를 배포하였으며, RDS, Route53등의 기능을 활용하였습니다.
- 자동화된 빌드 및 배포: GitHub Actions, AWS CodeDeploy를 활용하여 소스 코드의 자동 빌드 및 배포를 구성하였습니다.

## 기능 실행화면
### 게시글 조회
- 모든 게시글을 조회 할 수 있습니다. 페이징 기능을 통해 한 페이지에서 최대 10개의 게시글이 조회 됩니다.
![image](https://github.com/ha2ee/cicd_project/assets/115638416/ec7243ca-dc4e-4b46-b478-d8f303080e36)

### 게시글 작성
- 로그인 한 사용자는 게시글을 작성 할 수 있습니다.
![image](https://github.com/ha2ee/cicd_project/assets/115638416/8194e955-8fe2-4f61-8b01-3250d35734af)

### 파일 업로드
- 10MB 넘는 파일은 업로드 불가능합니다.
![image](https://github.com/ha2ee/cicd_project/assets/115638416/329e9409-a7e2-4f83-bcc9-c3956acba170)

### 게시글 수정
- 게시글 작성자는 게시글을 수정 할 수 있습니다.
![image](https://github.com/ha2ee/cicd_project/assets/115638416/1abe21b4-c0ee-4407-97eb-3de45d44936b)
![image](https://github.com/ha2ee/cicd_project/assets/115638416/52088307-45c1-4125-a53b-010d7140d833)

### 게시글 삭제
- 게시글 작성자는 게시글을 삭제 할 수 있습니다.
![image](https://github.com/ha2ee/cicd_project/assets/115638416/a12ed03c-78d8-462c-9edf-38c479f65d2d)
![image](https://github.com/ha2ee/cicd_project/assets/115638416/3999571e-f916-40a2-a7df-e82b1487c8d8)

### 댓글
- 페이징 처리를 통해 한 페이지에서 최대 5개의 댓글을 조회 할 수 있습니다.
![image](https://github.com/ha2ee/cicd_project/assets/115638416/cb4a1a3a-91f3-4a2b-bdd6-d65339126dbd)


## 배포환경 아키텍쳐 구성
  ![architecture](https://github.com/ha2ee/cicd_project/assets/115638416/7669a8d5-5f71-4ef4-a107-d001b5866015)

## 배운 점
- Java와 관련된 기술 스택의 활용과 통합
  - Java와 Spring Framework, Thymeleaf를 이용해서 MVC패턴을 사용해 보았습니다.
- AWS의 다양한 서비스를 활용한 클라우드 인프라 구성
  - CodeDeploy, RDS, Route53, Certificate Manager, S3 저장소 등 클라우드 서비스를 사용 해 보고 인프라에 대해 알게 되었습니다.
- GitHub Actions를 활용한 자동화된 빌드 및 배포 환경 구축
  - GitHub Actions의 기본적인 기능들을 활용 해 보면서 CI/CD란 어떤 방식으로 이루어지는건지에 대해 알게 되었습니다.
- 무중단 배포를 위한 Nginx의 활용
  - Nginx를 리버스프록시 서버로 사용해서 무중단 배포와 비슷한 환경을 구성해보았습니다.
  
