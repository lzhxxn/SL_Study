package study;

public class Json {

	@GetMapping("/hello")
	public String hello() {
		return "Hi";
	}

		일반적인 방식의 메서드에서 return값이 문자열일 경우에는 뷰파일을 찾지못해 오류가 날것입니다. 뷰 리졸버가 동작하기 때문입니다. 
		Hi!라는 jsp파일을 찾게되는데 해당파일이 없어 오류를 띄우게 됩니다.

		하지만, Rest API는 목적이 어떤 페이지를 여는것이 아닌, 데이터 전송에 대한 목적입니다.
		바로 클라이언트쪽에 JSON방식으로 데이터를 추가하기위한 목적입니다.

		서버에서 클라이언트에게 JSON방식으로 데이터를 주기 위해서는 @ResponseBody라는 어노테이션을 사용한다.
		즉, return 데이터를 viewResolver에게 전달하지 않고 클라이언트에게 해당 데이터를 바로 응답하게 해주는 어노테이션 입니다.
		

	// [String]
	클라이언트에게 String형식의 데이터를 JSON으로 전달하겠습니다.
	@ResponseBody어노테이션을 추가한 후 메서드의 반환타입을 String으로 지정합니다.
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello world!";
	}
	
	즉, 서버를 올리고 uri에 ../hello 라고 치시면 Hello world! 라는 문자열이 출력이 됩니다.
	
	
	
	// [List]
	클라이언트에게 List형식의 데이터를 JSON으로 전달하겠습니다.
	@ResponseBody어노테이션을 추가한 후 메서드의 반환타입을 List< >로 지정합니다.
	
	@Getmapping("/hobbys")
	@ResponseBody
	public List<String> hobbys(){
		List<String> hobbys = Arrays.asList("축구", "수영", "음악감상");
		return hobbys;
		}
		
	서버를 올리고 uri에 /hobbys라고 입력하시면 ["축구", "수영", "음악감상'] 형태의 JSON형식으로 파싱되어 출력됩니다.
	 
	
	// [Map]
	이번에는 클라이언트에게 Map형식의 데이터를 JSON으로 전달하겠습니다.
	
	@GetMapping("/study")
	@ResponseBody
	public Map<String,Object> study() {
		Map<String,Object> subject = new HashMap<>();
		subject.put("자바", "Java");
		subject.put("JSP", "Java Server Pages");
		subject.put("JSON", "javascript object notation");
		return subject;
	}
	
	서버를 올리고 uri에 /study라고 입력하시면
	{"자바":"Java","JSP":"Java Server Pages","JSON":"javascript object notation"}형태의 JSON 형식으로 출력이 됩니다.
	Python의 Dictionary와 비슷하게 생겼습니다.
	
	
	// [Object]
	이번엔 Object 즉, 객체형태를 반환하여 JSON데이터의 형식을 확인해 보겠습니다.
	Person 클래스를 만들고 필드와 set get메서드를 오버라이드합니다.
	
	public class Person {
		private String name;
		private Integer age;
		private List<String> hobbys;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public List<String> getHobbys() {
			return hobbys;
		}
		public void setHobbys(List<String> hobbys) {
			this.hobbys = hobbys;
		}
	}
	
	
	//@ResponseBody 어노테이션을 추가한 후 메서드의 반환타입을 Person으로 지정한다.
	// Array.asList()는 배열을 List로 변환해주는 기능을 한다. 매개값으로 ...에 해당하는 가변인자 조건이 들어있어서 String나열하면 알아서 String배열로 변환하여 리스트로 넣어 전달한다.
	
	@Getmapping("/person")
	@ResponseBody
	public Person person() {
		Person p = new Person();
		p.setName("김철수");
		p.setAge(30);
		p.setHobbys(Arrays.asList("수영", "탁구", "요리"));
		//Arrays.asList
		return p
	}
	
	{"name":"김철수","age":30,"hobbys":["수영","탁구","요리"]} 형태로 브라우져에 출력된다. 역시나 객체도 Map의 형태처럼 {중괄호}로 둘러쌓여있으며, 필드 이름이
	key가 됩니다. hobbys는 List이므로 value가 [대괄호]형태로 묶여서 들어오게 됩니다.
	
	이러한 JSON의 형태를 기억해야만 한다.
	그 이유는 클라이언트에 보냈을 때, 클라이언트에서는 이러한 JSON 형태에 맞게 데이터를 다시 파싱해서 사용해야하기 때문이다.
	
	
	@RestController 선언한다면, 여러개의 @ResponseBody 하나하나 메서드마다 지정하지 않고 처음 컨트롤러를 만들때 REST API기능을 가진 컨트롤러로 만들 수 있다.
	
}
