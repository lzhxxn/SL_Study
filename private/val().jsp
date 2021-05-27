-- 양식에 텍스트를 입력하고 버튼을 클릭하면, 입력한 값을 출력합니다.

<!doctype html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<title>jQuery</title>
		<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
		<script>
			$( document ).ready( function() {
				$( 'button#jbInputButton' ).click( function() {
					var jb = $( 'input#jbInput' ).val();
					alert( jb );
				} );
			} );
		</script>
	</head>
	<body>
		<p><input type="text" id="jbInput"> <button id="jbInputButton">Click</button></p>
	</body>
</html>


--select 양식에서 값이 바뀌면, 그 값을 출력합니다.
<!doctype html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<title>jQuery</title>
		<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
		<script>
			$( document ).ready( function() {
				$( 'select#jbSelect' ).change( function() {
					var jb = $( 'select#jbSelect' ).val();
					alert( jb );
				} );
			} );
		</script>
	</head>
	<body>
		<select id="jbSelect">
			<option>One</option>
			<option>Two</option>
			<option>Three</option>
		</select>
	</body>
</html>


--버튼을 클릭하면 input의 값을 ABCDE로 설정합니다.
<!doctype html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<title>jQuery</title>
		<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
		<script>
			$( document ).ready( function() {
				$( 'button#jbInputButton' ).click( function() {
					$( 'input#jbInput' ).val( 'ABCDE' );
				} );
			} );
		</script>
	</head>
	<body>
		<p><input type="text" id="jbInput"> <button id="jbInputButton">Click</button></p>
	</body>
</html>
