package jobplatform.fo.enterprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jobplatform.fo.enterprise.domain.dto.EnterLoginDTO;
import jobplatform.fo.enterprise.domain.dto.EnterRegisterDTO;
import jobplatform.fo.enterprise.service.EnterMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/enter")
public class EnterMemberController {

    @Autowired
    EnterMemberService enterMemberService;
    
    
    // ResponseEntity로 상태 코드만 전달,

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody EnterRegisterDTO enterMemberDTO){
        log.info("register 들어옴");

        if(enterMemberService.selectId(enterMemberDTO.getEntrprsId())){

            log.info(  "중복 id 있음");

            return ResponseEntity.badRequest().build();
        }

        try{
            enterMemberService.insert(enterMemberDTO);

        }catch (Exception e){
        log.info("register sql 오류 "+e);
        }

        return ResponseEntity.ok().build();
    };

    @GetMapping("/check")
    public ResponseEntity checkId(@RequestParam String entrprsId){
        log.info("check요청");
      if(enterMemberService.selectId(entrprsId)){
          log.info(entrprsId + " id 있음");
          return    ResponseEntity.badRequest().build();
      }
   return ResponseEntity.ok().build(); }
    
    
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody EnterLoginDTO enterLoginDTO,HttpSession session
    ) {
            log.info("login요청 들어옴");
            log.info(enterLoginDTO);
    	    Long pk = null;
        if(enterLoginDTO.getEntrprsId() != null && enterLoginDTO.getEntrprsPswrd() != null){

            try {
                pk =	enterMemberService.login(enterLoginDTO);
                log.info("pk:" + pk);

            }catch(Exception e) {
                log.info(e);

                return ResponseEntity.badRequest().body("jpa login 오류" + e);

            }

            if(pk == null ){
                return ResponseEntity.badRequest().body("아이디 정보가 없습니다" );

            }


        }


        session.setAttribute("isLogin", true);
        session.setAttribute("enterPk", pk );
    	
    	
    return ResponseEntity.ok().build();
    }
   
}
