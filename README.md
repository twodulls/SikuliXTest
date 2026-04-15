# SikuliXTest

SikuliX와 Selenium을 활용한 파일 업로드 UI 테스트 자동화 프로젝트입니다.
OS 네이티브 파일 다이얼로그를 Selenium으로 제어할 수 없는 상황에서 SikuliX 이미지 인식을 사용해 파일을 선택합니다.

---

## 프로젝트 구조

```
SikuliXTest/
├── src/
│   ├── driver/
│   │   └── chromedriver                  # ChromeDriver 실행 파일
│   └── test/
│       ├── java/com/test/sikulix/
│       │   └── SikuliXTest.java          # 테스트 클래스
│       └── resources/
│           ├── suite.xml                 # TestNG 스위트 설정
│           ├── test_docx.png             # SikuliX 이미지 패턴 (파일명)
│           ├── open.png                  # SikuliX 이미지 패턴 (열기 버튼)
│           └── download.png              # SikuliX 이미지 패턴
├── build.gradle
├── settings.gradle
├── gradlew
└── gradlew.bat
```

---

## 기술 스택

| 항목             | 내용               |
|------------------|--------------------|
| Language         | Java 1.8           |
| Build Tool       | Gradle 8.7         |
| Test Framework   | TestNG 7.11.0      |
| Browser Automation | Selenium 4.33.0  |
| Image Recognition | SikuliX 2.0.5    |
| Utility          | commons-lang 2.6   |

---

## 테스트 수행 환경

- **Browser**: Chrome (ChromeDriver 버전 일치 필요)
- **OS**: macOS
- **IDE**: IntelliJ IDEA
- **Language & Framework**: Java, Selenium, SikuliX, TestNG

---

## 실행 방법

**1. ChromeDriver 버전 확인**

설치된 Chrome 브라우저 버전에 맞는 ChromeDriver를 `src/driver/chromedriver`에 배치합니다.

**2. 테스트 실행**

```bash
./gradlew test
```

**3. 직접 실행 (IDE)**

`src/test/resources/suite.xml`을 TestNG 스위트로 실행합니다.

---

## SikuliX 이미지 패턴 안내

SikuliX는 화면 이미지를 인식하여 클릭/입력을 수행합니다.
OS 또는 해상도가 달라지면 `src/test/resources/` 내 PNG 파일을 **현재 환경에 맞게 재캡처**해야 합니다.

| 파일             | 용도                                           |
|------------------|------------------------------------------------|
| `test_docx.png`  | 파일 선택 다이얼로그에서 test.docx 파일 인식  |
| `open.png`       | 파일 선택 다이얼로그의 열기 버튼 인식          |
| `download.png`   | 다운로드 관련 화면 인식                        |

---

## 테스트 시나리오

**시나리오: OS 네이티브 파일 다이얼로그를 통한 파일 업로드 성공 확인**

| 단계 | 설명                                                                                    |
|------|-----------------------------------------------------------------------------------------|
| 1    | `http://demo.guru99.com/test/image_upload/index.php` 접속                               |
| 2    | `파일 선택` 버튼을 Selenium으로 클릭하여 OS 파일 다이얼로그 열기                       |
| 3    | SikuliX 이미지 인식으로 파일 다이얼로그에서 `test.docx` 파일을 더블클릭하여 선택       |
| 4    | 업로드 결과 메시지 `File Upload Successful` 노출 여부 Assert 검증                      |

**기댓값**: 업로드 결과 영역(`#preview > h4 > font`)에 `File Upload Successful` 텍스트 출력

<img width="800" alt="테스트 결과 화면" src="https://user-images.githubusercontent.com/25242202/96845026-32c96080-148b-11eb-920f-0ee0eb38d9bf.png">
