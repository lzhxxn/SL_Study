		/*##############################################################
		 * 5. LINE 발송 ( Analyzer_AlarmHandler.java ) 
		 *############################################################*/
		if(cond.get("line_use_yn").equals("Y")) {
			List<Map<String,Object>> tokenKEY = dao.selectLineToken();
			for(Map<String,Object> key:tokenKEY) {
				Object token = key.get("line_token");
				if(token instanceof String && !((String) token).isEmpty()) {
					logger.info("############################################ LINE_NOTIFY_API START !");
					int statusCode = -1;
					try {
			            URL url = new URL( strEndpoint );
			            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

			            connection.setRequestMethod( "POST" );
						connection.addRequestProperty("Authorization",  "Bearer " + token);
			            connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			            connection.setConnectTimeout(15*1000);
			            connection.setDoOutput( true );
			            connection.setUseCaches( false );
			            connection.connect();

			            connection.setReadTimeout(1000);
						//connection.setDoInput( true );

			            try(OutputStream os = connection.getOutputStream();
			            		BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( os, "UTF-8") )){

			                writer.write( "message=" + smsMsg );
				            writer.flush();
//				            writer.close();
			            }catch(Exception e) {
			            	throw e;
			            }

			            statusCode = connection.getResponseCode();

			            if ( statusCode == 200 ) {
			                //
			            } else {
			                throw new Exception( "Error:(StatusCode)" + statusCode + ", " + connection.getResponseMessage() );
			            }
			            connection.disconnect();
			        } catch (Exception e) {
			            e.printStackTrace();
			            logger.info("############################################ LINE_NOTIFY_API ERROR !");
			        }	
				}
			}		
		}
