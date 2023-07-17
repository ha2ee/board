# Repository 소개
> Github Action, AWS, Nginx를 활용해서 CI/CD 및 무중단 배포를 환경을 구축한 CRUD 게시판입니다.
>>
>> * Tool : IntelliJ, GitHub, AWS, Nginx, MariaDB
>> * Tech : Java, Thymeleaf, Mybatis
> <hr>
>   
>> ## DB구성
>> ![image](https://github.com/ha2ee/board/assets/115638416/7e7abe5f-2fb1-424e-b33f-8bb3df81a47c)
>> 
> <hr>
> 
>> ## 아키텍쳐
>> ![a drawio (2)](https://github.com/ha2ee/board/assets/115638416/d4fbc0a8-5a13-48da-be56-dd867343fdc6)
>>
>> CI/CD 흐름은 다음과 같이 동작합니다.
>> 1. 코드 푸시가 발생하면 GitHub Actions workflow가 실행됩니다.
>> 2. Gradle을 사용하여 프로젝트를 빌드합니다.
>> 3. 빌드된 애플리케이션을 압축(zip)파일로 만듭니다.
>> 4. 압축 파일을 AWS S3에 업로드합니다.
>> 5. AWS CodeDeploy를 사용하여 EC2 인스턴스에 배포를 생성합니다.
>> 6. 배포 후에는 appspec.yml의 `AfterInstall`훅에서 deploy.sh 스크립트가 실행됩니다.
>> 7. deploy.sh 스크립트는 EC2인스턴스에서 백그라운드로 실행됩니다.
