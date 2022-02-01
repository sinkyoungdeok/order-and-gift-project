# 1. 프로젝트의 목적 
- 지금까지 공부했던 기술들을 다듬는다.
- 유용한 기능을 개발하는 것 보다는 기존에 했던 프로젝트를 새롭게 알게된 기술들로 재구현 하는데에 초점을 둔다.
- 기획에서 설계부터 실제 개발까지 진행하는것도 좋지만, 여기에서는 기획력이나 설계하는 능력을 기르기보다는 지금까지 배운 기술들을 정리하는데에 집중한다.
- 단순히 새로운 기술로 전환하기보다는 요구사항만을 보면서 분석하고 처음부터 개발한다

# 2. 프로젝트 기술스택
- 기존 프로젝트: https://github.com/sinkyoungdeok/msa
- 기존 프로젝트는 패스트캠퍼스의 The Red강의를 들으면서 클론코딩을 진행하였다.

## 기존 프로젝트의 기술스택
- 아키텍처: msa, ddd
- 언어: java
- 프레임워크: spring boot, spring mvc, spring data jpa
- 라이브러리: jpa, retrofit2, mapstruct
- 빌드툴: gradle
- 메시지 브로커: aws sqs
- 컨테이너툴: docker
- 데이터베이스: mysql

## 새로운 프로젝트의 기술 스택
- 아키텍처: msa, ddd
- 언어: kotlin
- 프레임워크: spring boot, spring webflux, spring data mongodb reactive
- 라이브러리: spring kafka, retrofit, mapstruct
- 빌드툴: gradle
- 메시지 브로커: kafka
- 컨테이너툴: docker
- 데이터베이스: mongodb

# 3. 요구사항 

## 3-1. 주문 프로젝트

### 주요 이해관계자
1. 유저 - 상품을 선택하여 주문하는 고객 
2. 파트너 - 상품을 판매하는 업체
3. 내부 운영자 - 서비스를 운영하고 관리하는 담당자 

### 주요 도메인 
1. 파트너 - 파트너 등록과 운영을 처리
2. 상품 - 상품과 상품의 옵션 정보를 등록하고 관리
3. 주문 - 유저가 선택한 상품 정보와 주문 정보를 관리하고 결제를 처리함 

### 도메인 별 요구사항 
1. 파트너 
   - 시스템에 등록된 파트너만이 상품을 등록하고 주문을 처리할 수 있다
   - 파트너 등록 시 파트너명, 사업자등록번호, 이메일은 필수 값이다.
   - 파트너는 계약이 종료되면 비활성 상태로 전환된다. 단, 파트너 정보 자체는 삭제되지 않고 유지된다
   - 파트너 등록이 성공하면 등록된 이메일로 가입 완료 안내 메일을 발송한다
   - 그 외 시스템을 사용하는 유저가 기본적으로 기대하는 기본 기능들 - 조회, 등록, 수정, 삭제 등의 기능을 제공해야 한다
2. 상품
   - 시스템에 등록되고 활성화된 파트너는 상품을 등록할 수 있다.
   - 등록된 상품은 유저의 주문을 받아 판매될 수 있다
   - 상품은 상품명, 가격 등의 기본 정보와 색상, 사이즈와 같은 옵션으로 구성된다
   - 상품은 옵션 정보 없이 기본값으로만 저장될 수도 있다
   - 주문 화면에서 보여지는 상품의 옵션은 파트너사가 원하는 순서에 맞게 노출될 수 있어야 한다
   - 상품 구매 시 특정한 옵션을 선택하면 가격이 추가 될 수 있다
   - 상품은 판매 준비중, 판매중, 판매 종료와 같은 상태를 가진다
   - 그 외 시스템을 사용하는 유저가 기본적으로 기대하는 기본 기능들 - 조회, 등록, 수정, 삭제 등의 기능을 제공해야 한다
   - 여기에서는 실제의 복잡한 상품 도메인 요구사항을 간소화 하였다(수량 등의 속성 생략)
3. 주문
   - 시스템에 등록된 상품은 유저가 주문할 수 있다
   - 주문은 주문 등록, 결제, 배송준비, 배송중, 배송 완료의 단계를 가진다
   - 주문 등록 과정에서는 결제 수단을 선택하고 상품 및 상품 옵션을 선택한다
   - 시스템에서 사용 가능한 결제 수단은 1) 카드 2) 토스페이 3) 카카오페이 4) 네이버페이 등이 있다
   - 결제 과정에서는 유저가 선택한 결제수단으로 결제를 진행한다
   - 결제완료 후 유저에게 카카오톡으로 주문 성공 알림이 전달된다
   - 결제가 완료되면 배송준비 단계로 넘어간다
   - 배송중, 배송완료의 단계도 순차적으로 진행된다
   - 여기에서는 실제의 복잡한 주문 도메인 요구사항을 간소화 하였다(결제 연동, 취소 등의 요구사항 생략)

### 도메인별 다이어그램 
![image](https://user-images.githubusercontent.com/28394879/150360895-45ceac43-b217-4b32-93e8-3ba6f7f70611.png)

## 3-2. 선물하기 프로젝트

### 요구사항
- 선물하기 주문은 일반 주문과 달리, 주문 과정에서 배송지 주소를 확정할 수 없다
  - 선물하기 주문을 결제한 사람이 해당 주문을 지인하게 선물로 전달하고, 이 후 수령자가 본인의 배송지 주소를 입력해야 하는 배송이 시작되는 구조이기 때문이다
- 선물하기 결제 후 구매자와 수령자에게 카카오톡이나 LMS로 알림이 발송되어야 한다
- 수령자는 선물을 수락하거나 거절할 수 있다
- 선물하기 서비스 개발과 운영 시에는 기존 주문 서비스의 기능에는 영향이 없도록 하면서 최대한 빠르게 개발이 진행되어야 한다
- 여기에서는 실제의 복잡한 선물하기 도메인 요구사항을 간소화하여, msa에서의 서버간 통신과 비즈니스 도메인별로 서버를 나누는 내용에 초점을 맞추어 진행한다

### 설계
- 선물하기 서비스에서 기존 주문 생성 API를 호출할 때, 임의의 배송지 정보를 전달하는 방식을 채택한다 (추후 배송지 정보를 update 하게끔 처리한다)


### 다이어그램

**전체 Flow 간략 버전**  
![image](https://user-images.githubusercontent.com/28394879/151736648-21be3491-99b5-4d46-b0fa-7c1cbfa0c688.png)

**선물하기 서비스와 주문 서비스간의 의존관게**  
![image](https://user-images.githubusercontent.com/28394879/151736756-9dc02bd1-f949-49ef-b3c7-e1a71234a7f2.png)

**전체 Flow**  
![image](https://user-images.githubusercontent.com/28394879/151734979-854e0ff1-19a4-4630-8a5e-ca86ce5a6a4a.png)

**실제 Flow**  
![image](https://user-images.githubusercontent.com/28394879/151737066-2b5f53eb-e375-44f9-bf19-9a15f845a012.png)

**선물 구매 시**  
![image](https://user-images.githubusercontent.com/28394879/151735130-45a84aab-ea41-442e-aab8-a54644bcb324.png)

**선물 수락 시**  
![image](https://user-images.githubusercontent.com/28394879/151735169-a4f1ed9c-d750-46b8-a7ef-fbe494580c1d.png)

**선물 거절 시**  
![image](https://user-images.githubusercontent.com/28394879/151735207-ece3ab78-3ec4-4b14-9f7f-7f973deaf179.png)

**나중에 Kafka를 통해서 얻을 수 있는 설계**  
![image](https://user-images.githubusercontent.com/28394879/151737175-87b3e2ba-bc1b-4010-8516-b63a0621559c.png)

### 변경사항
- 확장성을 위해서 SQS대신 kafka를 사용한다.

# 4. 발견한 오류 & 해결방법 
<details><summary>1. reactiveMongoDB의 repository빈 주입 실패 </summary>

### 코드
```kotlin
@Repository
interface PartnerRepository : ReactiveMongoRepository<Partner, String>
```
```
implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
```

### 에러
```
Description:

Parameter 0 of constructor in msa.order.infrastructure.partner.PartnerStoreImpl required a bean of type 'msa.order.infrastructure.partner.PartnerRepository' that could not be found.


Action:

Consider defining a bean of type 'msa.order.infrastructure.partner.PartnerRepository' in your configuration.


Process finished with exit code 1
```

### 해결 방법
- 내가 사용한 repository는 ReactiveMongoDB 였는데, gradle의 의존성은 일반(논리액티브) mongodb를 사용하고 있었다.
- 의존성을 reactive mongodb로 변경하니 잘 되었다
- 내가 빈을 잘못 설정했나 생각해서 다양한 컴포넌트로 바꾸어 보았지만, 안되서 많이 헤맸었다.
```
implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
```

</details>

<details><summary> 2. reactiveMongoDB의 연동 문제 </summary>

### 코드
```yaml
spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      authentication-database: admin
      username: root
      password: 1234
      database: order
```

### 오류
```
org.springframework.data.mongodb.UncategorizedMongoDbException: Exception authenticating MongoCredential{mechanism=SCRAM-SHA-256, userName='root', source='admin', password=<hidden>, mechanismProperties=<hidden>}; nested exception is com.mongodb.MongoSecurityException: Exception authenticating MongoCredential{mechanism=SCRAM-SHA-256, userName='root', source='admin', password=<hidden>, mechanismProperties=<hidden>}
	at org.springframework.data.mongodb.core.MongoExceptionTranslator.translateExceptionIfPossible(MongoExceptionTranslator.java:140) ~[spring-data-mongodb-3.3.0.jar:3.3.0]
	Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException: 
Error has been observed at the following site(s):
	*__checkpoint ⇢ Handler org.springframework.web.reactive.function.server.RouterFunctionDsl$POST$2@102b2dbd [DispatcherHandler]
	*__checkpoint ⇢ HTTP POST "/api/v1/partners" [ExceptionHandlingWebHandler]
Original Stack Trace:
...
```

### 해결 방법
- 오류를 구글링해도 잘 나오지 않아서, 찾기가 어려웠다.
- host,port 등을 작성하는 것 대신, uri로 한번에 작성하니 잘 되었다.
- 왜 안되는진 아직 잘 모르겠다.
```
spring:
  data:
    mongodb:
      uri: mongodb://root:1234@localhost/order?authSource=admin
```

</details>

<details><summary> 3. webflux에서 validation 처리 안되던 현상 </summary>

### 코드
```kotlin
@PostMapping
fun registerPartner(
  @Valid @RequestBody request: PartnerDto.RegisterRequest
): Mono<CommonResponse<PartnerDto.RegisterResponse>> {
  var command: Mono<PartnerCommand.RegisterPartner> = Mono.just(request.toCommand())
  var partnerInfo = partnerFacade.registerPartner(command)
  var response = partnerInfo.map { PartnerDto.RegisterResponse(it) }
  return response.map { CommonResponse(it) }
}
```

```kotlin
class PartnerDto {

   class RegisterRequest(
      @field:NotEmpty(message = "partnerName 은 필수값 입니다")
      var partnerName: String? = null,

      @field:NotEmpty(message = "businessNo 는 필수값 입니다")
      var businessNo: String? = null,

      @field:Email(message = "email 형식에 맞추어야 합니다")
      @field:NotEmpty(message = "email 은 필수값 입니다")
      var email: String? = null
   )
}
```

```kotlin
@RestControllerAdvice
class CommonControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): Mono<CommonResponse<String>> {
        // ...
        
        return Mono.just(errorResponse)
    }
}
```


http 요청
```
POST http://localhost:8080/api/v1/partners
Content-Type: application/json

{
  "partnerName": "",
  "businessNo": "1234123456",
  "email": "greg.shiny8"
}
```

### 에러 
- validation 에러에 대한 처리가 이루어지지 않았다. 
- partnerName이 비어 있으므로, "partnerName 은 필수값입니다"에 대한 에러가 등장 해야하는데, controllerAdvice에서 이를 감지 못하는 현상이 발생

### 해결 방법 
- Webflux의 Advice예제가 많이 없어서 찾기가 힘들었다.
- 사실상, 구글링으로 해결한 것이 아니라, Advice로 이것저것 해보다가 알게 되었다. (Exception 자체를 받아서 처리하게끔해서 어떤 에러를 던지는지 확인하였다)
- 해결 방법은 ControllerAdvice쪽에서 `MethodArgumentNotValidException::class` 가 아닌 `WebExchangeBindException::class`를 감지할 수 있도록 변경하였다.
- validation 에러에 대한 exception class가 왜 spring mvc랑은 다른지는 모르겠다. 

```kotlin
@RestControllerAdvice
class CommonControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [WebExchangeBindException::class])
    fun methodArgumentNotValidException(e: WebExchangeBindException): Mono<CommonResponse<String>> {
        // ...
        return Mono.just(errorResponse)
    }
}
```

</details>

<details><summary> 4. mapstruct 의존성 추가 </summary>

### 코드
```
implementation("org.mapstruct:mapstruct:1.4.2.Final")
annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
annotationProcessor(
  "org.projectlombok:lombok",
  "org.projectlombok:lombok-mapstruct-binding:0.1.0"
)
```

```kotlin
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface PartnerDtoMapper {

    fun of(request: PartnerDto.RegisterRequest): PartnerCommand.RegisterPartner
}
```

```kotlin
@RestController
@RequestMapping("/api/v1/partners")
class PartnerApiController(val partnerFacade: PartnerFacade, val partnerDtoMapper: PartnerDtoMapper) {

    //...
}
```

### 에러 
- `val partnerDtoMapper: PartnerDtoMapper`에서 빈을 인식 못하는 상황 발생
- `PartnerDtoMapper`의 구현체를 mapstruct에서 만들어주어야 하는데, 이것 또한 안되었었다.
```
Description:

Parameter 1 of constructor in msa.order.interfaces.partner.PartnerApiController required a bean of type 'msa.order.interfaces.partner.PartnerDtoMapper' that could not be found.


Action:

Consider defining a bean of type 'msa.order.interfaces.partner.PartnerDtoMapper' in your configuration.


Process finished with exit code 1
```

### 해결 방법
- 원래 작성한 코드는, java-spring에서 쓰던 라이브러리 의존성을 가져다가 쓴 것인데, kotlin에서 그대로 사용은 안되는것을 알게됨.
- 구글링을 통해 이것 저것 따라 해보다가, 되는것을 찾음

```
plugins {
	...
	kotlin("kapt") version "1.3.72" // 추가
}
...
dependencies {
    ...
    
	// MapStruct
	implementation("org.mapstruct:mapstruct:1.4.2.Final")
	kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")
	implementation("org.projectlombok:lombok-mapstruct-binding:0.1.0")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
	annotationProcessor(
		"org.projectlombok:lombok",
		"org.projectlombok:lombok-mapstruct-binding:0.1.0"
	)
	
	...
}
```




</details>

<details><summary> 5. spring data reactiveMongoDB의 parameter에 mono타입을 넣어서 생긴 오류 </summary>

- 오류 발견 날짜: 1월 24일 
- 해결 날짜: 1월 25일

### 코드
```kotlin
@Repository
interface PartnerRepository : ReactiveMongoRepository<Partner, String> {
    fun findByPartnerToken(partnerToken: Mono<String>): Mono<Partner>
}
```

```kotlin
@Component
class PartnerReaderImpl(
   val partnerRepository: PartnerRepository
) : PartnerReader {
   override fun getPartner(partnerToken: Mono<String>): Mono<Partner> {
      return partnerRepository.findByPartnerToken(partnerToken)
   }
}
```

### 에러 
- API 요청을 하면 무한 대기에 빠지게 됨

### 해결 방법
- Mono타입에서 객체를 추출하여 repository를 호출.
- 해결한 방법은 다른 분들 작성한 것을 보니까 repository로 호출하기 전에 객체로 꺼내서 호출하기 전에 따라해보니 됐음
- 예상으로는 request로 전달 받은 partnerToken이 너무 빨리 repository를 호출하거나, 제 때 호출을 못해서 생기는 이슈라고 생각이 듬.
- 제대로는 어떻게 해서 안된 것인지 상황에 대해서 이해를 하지 못하였음.

```kotlin
@Repository
interface PartnerRepository : ReactiveMongoRepository<Partner, String> {
    fun findByPartnerToken(partnerToken: String): Mono<Partner>
}
```

```kotlin
@Component
class PartnerReaderImpl(
    val partnerRepository: PartnerRepository
) : PartnerReader {
    override fun getPartner(partnerToken: Mono<String>): Mono<Partner> {
        return partnerToken.flatMap { partnerRepository.findByPartnerToken(it) }
    }
}
```



</details>



<details> <summary> 6. request를 mono타입으로 전달 받음으로써 생긴 에러</summary>

### 코드 

```kotlin
@PostMapping
fun registerItem(
   @RequestBody @Valid request: Mono<ItemDto.RegisterItemRequest>
): Mono<CommonResponse<ItemDto.RegisterResponse>> {
   var partnerToken = request.map { it.partnerToken ?: "" }
   var itemCommand = request.map { itemDtoMapper.of(it) }
   var itemInfo = itemFacade.registerItem(itemCommand, partnerToken)
   var response = itemInfo.map { itemDtoMapper.of(it) }
   return response.map { CommonResponse(it) }
}
```

```kotlin
 override fun registerItem(
     command: Mono<ItemCommand.RegisterItemRequest>,
     partnerToken: Mono<String>
 ): Mono<ItemInfo.Token> {
     return partnerReader.getPartner(partnerToken)
         .flatMap { p ->
             val map: Mono<Item> = command.map { c ->
                 p.id?.let { c.toEntity(it) }
             }

             map
         }.map {
             ItemInfo.Token(it.itemName)
         }
```


### 에러
- request를 mono타입으로 전달 받음으로써, reactive DB에 전달 및 전달받을 때 객체를 꺼내서 전달해줘야 하는 상황이 생김
- request에서 전달받은 객체와, db에서 받은 객체와의 연결하는부분에서 잘 처리되지 않는다는 것을 발견
- 코드에서 두번째 registerItem 함수에서
  - partnerReader에서 전달받은 partner와
  - request로 전달받은 command
  - 위의 두개 객체가 독립적으로는 전달이 잘 되지만, 두개를 연결시키는 순간 알 수 없는 오류가 생김


### 해결 방법
- request에 일반 객체, mono객체 두개중에 선택하는 과정에서 mono로 택했지만, 일반 객체를 사용하는 것이 좋다는 블로그글을 찾게됨 
- https://homoefficio.github.io/2020/08/06/Spring-WebFlux-RequestBody/

</details>
	
# 5. 프로젝트 기간
- 주문 프로젝트: 2022.01.20 ~ 2022.01.31
- 선물 프로젝트: 2022.01.31 ~ 2022.02.01
