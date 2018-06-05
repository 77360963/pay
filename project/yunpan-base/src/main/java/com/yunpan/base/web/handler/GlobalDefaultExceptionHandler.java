package com.yunpan.base.web.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunpan.base.web.util.Result;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	private Logger logger = LoggerFactory
			.getLogger(GlobalDefaultExceptionHandler.class);

	@ExceptionHandler(value = Throwable.class)
	@ResponseBody
	public Map<String, Object> defaultErrorHandler(HttpServletRequest req,
			Exception e) {
		logger.error(e.getMessage(), e);

		return Result.failed(e.getMessage());

	}

}