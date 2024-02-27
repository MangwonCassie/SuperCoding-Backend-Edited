# SuperCoding-Backend-Edited


⬛class95-1 실습내용:

Spring 프레임워크에서는 Repository를 Service에서 직접 사용하는 것이 일반적입니다. Repository는 데이터베이스와의 상호작용을 담당하고, Service는 비즈니스 로직을 처리합니다. 따라서 Service에서 Repository를 필드로 선언하여 사용하는 것은 꽤 흔한 패턴입니다.

Repository 필드를 Service에 주입하는 것은 Spring의 의존성 주입(Dependency Injection) 기능을 이용하여 이루어집니다. 주로 생성자 주입(Constructor Injection), 필드 주입(Field Injection), 또는 세터 주입(Setter Injection) 중 하나를 사용하는 실습 내용
