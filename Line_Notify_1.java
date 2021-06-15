//cron-trigger을 이용해 10초마다 동작하게 만들고, 멤버변수에 tokenKey, url을 선언한다음 Connection시키고, POST방식으로 API를 연동한다.
//현재는 www쪽이기때문에, Analyzer쪽에서 다시 코드를 짜야한다.
//String lineMng = StringUtil.get(dao.selectAlarmMng().get("line_use_yn")); => sendMSG실행되게끔 만들어야한다. timetable을 이용해서.


public int sendMSG( String message ) {

        int statusCode;
        statusCode = -1;

        try {
            URL url = new URL( strEndpoint );
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod( "POST" );
            connection.addRequestProperty("Authorization",  "Bearer " + tokenKEY);
            connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setConnectTimeout(15*1000);
            connection.setDoOutput( true );
            connection.setUseCaches( false );
            connection.connect();

            connection.setReadTimeout(1000);
			/* connection.setDoInput( true ); */

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( os, "UTF-8") );

            writer.write( "message=" + message );
            writer.flush();
            writer.close();

            os.close();

            statusCode = connection.getResponseCode();

            if ( statusCode == 200 ) {
                //
            } else {
                throw new Exception( "Error:(StatusCode)" + statusCode + ", " + connection.getResponseMessage() );
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return statusCode;
    }
