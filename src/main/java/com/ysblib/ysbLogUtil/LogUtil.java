package com.ysblib.ysbLogUtil;

import org.json.simple.JSONObject;
import com.amazonaws.services.lambda.runtime.Context;

public class LogUtil {
			
	public String FailLog(JSONObject input, Context context, Exception lambdaException) {
		JSONObject logJSON = new JSONObject();
		try {
			String username = input.get("username").toString();
			String deviceid = input.get("deviceid").toString();
			String lambdaName = context.getFunctionName();
			String logGroupName = context.getLogGroupName();
			String logStreamName = context.getLogStreamName();
			String awsRequestId = context.getAwsRequestId();
			
//			//datetime이 오늘인지 확인. 
//        	ZonedDateTime seoulDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
//        	String logDateTime = seoulDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        	
			logJSON.put("username", username);
			logJSON.put("deviceid", deviceid);
			logJSON.put("lambdaName", lambdaName);
			logJSON.put("logGroupName", logGroupName);
			logJSON.put("logStreamName", logStreamName);
			logJSON.put("awsRequestId", awsRequestId);
			logJSON.put("Exception", lambdaException.toString()); //to String 해줘야함. 그래야 모든 value가 ""로 감싸져서 나중에 parsing시 오류안남.

			//logJSON.put("Exception", "\\\""+lambdaException.toString()+"\\\""); //to String 해줘야함. 그래야 모든 value가 ""로 감싸져서 나중에 parsing시 오류안남.
			System.out.println("LogUtil : FailLog!!!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return logJSON.toJSONString();
	}

}
