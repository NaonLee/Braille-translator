# Braille-translator (점자번역기)
Braille translator by using arduio and relay
시각 장애인들의 정보 격차와 불편을 최소화하기 위한 점자 번역기 개발


## 자체개발 기술

1) 점자 모듈
 - 점자를 자유롭게 나타낼 수 있도록 점자를 만드는 데는 전자석을 이용하기로 하였고, 아두이노의 핀을 확장하기 위하여 시프트 레지스터를 이용했다. 전자석 36개와 시프트 레지스터 6개, 레귤레이터 1개를 이용하여 점자 6개를 나타낼 수 있는 점자 모듈을 구현하였다. 점자 모듈은 아두이노로 제어하여 원하는 텍스트 파일의 점자를 표현할 수 있다.


2) Bluetooth 통신
 - 블루투스 모듈을 이용하여 안드로이드 어플리케이션과 통신하기로 하였다. 블루투스로 통신하여 어플리케이션에서 주어지는 데이터 값을 아두이노로 전송할 수 있다.


3) 텍스트 파일
 - 스마트폰 메모리에 있는 텍스트 파일을 불러오기 위하여 파일 시스템을 읽어 들였다. 그 이후에 원하는 텍스트 파일을 선택하면 해당 텍스트 파일을 String으로 불러오고, 읽어 들인 String을 점자 변환 클래스로 전송한다.
  
  
4) 점자 변환 프로그램
- 텍스트 파일의 문자를 받아들여 해당 문자의 타입을 확인하여 타입에 알맞은 점자로 변환한다. 유니코드를 이용하여 한글을 초성, 중성, 종성을 나뉘어 문자에 해당하는 아스키 코드 값을 전송한다. 영어는 대소문자를 구분하여 해당하는 아스키 코드 값을 전송한다.


## 기대효과 

 - 저렴한 점자 모듈 제공으로 장애인 보조 기기 시장 활성화 및 다양한 종류의 콘텐츠 생산이 증가 됨.
  
 - 시장 활성화를 통한 장애인 보조 물품 생산율이 증가 됨.
  
 - 시각 장애인의 학습 교재를 점자 모듈로 사용하여 학습 교재 확보 및 교재가 다양화 됨.
  
 - 점자를 스스로 학습할 수 있어 시각 장애인의 삶의 질이 향상됨.
  

## 업무분담

 - 이나온(팀장)
    : 전체적인 프로젝트 관리, 점자 모듈 구현, 블루투스 통신
    
 - 권수정(팀원)
    : 한글을 점자로 변환하는 프로그램
    
 - 박주현(팀원)
    : 어플리케이션, 회의록 작성
    
 - 윤설아(팀원)
    : 점자 모듈 구현, 아두이노 소프트웨어, 블루투스 통신
    
## 클래스 구조
   
![Class](https://user-images.githubusercontent.com/42129707/114408239-2d500600-9be4-11eb-8c67-ccee9590a847.png)
