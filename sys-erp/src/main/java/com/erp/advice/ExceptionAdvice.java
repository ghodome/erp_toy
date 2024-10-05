package com.erp.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.erp.error.TargetNotFoundException;


// 스프링 부트에서 발생하는 각종 예외들을 처리하는 간섭 객체 (AOP) interceptor, controlleradvice 등
@RestControllerAdvice(basePackages = {"com.kh14.spring11restapi.restcontroller"})
// @RestControllerAdvice(annotaions={RestController.class})
public class ExceptionAdvice {
	
	// 두 가지 핸들러로 처리한다 (404, 500)
	// TargetnotFound는 404
	// 나머지는 500 처리 | 메세지는 제거할 것(서버에서만 볼수있게)

	@ExceptionHandler(TargetNotFoundException.class)
	public ResponseEntity<String> error404(){
//		return ResponseEntity.ok().build();// 200 성공
		return ResponseEntity.status(404).body("대상 없음");
//		return ResponseEntity.notFound().build();//404 보낸 번호에 대한 대상이 없다.
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> error500(Exception e){
		e.printStackTrace();
//		return ResponseEntity.status(500).body("server error");
		return ResponseEntity.internalServerError().body("server error");// 500 서버에러
	}

}
