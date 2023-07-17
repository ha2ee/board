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
>> 8. deploy.sh 스크립트는 다음 작업을 수행합니다:
>>    * 빌드된 애플리케이션 파일을 복사하여 배포 디렉토리에 저장합니다.
>>    * 현재 실행중인 애플리케이션의 프로필을 확인합니다.
>>    * 현재 사용중인 프로필에 따라 배포할 프로필과 포트를 결정합니다.
>>    * 애플리케이션 파일을 심볼릭 링크를 사용해 배포 디렉토리에 교체합니다.
>>    * 이전에 실행중인 애플리케이션 프로세스 종료합니다.
>>    * 새로운 프로필로 애플리케이션 실행합니다.
>>    * 헬스 체크를 수행해서 애플리케이션이 정상적으로 실행되는지 확인합니다.
>>    * 헬스 체크가 성공한 경우 스위칭 작업 수행합니다.
>>    * switch.sh 스크립트를 수행합니다.
>> 9. switch.sh 스크립트는 다음 작업을 수행합니다:
>>    * 현재 실행중인 프로필의 포트 확인합니다.
>>    * 현재 사용중인 프로필에 따라 전환할 포트를 결정합니다.
>>    * Nginx의 프록시 설정을 변경하여 새로운 포트로 전환합니다.
>>    * Nginx를 다시 로드하여 변경 사항을 적용합니다.
