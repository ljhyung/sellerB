package backend.sellerB.controller;

import backend.sellerB.dto.LoginDto;
import backend.sellerB.dto.LoginResponseDto;
import backend.sellerB.dto.TokenDto;
import backend.sellerB.entity.Manager;
import backend.sellerB.exception.ManagerNotFoundException;
import backend.sellerB.repository.ManagerRepository;
import backend.sellerB.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ManagerRepository managerRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
     //로그인을 했을때 토큰(AccessToken, RefreshToken)을 주는 메서드
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {

        LoginResponseDto loginResponseDto = authService.authorize(loginDto.getId(), loginDto.getPass());

        return ResponseEntity.ok(loginResponseDto);
    }

    //AccessToken이 만료되었을 때 토큰(AccessToken , RefreshToken)재발급해주는 메서드
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue( @Valid @RequestBody TokenDto requestTokenDto) {
        TokenDto tokenDto = authService
                .reissue(requestTokenDto.getAccessToken(), requestTokenDto.getRefreshToken());
        return ResponseEntity.ok(tokenDto);
    }

//    /*
//       로그아웃을 했을 때 토큰을 받아 BlackList에 저장하는 메서드
//     */
//    @DeleteMapping("/authenticate")
//    public ResponseEntity<Void> logout(
//            @RequestBody @Valid TokenDto requestTokenDto) {
//        authService.logout(requestTokenDto.getAccessToken(), requestTokenDto.getRefreshToken());
//        return OK;
//    }

    @GetMapping("/test")
    public String test(@Valid @RequestBody LoginDto loginDto) {
        return loginDto.getId()+loginDto.getPass();
    }

}
