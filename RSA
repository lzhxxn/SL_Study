-- 1. JAVA에서 RSA를 사용하기위해서는 라이브러리를 Import 시킨다. ( Server )
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

-- 2. 로그인 처리전이면 로그인폼에 공개키값을 전달한다. "공개키값은 Input[type=hidden] value 값에 셋팅" ( Server )
 //로그인
   @RequestMapping("login.do")
   public String MoveLogin(@ModelAttribute Member member, Model model, HttpServletRequest request,HttpServletResponse response)throws GeneralSecurityException {
      HttpSession session = request.getSession();
      String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
      model.addAttribute("url", naverAuthUrl);
      if(member.getM_email() != null) //로그인처리 완료 조건
    	  return "index"; //메인페이지 이동
      else { //로그인처리 전이면 로그인 페이지로 이동한다.
         KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
         generator.initialize(1024);
         KeyPair keyPair = generator.genKeyPair();
         KeyFactory keyFactory = KeyFactory.getInstance("RSA");
         PublicKey publicKey = keyPair.getPublic();
         PrivateKey privateKey = keyPair.getPrivate();  
      
         session.setAttribute("_RSA_WEB_Key_", privateKey);   //세션에 RSA 개인키를 세션에 저장한다.
         RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
         String publicKeyModulus = publicSpec.getModulus().toString(16);
         String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
         
         request.setAttribute("RSAModulus", publicKeyModulus);  //로그인 폼에 Input Hidden에 값을 셋팅하기위해서
         request.setAttribute("RSAExponent", publicKeyExponent);   //로그인 폼에 Input Hidden에 값을 셋팅하기위해서
         return "login";
      }
   }
   
   -- 3. 로그인 페이지에서 서버에서 전달된 공개키를 셋팅한다. ( Client )
   <!-- RSA 자바스크립트 라이브러리 -->
<script type="text/javascript" src="../js/RSA/jsbn.js"></script>
<script type="text/javascript" src="../js/RSA/rsa.js"></script>
<script type="text/javascript" src="../js/RSA/prng4.js"></script>
<script type="text/javascript" src="../js/RSA/rng.js"></script>

<!-- 로그인  -->
<p class="tip"></p>
<div class="cont">
  <div class="form sign-in">
   <form id="LoginS" name="loginS"  >    
       <h2>여행시작하기,</h2>
       <input type="hidden" id="RSAModulus" value="${RSAModulus}" /><!-- 서버에서 전달한값 세팅. -->
      <input type="hidden" id="RSAExponent" value="${RSAExponent}" /><!-- 서버에서 전달한값 세팅 -->
       <label>
         <span>이메일</span>
         <input type="text" name="m_email" id="l_email" required="required"/>
       </label>
       <label>
         <span>비밀번호</span>
         <input type="password" name="m_pwd" id="l_pwd" required="required"/>
       </label>
      <a href="findIdPwd">
          <p class="forgot-pass" style="text-decoration-line: underline;">이메일 또는 비밀번호가 기억이 안나시나요?</p>
       </a>
   
       <button type="button" id="ms_login" class="submit" onclick="loginCheck()" >시작 </button><!-- class="submit" onclick="loginCheck()" -->
       
       <!-- 네이버 로그인 창으로 이동 -->
      <div id="naver_id_login" style="text-align:center"><a href="${url}">
      <img style="max-height: 40px;border-radius: 30px;width: 45%;" width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/></a></div>
      <br>
  </form>
  </div>
  
  -- 4. 사 용자 계정정보를 등록후 로그인 버튼을 클릭하면 서버로 전송되기전에 사용자 계정정보를 암호화후에 서버로 전달한다. ( Client )
  <script>
$("#ms_login").click(function(){
   //사용자 계정정보 암호화전 평문
   var uemail = $("#l_email").val();
   var pwd = $("#l_pwd").val();
   
   //alert(uemail +"##"+ pwd);
   //RSA 암호화 생성
   var rsa = new RSAKey();
   rsa.setPublic($("#RSAModulus").val(), $("#RSAExponent").val());
   
   //사용자 계정정보를 암호화 처리
   uemail = rsa.encrypt(uemail);
   pwd = rsa.encrypt(pwd); 
   //alert(uemail +"##"+ pwd);
   $.ajax({ 
        type: "POST",  
        url: "login.proc",  
        data: {m_email :uemail, m_pwd: pwd},  //사용자 암호화된 계정정보를 서버로 전송
        dataType:"json",
        success: function(msg){    
          
           if(msg.state == "true")
           {
              location.href = "../"; 
           }
           else if(msg.state == "false")
           {
             salert("이메일 또는 비밀번호를 다시 확인해주세요.");
           }
           else
           {
             salert("잘못된 경로로 접근하였습니다. 암호화 인증에 실패하였습니다."); 
           } 
        } 
   });
});
</script>

-- 5. 로그인 폼에서 암호화처리된 사용자정보를 전송받아 복호화 처리후 . Database에 저장된 사용자 아이디와 패스워드가 일치하는지 확인한다.
 //암호화
   public String decryptRsa(PrivateKey privateKey, String securedValue) {
       String decryptedValue = "";
       try{
         Cipher cipher = Cipher.getInstance("RSA");
         /**
         * 암호화 된 값은 byte 배열이다.
         * 이를 문자열 폼으로 전송하기 위해 16진 문자열(hex)로 변경한다. 
         * 서버측에서도 값을 받을 때 hex 문자열을 받아서 이를 다시 byte 배열로 바꾼 뒤에 복호화 과정을 수행한다.
         */
         byte[] encryptedBytes = hexToByteArray(securedValue);
         cipher.init(Cipher.DECRYPT_MODE, privateKey);
         byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
         decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 주의.
       }catch(Exception e)
       {
          System.out.println("# 암호화 에러 발생 : "+e.getMessage());
       }
         return decryptedValue;
   } 
 
   	//16진 문자열을 byte 배열로 변환 
    public static byte[] hexToByteArray(String hex) {
      if (hex == null || hex.length() % 2 != 0) {
         return new byte[]{};
      }
    
      byte[] bytes = new byte[hex.length() / 2];
      for (int i = 0; i < hex.length(); i += 2) {
         byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
         bytes[(int) Math.floor(i / 2)] = value;
      }
      return bytes;
   }
    //로그인 체크
    @RequestMapping(value = "/login.proc",headers="Accept=application/json",method = RequestMethod.POST)
    public @ResponseBody JSONObject loginChk(HttpServletRequest request) 
    {
       Member loginMember = new Member();
       JSONObject listObj = new JSONObject();
       String uemail = request.getParameter("m_email");
       String pwd = request.getParameter("m_pwd");
       HttpSession session = request.getSession();
       System.out.println("# 암호화 된 아이디: "+uemail+", # 암호화 된 비밀번호: "+pwd);
       PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key_");  //로그인전에 세션에 저장된 개인키를 가져온다.
       if (privateKey == null) { 
    	   System.out.println("# LoginCon / 로그인체크 실패");
          listObj.put("state", "false"); 
          //json 형태의 데이터를 DB나 웹서버에서 가져올때는 json데이터를 넣어주지않고 적절하게 가공만 하면 되므로 문제되지 않지만 다음과 같이 직접적으로 Generic 타입 선언 없이 put으로만 일방적으로 넣었을때 경고가 나타난것이다.
       }
       else{
          try{
             //암호화처리된 사용자계정정보를 복호화 처리한다.
             String _uemail = decryptRsa(privateKey, uemail);
             String _pwd = decryptRsa(privateKey, pwd);
             //복호화 처리된 계정정보를 member에 담아서 myBatis와 연동한다.
             loginMember.setM_email(_uemail);
             loginMember.setM_pwd(_pwd);
             Member m1 = service.login(loginMember, null, servletContext);
             if(m1.getM_email().length() > 0) { 
                  session.setAttribute("LOGINUSER", m1);
                  System.out.println("# LoginCon / 로그인체크 정상 작동");
                  listObj.put("state", "true");
             }else { 
                listObj.put("state", "false");
             } 
             //iBatis 처리 및 로그인후 session 처리
          }
          catch(Exception e)
          {
             listObj.put("state", "false");
             System.out.println("# LoginCon / 로그인체크 ERROR : "+e.getMessage()); 
          }
       } 
       System.out.println("# LoginCon / 로그인체크 정상 작동");
       return listObj;  
    }
} 
